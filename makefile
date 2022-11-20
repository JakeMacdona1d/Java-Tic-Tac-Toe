PACKAGE = cpsc2150/extendedTicTacToe

default: ${PACKAGE}/models/*.java ${PACKAGE}/*.java
	javac ${PACKAGE}/models/AbsGameBoard.java ${PACKAGE}/models/BoardPosition.java ${PACKAGE}/models/GameBoard.java ${PACKAGE}/models/GameBoardMem.java ${PACKAGE}/models/IGameBoard.java
	javac ${PACKAGE}/*.java

run: ${PACKAGE}/*.class
	java cpsc2150.extendedTicTacToe.GameScreen

clean:
	rm -f ${PACKAGE}/GameScreen.class
	rm -f ${PACKAGE}/models/*.class

zip:
	rm project.zip
	zip project.zip -r ${PACKAGE}/models ${PACKAGE}/GameScreen.java makefile report.pdf

test: ${PACKAGE}/models/*.java
	javac -cp .:/usr/share/java/junit4.jar ${PACKAGE}/models/TestGameBoard.java ${PACKAGE}/models/TestGameBoardMem.java

testGB: ${PACKAGE}/models/*.class
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.extendedTicTacToe.models.TestGameBoard

testGBmem: ${PACKAGE}/models/*.class
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.extendedTicTacToe.models.TestGameBoardMem

testing: default test testGB testGBmem



