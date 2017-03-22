package Fitnesse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import game.Game;

public class StartGame {

	public StartGame() {

	}

	public String welcomeMessage() {
		return Game.sendWelcome();
	}

	public String userInputY() {
		return Game.sendRules();
	}

	public boolean userInputYEnterGameStart() throws FileNotFoundException {
		String[] args = null;
		System.setIn(new FileInputStream("C:/Users/rapha/fitnesseTraining/WumpusGame/GameTest.txt"));
		Game.main(args);
		return true;
	}

	public boolean userInputNGameEnds() throws FileNotFoundException {
		String[] args = null;
		System.setIn(new FileInputStream("C:/Users/rapha/fitnesseTraining/WumpusGame/GameTest2.txt"));
		Game.main(args);
		return true;
	}

}
