PACKAGE = cpsc2150/extendedTicTacToe
default: ${PACKAGE}/models/*.java ${PACKAGE}/*.java
	javac ${PACKAGE}/models/*.java 
	javac ${PACKAGE}/*.java
run: ${PACKAGE}/*.class
	java cpsc2150.extendedTicTacToe.GameScreen
clean:
	rm -f ${PACKAGE}/GameScreen.class
	rm -f ${PACKAGE}/models/*.class
zip:
	rm project.zip
	zip project.zip -r cpsc2150 makefile report.pdf

test: default ${PACKAGE}/test/Testing.java
	javac -cp .:/usr/share/java/junit4.jar ${PACKAGE}/test/Testing.java 

testRun: ${PACKAGE}/test/Testing.class ${PACKAGE}/models/*.class ${PACKAGE}/*.class
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.extendedTicTacToe.test.Testing

testing: default test testRun


