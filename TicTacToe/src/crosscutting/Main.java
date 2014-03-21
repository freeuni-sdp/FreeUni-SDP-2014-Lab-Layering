package crosscutting;

import java.util.Scanner;

import logic.*;
import model.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ui.*;

/**
 * @author George Mamaladze
 * This is a launcher class for the application.
 * It responsible for object graph creation and starting the game.
 */
public class Main {

	/**
	 * Starts a game variant based on console arguments.
	 * Wrong argument prints help.
	 * Missing argument starts the default console game.
	 */	
	public static void main(String[] args) {
		if (args.length==0) {
			playConsole();
		}
		
		String arg0 = args[0];
		switch(arg0) {
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
		System.out.println("-console / -c \t to start the console game variant");
		System.out.println("-gui / -g \t to start the gui game variant");
		//TODO: Please add help for options starting further game variants here
	}

	/**
	 * Constructs and starts the console game
	 */
	private static void playConsole() {
		Scanner scanner = new Scanner(System.in);

		try {
			Presenter presenter = new ConsolePresenter(System.out);
			Board board = new Board();
			Player xPlayer = new ConsolePlayer(scanner, System.out);
			Player oPlayer = new ConsolePlayer(scanner, System.out);
			Referee referee = new PrimeReferee();
			
			Game game = new Game(board, xPlayer, oPlayer, referee, presenter);
			game.play(PlayerColor.X);
		} finally {
			scanner.close();
		}
	}
	
	/**
	 * Constructs and starts the GUI game
	 */
	private static void playGui() {
		throw new NotImplementedException();
	}
}