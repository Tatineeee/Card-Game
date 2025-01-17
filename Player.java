/**
    The Player class initializes the player's name, token count, hand size, card count, and their hand status.
	It allows the players to draw, swap, discard, find, and heal cards.
	It can return these values and a status report of the player's cards status.

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

public class Player {
	private String name;
	private int tokens;
	private Card[] hand;
	private int cardCount;
	private boolean full;
	private int healChance;

	/**
		Initializes the player with their name @param n, starting tokens, hand size, card count, and if their hand is full.
	 */
	public Player(String n) {
		name = n;
		tokens = 0;
		hand = new Card[5];
		cardCount = 0;
		full = false;
		healChance = 3;
	}

	/**
		If the hand is not full, deals the card @param c to the player hand then increases the card count until their hand is full.
	 */
	public void drawCard(Card c) {
		if (!handIsFull()) {
			hand[cardCount] = c;
			cardCount++;
			if (cardCount == 5) {
				full = true;
			}
		}
	}

	/**
		Discards the active card in the player's hand and moves the other cards up until the last slot is empty.
	 */
	public void discard() {
		for (int i = 0; i < cardCount - 1; i++) {
			hand[i] = hand[i + 1];
		}
		cardCount--;
		hand[cardCount] = null;
		full = false;
	}

	/**
		Swaps the active card with the card with the highest determining product (HDP) found from {@link #findCard()}, then @return a message based on the condition of the hand.
	 */
	public String swap() {
		String output = "   " + getName() + " has no other card to swap with. Turn forfeited.";
		if (cardCount > 1) {
			int swapIndex = findCard();
			Card activeCard = getActiveCard();
			hand[0] = hand[swapIndex];
			hand[swapIndex] = activeCard;
			output = "   " + hand[0].getName() + " is now active with " + hand[0].getHealth() + " health.";
		}
		return output;
	}

	/**
		Finds the card with the highest HDP, not including the active card.
	 */
	private int findCard() {
		int highestDeterminingProduct = 0;
		int indexHDP = -1;
		for (int i = 1; i < cardCount; i++) {
			int HDP = hand[i].getHealth() * hand[i].getPower();
			if (highestDeterminingProduct < HDP) {
				indexHDP = i;
				highestDeterminingProduct = HDP;
			}
		}
		return indexHDP;
	}

	/**
		(ADD ON): Heals the active card for 50 health if they have atleast 1 token and 1 heal chance, then decrements their token count and heal chances.
		If their currenth health or health after healing is more than 200, they are penalized and lose 100 health, and still lose 1 token and heal chance.
		If they don't have enough tokens or if they don't have anymore heal chances, they are asked to choose another action.
	 */
	public String heal() {
		String output = "";
		int newHealth = getActiveCard().getHealth() + (50);
		if (getHealChance() > 0) {
			if (getTokens() > 0) {
				if ((getActiveCard().getHealth() + 50 <= 200 || newHealth + 50 <= 200)) {
					newHealth = getActiveCard().heal(50);
					output = "   " + getName() + " heals " + getActiveCard().getName() + " for 50 health.\n";
					output += "   " + getActiveCard().getName() + " now has " + newHealth + " health.\n";
					tokens--;
					healChance--;
					output += "   Remaining tokens: " + getTokens() + "\n";
					output += "   Remaining heals: " + getHealChance() + "\n";
				} else if ((getActiveCard().getHealth() + 50 > 200 || newHealth + 50 > 200)) {
					output = "   Don't be greedy. " + getActiveCard().getName() + " loses 100 health.\n";
					getActiveCard().takeDamage(100);
					output += "   " + getActiveCard().getName() + " now has " + getActiveCard().getHealth()
							+ " health.\n";
					tokens--;
					healChance--;
					output += "   Remaining tokens: " + getTokens() + "\n";
					output += "   Remaining heals: " + getHealChance() + "\n";
				}
			} else {
				output += "   Not enough tokens. Choose another action.\n";
			}
		} else {
			output += "   No more heals. Choose another action.\n";
		}
		return output;
	}

	/**
		Increments the player's token count.
	 */
	public void claimToken() {
		tokens++;
	}

	/**
		@return the player's active card.
	 */
	public Card getActiveCard() {
		return hand[0];
	}

	/**
		@return the player's name.
	 */
	public String getName() {
		return name;
	}

	/**
		@return the player's token count.
	 */
	public int getTokens() {
		return tokens;
	}

	/**
		@return the status of the player's hand being full.
	 */
	public boolean handIsFull() {
		return full;
	}

	/**
		@return the how many heals a player has left.
	 */
	public int getHealChance() {
		return healChance;
	}

	/**
		@return the status of the player's cards in hand.
	 */
	public String statusReport() {
		String statusReport = getName().toUpperCase() + "\n";
		for (int i = 0; i < cardCount; i++) {
			statusReport += String.format("%11s : %3d\n", hand[i].getName(), hand[i].getHealth());
		}
		return statusReport;
	}
}