PROJECTDIR=../../project
v2=tests2
v=tests_50

echo -e "Compiling..."
cd $PROJECTDIR && make &> /dev/null && cd ../$v2/$v
echo -e "Done compiling!"

if [ ! -d hyp/ ]; then
	mkdir hyp
fi

for FILE_RAW in $(ls auto-tests/*.in);
do


	FILE=$(echo -e $FILE_RAW | grep -o -P "A-\d{2}-\d{2}-M-ok")

	java -Dimport=auto-tests/$FILE.import -Din=auto-tests/$FILE.in -Dout=hyp/$FILE.outHyp -cp "/usr/share/java/po-uuilib.jar:$PROJECTDIR/sth-core/sth-core.jar:$PROJECTDIR/sth-app/sth-app.jar" sth.app.App


	if diff auto-tests/expected/$FILE.out hyp/$FILE.outHyp >/dev/null ; then
		echo -e $FILE PASSED V
	else
		echo -e $FILE FAILED XX


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

		echo -e $"________us______________________________________________________________________________________________________" >> log/$FILE.log
		cat hyp/$FILE.outHyp >> log/$FILE.log
		echo -e $"________exp______________________________________________________________________________________________________" >> log/$FILE.log
		cat auto-tests/expected/$FILE.out  >> log/$FILE.log
		echo -e $"_________diff_____________________________________________________________________________________________________" >> log/$FILE.log
		diff -b  hyp/$FILE.outHyp auto-tests/expected/$FILE.out >> log/$FILE.log
		#____________________________________________________________________________________________________________________________________________________________


	fi
echo -e $"______________________________________________________________________________________________________________"

done
cd $PROJECTDIR && make clean &> /dev/null && cd ../$v2/$v

notify-send "done"
echo -e "Done!"
