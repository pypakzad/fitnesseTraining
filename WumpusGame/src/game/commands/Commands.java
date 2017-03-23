package game.commands;

public enum Commands {

	w("w"), a("a"), s("s"), d("d"), up("i", "North"), dwn("k", "South"), lft("j", "West"), rt("l",
			"East"), textExit("testExit");

	String userInput;
	String direction;

	Commands(String key) {
		userInput = key;
	}

	Commands(String key, String cardinality) {
		userInput = key;
		direction = cardinality;
	}

	public String getUserInput() {
		return userInput;
	}

	public String getDirection() {
		return direction;
	}

}
