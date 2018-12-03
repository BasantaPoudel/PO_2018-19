PROJECTDIR=../../project
v=tests_25-e

echo -e "Compiling..."
cd $PROJECTDIR && make &> /dev/null && cd ../tests-e/$v
echo -e "Done compiling!"

if [ ! -d results/ ]; then
	mkdir results
fi

for FILE_RAW in $(ls auto-tests/*.in);
do


	FILE=$(echo -e $FILE_RAW | grep -o -P "A-\d{2}-\d{2}-M-ok")

	echo -e executing $FILE
	java -Dimport=auto-tests/$FILE.import -Din=auto-tests/$FILE.in -Dout=results/$FILE.outhyp -cp "/usr/share/java/po-uuilib.jar:$PROJECTDIR/sth-core/sth-core.jar:$PROJECTDIR/sth-app/sth-app.jar" sth.app.App


	if diff auto-tests/expected/$FILE.out results/$FILE.outhyp >/dev/null ; then
		echo -e $FILE PASSED
	else
		echo -e $"______________________________________________________________________________________________________________"
		echo -e $FILE FAILED


		#____________________________________________________________________________________________________________________________________________________________
		echo -e $"______________________________________________________________________________________________________________" > log/$FILE.log
		echo -e $FILE FAILED >> log/$FILE.log
		cat desc/$FILE.desc >> log/$FILE.log
		echo -e please schedule a fix >> log/$FILE.log
		echo -e $FILE  >> log/$FILE.log
		echo -e $"______________________________________________________________________________________________________________" >> log/$FILE.log
		cat auto-tests/$FILE.import >> log/$FILE.log
		echo -e $"______________________________________________________________________________________________________________" >> log/$FILE.log
		cat auto-tests/$FILE.in >> log/$FILE.log
		echo -e $"______________________________________________________________________________________________________________" >> log/$FILE.log
		cat auto-tests/expected/$FILE.out >> log/$FILE.log
		echo -e $"______________________________________________________________________________________________________________" >> log/$FILE.log
		diff -b -y results/$FILE.outhyp auto-tests/expected/$FILE.out >> log/$FILE.log
		#____________________________________________________________________________________________________________________________________________________________
		
	fi

done
cd $PROJECTDIR && make clean &> /dev/null && cd ../tests-e/$v

notify-send "done"
echo -e "Done!"
