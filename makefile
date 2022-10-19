default: cpsc2150/extendedTicTacToe/models/*.java cpsc2150/extendedTicTacToe/*.java
	javac cpsc2150/extendedTicTacToe/models/*.java 
	javac cpsc2150/extendedTicTacToe/*.java
run: cpsc2150/extendedTicTacToe/*.class
	java cpsc2150.extendedTicTacToe.GameScreen
clean:
	rm -f cpsc2150/extendedTicTacToe/GameScreen.class
	rm -f cpsc2150/extendedTicTacToe/models/*.class
zip:
	rm project.zip
	zip project.zip -r cpsc2150 makefile report.pdf
