PROJECTDIR=../../project
v2=tests
v=tests_ef


echo "Compiling..."
cd $PROJECTDIR && make &> /dev/null && cd ../tests/tests_ef
echo "Done compiling!"

if [ ! -d results/ ]; then
	mkdir results
fi

echo "" > log/A-00.log
echo "" > log/A-00wik.log

for FILE_RAW in $(ls auto-tests/*.in);
do

	FILE=$(echo $FILE_RAW | grep -o -P "A-\d{2}-\d{2}-M-ok")

	java -Dimport=auto-tests/$FILE.import -Din=auto-tests/$FILE.in -Dout=results/$FILE.outhyp -cp "/usr/share/java/po-uuilib.jar:$PROJECTDIR/sth-core/sth-core.jar:$PROJECTDIR/sth-app/sth-app.jar" sth.app.App

	echo  $FILE
	if diff auto-tests/expected/$FILE.out results/$FILE.outhyp >/dev/null ; then
			 if [ -f log/$FILE.log ];then
				 rm log/$FILE.log
			 fi
				echo "yes";
				echo $FILE.................. o o>> log/A-00wik.log
	else
		echo "no";
		echo $FILE.................. o !>> log/A-00wik.log

		echo $FILE FAILED > log/$FILE.log
		echo $FILE FAILED >> log/A-00.log

		cat desc/$FILE.desc >> log/$FILE.log
		echo please schedule a fix >> log/$FILE.log
		echo $FILE  >> log/$FILE.log
		echo "______________________________________________________________________________________________________________" >> log/$FILE.log
		cat auto-tests/$FILE.import >> log/$FILE.log
		echo "______________________________________________________________________________________________________________" >> log/$FILE.log
		cat auto-tests/$FILE.in >> log/$FILE.log

		echo "________expected______________________________________________________________________________________________________" >> log/$FILE.log
		cat auto-tests/expected/$FILE.out  >> log/$FILE.log
		echo "________us______________________________________________________________________________________________________" >> log/$FILE.log
		cat results/$FILE.outhyp >> log/$FILE.log
		echo "_________diff_____________________________________________________________________________________________________" >> log/$FILE.log
		diff -b  results/$FILE.outhyp auto-tests/expected/$FILE.out >> log/$FILE.log
		#____________________________________________________________________________________________________________________________________________________________


	fi
echo "______________________________________________"

done
cd $PROJECTDIR && make clean &> /dev/null && cd ../$v2/$v
find . -type f  ! -name "*.?*" -delete

notify-send "done"

echo "Done!"
