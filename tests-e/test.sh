a="A-02-05-M-ok"; 
cat ../tests-e/tests_25-e/auto-tests/$a.import; 
      export CLASSPATH=/usr/share/java/po-uuilib.jar:sth-core/sth-core.jar:sth-app/sth-app.jar; 
      java -Dimport=../tests/tests_25/auto-tests/$a.import sth.app.App
