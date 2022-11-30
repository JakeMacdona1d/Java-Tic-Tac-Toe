PACKAGE = cpsc2150/extendedTicTacToe

default: ${PACKAGE}/models/*.java ${PACKAGE}/controllers/*.java ${PACKAGE}/views/*.java ${PACKAGE}/*.java
	javac ${PACKAGE}/models/AbsGameBoard.java ${PACKAGE}/models/BoardPosition.java ${PACKAGE}/models/GameBoard.java 
	javac ${PACKAGE}/models/GameBoardMem.java ${PACKAGE}/models/IGameBoard.java
	javac ${PACKAGE}/controllers/*.java ${PACKAGE}/views/*.java ${PACKAGE}/TicTacToeGame.java


run: ${PACKAGE}/*.class
	java cpsc2150.extendedTicTacToe.TicTacToeGame

clean:
	rm -f ${PACKAGE}/TicTacToeGame.class
	rm -f ${PACKAGE}/models/*.class
	rm -f ${PACKAGE}/views/*.class
	rm -f ${PACKAGE}/controllers/*.class

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

guiSet:
	export DISPLAY="`grep nameserver /etc/resolv.conf | sed 's/nameserver //'`:0"
	export DISPLAY="`sed -n 's/nameserver //p' /etc/resolv.conf`:0"
	export DISPLAY=$(ip route|awk '/^default/{print $3}'):0.0
	echo xfce4-session > ~/.xsession







