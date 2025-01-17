import java.util.ArrayList;

/**
    The GameMaster class initializes the number of turns, deck of cards, winner status, and the 2 players.
	It has a predetermined deck of cards but can be randomized.
	It allows the GameMaster to conduct an attack or swap on the players' cards.
	It can determine if a target card is weak or resistant against an attacking card.
	It can deal damage to the target card according to its resistance/weakness and updates the card's current health.
	It can return the status of a winner and a game report which shows the number of turns and the players' cards statuses.

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

public class GameMaster {
	private int turn;
	private ArrayList<Card> deck;
	private boolean winner;
	private Player player1;
	private Player player2;
	private boolean random;

	/**
		Initializes the game master and the intitial turns, creates a new deck using {@link #assembleDeck()}, sets winner to false, and assigns the players.
		Decides if the game will be randomized and/or use human cards.
	 */
	public GameMaster(Player a, Player b, boolean r, boolean h) {
		turn = 1;
		deck = new ArrayList<>();
		if (h) {
			assembleDeckHuman();
		} else {
			assembleDeck();
		}
		winner = false;
		player1 = a;
		player2 = b;
		if (r) {
			random = true;
		} else {
			random = false;
		}
	}

	/**
		Adds the following cards to the deck.
	 */
	private void assembleDeck() {
		deck.add(new Card("Dragon", "Aquira", 174, 26));
		deck.add(new Card("Ghost", "Brawn", 130, 48));
		deck.add(new Card("Fairy", "Cerulea", 162, 29));
		deck.add(new Card("Dragon", "Demi", 147, 28));
		deck.add(new Card("Ghost", "Elba", 155, 37));
		deck.add(new Card("Fairy", "Fye", 159, 42));
		deck.add(new Card("Dragon", "Glyede", 129, 26));
		deck.add(new Card("Ghost", "Hydran", 163, 35));
		deck.add(new Card("Fairy", "Ivy", 146, 45));
		deck.add(new Card("Dragon", "Jet", 170, 24));
		deck.add(new Card("Ghost", "Kineti", 139, 21));
		deck.add(new Card("Fairy", "Levi", 160, 43));
		deck.add(new Card("Dragon", "Meadow", 134, 29));
		deck.add(new Card("Ghost", "Naidem", 165, 26));
		deck.add(new Card("Fairy", "Omi", 145, 21));
		deck.add(new Card("Dragon", "Puddles", 170, 34));
		deck.add(new Card("Ghost", "Quarrel", 151, 29));
		deck.add(new Card("Fairy", "Raven", 168, 32));
		deck.add(new Card("Dragon", "Surge", 128, 27));
		deck.add(new Card("Ghost", "Takiru", 140, 26));
		deck.add(new Card("Fairy", "Ustelia", 163, 47));
		deck.add(new Card("Dragon", "Verwyn", 145, 25));
		deck.add(new Card("Ghost", "Wyverin", 158, 32));
		deck.add(new Card("Fairy", "Xios", 155, 27));
		deck.add(new Card("Dragon", "Yora", 159, 44));
		deck.add(new Card("Ghost", "Zulu", 125, 46));
	}

	/**
		(ADD ON): assembleDeck method with human cards.
	 */
	private void assembleDeckHuman() {
		deck.add(new Card("Dragon", "Aquira", 174, 26));
		deck.add(new Card("Human", "Sir Choobs", 163, 28));
		deck.add(new Card("Ghost", "Brawn", 130, 48));
		deck.add(new Card("Human", "Sir Ross", 142, 35));
		deck.add(new Card("Fairy", "Cerulea", 162, 29));
		deck.add(new Card("Human", "Maam Jess", 152, 31));
		deck.add(new Card("Dragon", "Demi", 147, 28));
		deck.add(new Card("Human", "Sir Gab", 182, 29));
		deck.add(new Card("Ghost", "Elba", 155, 37));
		deck.add(new Card("Human", "Sir Gerrick", 172, 25));
		deck.add(new Card("Fairy", "Fye", 159, 42));
		deck.add(new Card("Dragon", "Glyede", 129, 26));
		deck.add(new Card("Ghost", "Hydran", 163, 35));
		deck.add(new Card("Fairy", "Ivy", 146, 45));
		deck.add(new Card("Dragon", "Jet", 170, 24));
		deck.add(new Card("Ghost", "Kineti", 139, 21));
		deck.add(new Card("Fairy", "Levi", 160, 43));
		deck.add(new Card("Dragon", "Meadow", 134, 29));
		deck.add(new Card("Ghost", "Naidem", 165, 26));
		deck.add(new Card("Fairy", "Omi", 145, 21));
		deck.add(new Card("Dragon", "Puddles", 170, 34));
		deck.add(new Card("Ghost", "Quarrel", 151, 29));
		deck.add(new Card("Fairy", "Raven", 168, 32));
		deck.add(new Card("Dragon", "Surge", 128, 27));
		deck.add(new Card("Ghost", "Takiru", 140, 26));
		deck.add(new Card("Fairy", "Ustelia", 163, 47));
		deck.add(new Card("Dragon", "Verwyn", 145, 25));
		deck.add(new Card("Ghost", "Wyverin", 158, 32));
		deck.add(new Card("Fairy", "Xios", 155, 27));
		deck.add(new Card("Dragon", "Yora", 159, 44));
		deck.add(new Card("Ghost", "Zulu", 125, 46));
	}

	/**
		If @param action is:
			SWAP: swaps the current player's cards then increments the turns.
			ACTION: The attacking cards deals damage to the target card.
				If the target card's health falls on or below 0, it checks the top 2 cards on the deck and deals the card with the highest determining product and returns the other card to the deck.
				After dealing, it gives the attacking player a token and if their tokens reach 3, they are announced the winner and the game ends.
				Otherwise, the number of turns increments and the game continues.
			HEAL: heals the active card.
		   		If they take a penalty instead, the turn goes to the next player.
				If they don't have enough tokens, they keep their turn to choose another action.
			STATUS: display the current player's hand.
		@return the output of the actions.
	 */
	public String play(String action) {
		String output = "";
		Player playerAttack;
		Player playerTarget;
		if (turn % 2 == 1) {
			playerAttack = player1;
			playerTarget = player2;
		} else {
			playerAttack = player2;
			playerTarget = player1;
		}
		Card attackCard = playerAttack.getActiveCard();
		Card targetCard = playerTarget.getActiveCard();
		if (action.equals("SWAP")) {
			output += "   " + playerAttack.getName() + " swaps out " + attackCard.getName() + "...\n";
			output += playerAttack.swap() + "\n";
		} else if (action.equals("ATTACK")) {
			output += "   " + playerAttack.getName() + " attacks with " + attackCard.getName() + ".\n";
			output += dealDamage(attackCard, targetCard);
			if (targetCard.getHealth() <= 0) {
				output += "   " + playerTarget.getName() + " discards " + targetCard.getName() + ".\n";
				playerTarget.discard();
				Card cardToDeal;
				Card cardToReturn;
				Card card1 = deck.get(0);
				Card card2 = deck.get(1);
				if (card1.getHealth() * card1.getPower() > card2.getHealth() * card2.getPower()) {
					cardToDeal = card1;
					cardToReturn = card2;
				} else {
					cardToDeal = card2;
					cardToReturn = card1;
				}
				output += "   " + playerTarget.getName() + " draws " + cardToDeal.getName() + ".\n";
				playerTarget.drawCard(cardToDeal);
				deck.remove(cardToDeal);
				deck.remove(cardToReturn);
				deck.add(cardToReturn);
				playerAttack.claimToken();
				output += "   " + playerAttack.getName() + " gets a token!\n";
				if (playerAttack.getTokens() == 3) {
					winner = true;
				}
				if (hasWinner()) {
					output += playerAttack.getName() + " wins!!!\n\n";
					output += gameReport();
				}
			}
		} else if (action.equals("HEAL")) {
			output += playerAttack.heal();
			if (output.contains("Not enough tokens. Choose another action.")
					|| output.contains("No more heals. Choose another action.")) {
				turn--;
			}
		} else if (action.equals("STATUS")) {
			output += playerAttack.statusReport();
			output += "\nTokens: " + playerAttack.getTokens();
			output += "\nHeal Chances: " + playerAttack.getHealChance() + "\n";
			turn--;
		}
		turn++;
		return output;
	}

	/**
		@return true if @param type1 is resistant to @param type2.
	 */
	public boolean checkResistance(String type1, String type2) {
		String target = type1.toUpperCase();
		String attack = type2.toUpperCase();
		boolean resistance = false;
		if (target.equals("DRAGON") && attack.equals("GHOST")) {
			resistance = true;
		} else if (target.equals("GHOST") && attack.equals("FAIRY")) {
			resistance = true;
		} else if (target.equals("FAIRY") && attack.equals("DRAGON")) {
			resistance = true;
		}
		return resistance;
	}

	/**
		@return true if @param type1 is weak to @param type2.
		(ADD ON): Includes human cards which are weak to every other type, but all types are also weak to humans.
	 */
	public boolean checkWeakness(String type1, String type2) {
		String target = type1.toUpperCase();
		String attack = type2.toUpperCase();
		boolean weakness = false;
		if (target.equals("DRAGON") && (attack.equals("FAIRY") || attack.equals("HUMAN"))) {
			weakness = true;
		} else if (target.equals("FAIRY") && (attack.equals("GHOST") || attack.equals("HUMAN"))) {
			weakness = true;
		} else if (target.equals("GHOST") && (attack.equals("DRAGON") || attack.equals("HUMAN"))) {
			weakness = true;
		} else if (target.equals("HUMAN")
				&& (attack.equals("DRAGON") || attack.equals("FAIRY") || attack.equals("GHOST"))) {
			weakness = true;
		}
		return weakness;
	}

	/**
		While either player's hands is not full, deals a card depending on the turn, then increments the turns.
		If random is true, deals a random card from the deck instead.
		(ADD ON): If random is true, a random card will instead be dealt.
	 */
	public String dealCard() {
		String output = "";
		while (!player1.handIsFull() || !player2.handIsFull()) {
			Player player;
			if (turn % 2 == 1) {
				player = player1;
			} else {
				player = player2;
			}
			if (player.handIsFull()) {
				output += player.getName() + "'s hand is full.\n";
			} else if (random) {
				int randomIndex = (int) (Math.random() * deck.size());
				Card cardToDeal = deck.remove(randomIndex);
				player.drawCard(cardToDeal);
				output += player.getName() + " draws " + cardToDeal.getName() + ".\n\n";
				turn++;
			} else if (!random) {
				Card cardToDeal = deck.remove(0);
				player.drawCard(cardToDeal);
				output += player.getName() + " draws " + cardToDeal.getName() + ".\n\n";
				turn++;
			}
		}
		return output;
	}

	/**
		Gets the type of @param inPlay and @param target, determines if the target is either weak or resistant, deals damage to the target card, and @return the output.
	 */
	public String dealDamage(Card inPlay, Card target) {
		String output = "";
		int power = inPlay.getPower();
		String targetType = target.getType();
		String attackType = inPlay.getType();
		if (checkWeakness(targetType, attackType)) {
			output += "      " + targetType + " is weak to " + attackType + ".\n";
			power *= 2;
		} else if (checkResistance(targetType, attackType)) {
			output += "      " + targetType + " is resistant to " + attackType + ".\n";
			power /= 2;
		}
		target.takeDamage(power);
		output += "   " + inPlay.getName() + " deals " + power + " damage on " + target.getName() + ".\n";
		output += "   " + target.getName() + " has " + target.getHealth() + " health left.\n";
		return output;
	}

	/**
		@return the value of winner.
	 */
	public boolean hasWinner() {
		return winner;
	}

	/**
		@return the number of turns so far and the status of both players.
	 */
	public String gameReport() {
		String gameReport = "---=== GAME SUMMARY ===---" + "\n" + "This game lasted " + turn + " turns.\n"
				+ player1.statusReport() + "\n" + player2.statusReport();
		return gameReport;
	}
}