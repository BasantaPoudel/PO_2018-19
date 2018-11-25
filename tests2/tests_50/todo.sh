PROJECTDIR=../../project
v2=tests2
v=tests_50

echo -e "Compiling..."
cd $PROJECTDIR && make &> /dev/null && cd ../$v2/$v
echo -e "Done compiling!"

if [ ! -d results/ ]; then
	mkdir results
fi

for FILE_RAW in $(ls auto-tests/*.in);
do


	FILE=$(echo -e $FILE_RAW | grep -o -P "A-\d{2}-\d{2}-M-ok")

	java -Dimport=auto-tests/$FILE.import -Din=auto-tests/$FILE.in -Dout=results/$FILE.outhyp -cp "/usr/share/java/po-uuilib.jar:$PROJECTDIR/sth-core/sth-core.jar:$PROJECTDIR/sth-app/sth-app.jar" sth.app.App

	echo -e $FILE
	if diff auto-tests/expected/$FILE.out results/$FILE.outhyp >/dev/null ; then
			 if [ -f log/$FILE.log ];then
				 rm log/$FILE.log
			 fi
				echo yes;
	else
		echo no;

		echo -e $"______________________________________________________________________________________________________________" > log/$FILE.log

		echo -e $FILE FAILED >> log/$FILE.log
		cat desc/$FILE.desc >> log/$FILE.log
		echo -e please schedule a fix >> log/$FILE.log
		echo -e $FILE  >> log/$FILE.log
		echo -e $"______________________________________________________________________________________________________________" >> log/$FILE.log
		cat auto-tests/$FILE.import >> log/$FILE.log
		echo -e $"______________________________________________________________________________________________________________" >> log/$FILE.log
		cat auto-tests/$FILE.in >> log/$FILE.log

		echo -e $"________us______________________________________________________________________________________________________" >> log/$FILE.log
		cat results/$FILE.outhyp >> log/$FILE.log
		echo -e $"________exp______________________________________________________________________________________________________" >> log/$FILE.log
		cat auto-tests/expected/$FILE.out  >> log/$FILE.log
		echo -e $"_________diff_____________________________________________________________________________________________________" >> log/$FILE.log
		diff -b  results/$FILE.outhyp auto-tests/expected/$FILE.out >> log/$FILE.log
		#____________________________________________________________________________________________________________________________________________________________


	fi
echo -e $"______________________________________________"

done
cd $PROJECTDIR && make clean &> /dev/null && cd ../$v2/$v

notify-send "done"
echo -e "Done!"
