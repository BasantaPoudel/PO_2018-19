PROJECTDIR=../../project
v2=tests2
v=tests_50



if [ ! -d results/ ]; then
	mkdir results
fi

for FILE_RAW in $(ls auto-tests/*.in);
do
	FILE=$(echo -e $FILE_RAW | grep -o -P "A-\d{2}-\d{2}-M-ok")
	java -Dimport=auto-tests/$FILE.import -Din=auto-tests/$FILE.in -Dout=results/$FILE.outhyp -cp "/usr/share/java/po-uuilib.jar:$PROJECTDIR/sth-core/sth-core.jar:$PROJECTDIR/sth-app/sth-app.jar" sth.app.App

	echo -e $FILE
	if diff auto-tests/expected/$FILE.out results/$FILE.outhyp >/dev/null ; then
				echo yes;
	else
		echo no;
		echo -e $"_________diff_____________________________________________________________________________________________________" >> log/$FILE.log
		if [ ! -d diff/ ]; then
			mkdir diff
		fi
		diff -b  results/$FILE.outhyp auto-tests/expected/$FILE.out >> diff/$FILE.diff
		#____________________________________________________________________________________________________________________________________________________________


	fi
echo -e $"_______________________________________________"

done
cd $PROJECTDIR && make clean &> /dev/null && cd ../$v2/$v

notify-send "done"
echo -e "Done!"
