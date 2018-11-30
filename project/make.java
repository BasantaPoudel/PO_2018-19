(cd sth-core; make  all)
make[1]: Entering directory '/home/user/java/ws/3/project/sth-core'
(cd src; javac -encoding UTF-8 `find . -name \*.java`)
./sth/core/SurveySubmission.java:11: error: invalid method declaration; return type required
	public SurveySubmission(double hoursSpent ,String freeComments){
	       ^
1 error
Makefile:4: recipe for target 'all' failed
make[1]: *** [all] Error 1
make[1]: Leaving directory '/home/user/java/ws/3/project/sth-core'
Makefile:2: recipe for target 'all' failed
make: *** [all] Error 2
