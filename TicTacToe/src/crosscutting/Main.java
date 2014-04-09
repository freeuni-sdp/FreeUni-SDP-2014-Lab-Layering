package crosscutting;

import java.awt.Button;
import java.util.Scanner;

import javax.swing.JLabel;

import logic.*;
import model.*;
import ui.*;

/**
 * @author George Mamaladze This is a launcher class for the application. It
 *         responsible for object graph creation and starting the game.
 */
public class Main {

	/**
	 * Starts a game variant based on console arguments. Wrong argument prints
	 * help. Missing argument starts the default console game.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			playConsole();
			return;
		}

		String arg0 = args[0];
		switch (arg0) {
		case "-console":
		case "-c":
			playConsole();
			break;

		case "-gui":
		case "-g":
			playGui();
			break;

		default:
			printHelp(arg0);
			break;
		}
	}

	/**
	 * Prints usage help message to console
	 */
	private static void printHelp(String arg0) {
		System.out.println(String.format("Invalid argument '%s'", arg0));
		System.out.println("Please specify on of the following arguments:");
		System.out
				.println("-console / -c \t to start the console game variant");
		System.out.println("-gui / -g \t to start the gui game variant");
		// TODO: Please add help for options starting further game variants here
	}

	/**
	 * Constructs and starts the console game
	 */
	private static void playConsole() {
		Scanner scanner = new Scanner(System.in);
		Presenter presenter = new ConsolePresenter(System.out);
		Player xPlayer = new ConsolePlayer(scanner, System.out);
		Player oPlayer = new ConsolePlayer(scanner, System.out);
		try {
			playGame(presenter, xPlayer, oPlayer);
		} finally {
			scanner.close();
		}
	}

	/**
	 * Constructs and starts the GUI game
	 */
	private static void playGui() {

		ButtonClickListener listener = new ButtonClickListener();
		JLabel header = new JLabel();
		Presenter presenter = new GUIPresenter(listener, header);

		Player xPlayer = new GUIPlayer(listener, header);
		Player oPlayer = new GUIPlayer(listener, header);

		playGame(presenter, xPlayer, oPlayer);

	}

	/**
	 * @author Tamar Keshelava. 
	 * 		Referee referee = new PrimeReferee();	
	 * 		Here we create PrimeReferee object, because we have 3x3 board, but if we 
	 *		want to change game rules we must write this:
	 * 		Referee referee = new ProReferee();	
	 */
	private static void playGame(Presenter presenter, Player xPlayer,
			Player oPlayer) {

		Board board = new Board();
		Referee referee = new PrimeReferee();		
		Game game = new Game(board, xPlayer, oPlayer, referee, presenter);
		game.play(PlayerColor.X);

	}

}