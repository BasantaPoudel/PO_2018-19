PROJECTDIR=../../project

echo -e "Compiling..."
cd $PROJECTDIR && make &> /dev/null && cd ../tests-edited/tests_25
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
		echo -e please schedule a fix
		echo -e $FILE 
		echo -e $"______________________________________________________________________________________________________________"
		cat auto-tests/$FILE.import
		echo -e $"______________________________________________________________________________________________________________"
		cat auto-tests/$FILE.in
		echo -e $"______________________________________________________________________________________________________________"
		diff -b -y results/$FILE.outhyp auto-tests/expected/$FILE.out
		# echo -e $"ours ______________________________________________________________________________________________________________"
		# cat results/$FILE.outhyp ; echo
		# echo -e $"correct ______________________________________________________________________________________________________________"
		# cat auto-tests/expected/$FILE.out ; echo
		# echo -e $"______________________________________________________________________________________________________________"
		
	fi

done	
cd $PROJECTDIR && make clean &> /dev/null && cd ../tests-edited/tests_25

notify-send "done"
echo -e "Done!"
