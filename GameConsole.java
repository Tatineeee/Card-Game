import java.util.Scanner;

/**
	The GameConsole class as a text-only interface for the Card Game.
	Through the console, it accepts values for the player names and if the player wishes to randomize the deck and use human cards.
	After the game, it asks if they want to play another game.

	@author Constantine P. Pazcoguin (243545)
    @version December 3, 2024
	
	I have not discussed the Java language code in my program 
	with anyone other than my instructor or the teaching assistants 
	assigned to this course.

	I have not used Java language code obtained from another student, 
	or any other unauthorized source, either modified or unmodified.

	If any Java language code or documentation used in my program 
	was obtained from another source, such as a textbook or website, 
	that has been clearly noted with a proper citation in the comments 
	of my program.
*/

public class GameConsole {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean playAgain = true;
		boolean random = false;
		boolean human = false;
		if (args.length > 2 && args[2].toUpperCase().equals("RANDOM")) {
			random = true;
		} else if (args.length > 2 && args[2].toUpperCase().equals("HUMAN")) {
			human = true;
		}
		if (args.length > 3 && args[3].toUpperCase().equals("RANDOM")) {
			random = true;
		} else if (args.length > 3 && args[3].toUpperCase().equals("HUMAN")) {
			human = true;
		}
		while (playAgain) {
			Player player1 = new Player(args[0]);
			Player player2 = new Player(args[1]);
			GameMaster gameMaster = new GameMaster(player1, player2, random, human);
			System.out.println("Welcome, " + player1.getName() + " and " + player2.getName() + "!\nThe game begins.\n");
			System.out.print(gameMaster.dealCard());
			while (!gameMaster.hasWinner()) {
				System.out.print("ATTACK, SWAP, HEAL, or show player STATUS? ");
				String action = in.next();
				System.out.println(gameMaster.play(action.toUpperCase()));
			}
			System.out.println("Game Over!\n");
			System.out.print("Play again? (YES/NO): ");
			String response = in.next().toUpperCase();
			if (response.equals("YES")) {
				System.out.println("\nStarting a new game...\n");
			} else {
				playAgain = false;
				System.out.println("\nThanks for playing!");
			}
		}
		in.close();
	}
}