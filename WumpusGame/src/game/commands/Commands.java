package game.commands;

public enum Commands {

	w("w"), a("a"), s("s"), d("d"), up("^[[A"), dwn("^[[B"), lft("^[[D"), rt("^[[C"), textExit("testExit");

	String userInput;

	Commands(String key) {
		userInput = key;
	}

	public String getUserInput() {
		return userInput;
	}

}
