PROJECTDIR=../project
TESTS_DIRNAME=Tests

 

echo $"___________________________________________________________________"
echo "Compiling..."	
# entra e sai
cd $PROJECTDIR && make &> /dev/null && cd ../$TESTS_DIRNAME
echo $"___________________________________________________________________"
echo "Done compiling!"
echo $"___________________________________________________________________"
echo $"___________________________________________________________________"
echo $"___________________________________________________________________"
echo $"___________________________________________________________________"



if [ ! -d results/ ]; then
	mkdir results
fi
for FILE_RAW in $(ls auto-tests/*.in);
do
	FILE=$(echo $FILE_RAW | grep -o -P "A-\d{2}-\d{2}-M-ok")
	echo Testing $FILE...
	# /usr/share/java/po-uuilib.jar:./sth-core/sth-core.jar:./sth-app/sth-app.jar
	echo ___________________________________________________________________
	echo $PWD
	echo ___________________________________________________________________
	cd $PROJECTDIR
	export CLASSPATH=/usr/share/java/po-uuilib.jar:./sth-core/sth-core.jar:./sth-app/sth-app.jar
	java -Dimport=auto-tests/$FILE.import -Din=auto-tests/$FILE.in -Dout=results/$FILE.outhyp sth.app.App	
	echo "Testing now the result:"

	if diff auto-tests/expected/$FILE.out results/$FILE.outhyp >/dev/null ; then
		echo $"_______________________________________________________"
		echo [1] yes
	else
		echo $"_______________________________________________________"
		echo [0] no
	fi
	
done	


echo $"___________________________________________________________________"
echo "Done!"
