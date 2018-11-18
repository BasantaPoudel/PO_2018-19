PROJECTDIR=../../project

echo "Compiling..."
cd $PROJECTDIR && make &> /dev/null && cd ../tests/tests_25
echo "Done compiling!"

if [ ! -d results/ ]; then
	mkdir results
fi

for FILE_RAW in $(ls auto-tests/*.in);
do


	FILE=$(echo $FILE_RAW | grep -o -P "A-\d{2}-\d{2}-M-ok")

	echo executing $FILE
	java -Dimport=auto-tests/$FILE.import -Din=auto-tests/$FILE.in -Dout=results/$FILE.outhyp -cp "/usr/share/java/po-uuilib.jar:$PROJECTDIR/sth-core/sth-core.jar:$PROJECTDIR/sth-app/sth-app.jar" sth.app.App	
	if diff auto-tests/expected/$FILE.out results/$FILE.outhyp >/dev/null ; then
		echo $FILE PASSED
	else
		echo $FILE FAILED
		echo please schedule a fix
		echo
		echo $FILE 
		diff -b auto-tests/expected/$FILE.out results/$FILE.outhyp
		echo 
		cat auto-tests/expected/$FILE.out
		cat results/$FILE.outhyp
	fi
	
done	

cd $PROJECTDIR && make clean &> /dev/null && cd ../tests/tests_25


echo "Done!"
