JAVADIR=/usr/share/java
JARNAME=sth-app

all:
	(cd src; javac -cp $(JAVADIR)/po-uuilib.jar:../../sth-core/sth-core.jar -encoding UTF-8 `find . -name \*.java`)
	(cd src; jar cvf ../$(JARNAME).jar `find . -name \*.class -o -name \*.java` )

clean:
	$(RM) $(JARNAME).jar `find . -name \*.class`
# prara definir cp para execucao
#export CLASSPATH=/usr/share/java/po-uuilib.jar:../sth-core/sth-core.jar:./sth-app.jar
#java sth.app.App

# alt:
#java -cp /usr/share/java/po-uuilib.jar:../sth-core/sth-core.jar:./sth-app.jar sth.app.App


# apos import	
#java -Dimport=test.import -Din=test.in -Dout=test.outhyp sth.app.App
