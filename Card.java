/**
    The Card class initializes the card's type, name, health, and power.
    It can return these values and updates the card's current health based on the damage dealt or healing done.
    
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

public class Card {
    private String type;
    private String name;
    private int health;
    private int power;

    /**
        Allows the the card to have the attributes type, name, health, and power using @param t, @param n, @param h, and @param p respectively.
     */
    public Card(String t, String n, int h, int p) {
        type = t;
        name = n;
        health = h;
        power = p;
    }

    /**
        @return the name of the card.
     */
    public String getName() {
        return name;
    }

    /**
        @return the type of the card.
     */
    public String getType() {
        return type;
    }

    /**
        @return the current health of the card.
     */
    public int getHealth() {
        return health;
    }

    /**
        @return the power of the card.
     */
    public int getPower() {
        return power;
    }

    /**
        Updates the card's current health based on the damage dealt from @param d.
     */
    public void takeDamage(int d) {
        health -= d;
    }

    /**
        (ADD ON): Heals the card for whatever the value of @param d is, then @return the new health value.
     */
    public int heal(int h) {
        health += h;
        return health;
    }
}