PROJECTDIR=../../project
v2=tests2
v=tests_50

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
	echo "" > log/$FILE.log

	if diff auto-tests/expected/$FILE.out results/$FILE.outhyp >/dev/null ; then
			 if [ -f log/$FILE.log ];then
				 rm log/$FILE.log
			 fi
			echo "yes";
			printf "$FILE.................. o o ">> log/A-00wik.log
			cat desc/$FILE.desc >> log/A-00wik.log
	else
		echo "no";

		printf "$FILE.................. o ! ">> log/A-00wik.log
		cat desc/$FILE.desc >> log/A-00wik.log

		echo $FILE  >> log/$FILE.log
		cat desc/$FILE.desc >> log/$FILE.log

		echo "_____________________ in _________________________________________________________________________________________" >> log/$FILE.log
		cat auto-tests/$FILE.in >> log/$FILE.log

		echo "__________ diff ____________________________________________________________________________________________________" >> log/$FILE.log
		colordiff -b -y  results/$FILE.outhyp auto-tests/expected/$FILE.out >> log/$FILE.log


		echo "________expected______________________________________________________________________________________________________" >> log/$FILE.log
		cat auto-tests/expected/$FILE.out  >> log/$FILE.log

		echo "__________us____________________________________________________________________________________________________" >> log/$FILE.log
		cat results/$FILE.outhyp >> log/$FILE.log




		echo "______________________________________________________________"$FILE"_________________________________________________________"
		cat desc/$FILE.desc
		echo

		# in
		cat auto-tests/$FILE.in
		echo "_________diff_____________________________________________________________________________________________________" >> log/$FILE.log
		echo
		colordiff -b -y results/$FILE.outhyp auto-tests/expected/$FILE.out
		echo

		echo "____________________im__________________________________________________________________________________________" >> log/$FILE.log
		cat auto-tests/$FILE.import >> log/$FILE.log
		#____________________________________________________________________________________________________________________________________________________________


	fi
	echo "______________________________________________"

done
cd $PROJECTDIR

notify-send "done"
echo "Done!"
