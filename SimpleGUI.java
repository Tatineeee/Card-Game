import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
    The SimpleGUI class initializes all the elemented needed for a simple GUI for the Card Game.
    It initializes the buttons, the frame, labels, and output area.
    It also has a method for if the player chooses to reset the game.

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

public class SimpleGUI {
    private JFrame frame;
    private JLabel player1Field;
    private JLabel player2Field;
    private JLabel actions;
    private JTextField player1Name;
    private JTextField player2Name;
    private JButton start;
    private JButton startRandom;
    private JToggleButton startHuman;
    private JButton attack;
    private JButton swap;
    private JButton heal;
    private JButton status;
    private JButton newGame;
    private JTextArea output;
    private int width;
    private int height;
    private GameMaster gameMaster;
    private Player player1;
    private Player player2;
    private boolean random;
    private boolean human;

    /**
    	Creates the frame, buttons, labels, text fields, and output area, as well as taking the values of @param w and @param h for the width and height.
     */
    public SimpleGUI(int w, int h) {
        frame = new JFrame();
        frame.setResizable(false);
        player1Field = new JLabel("Player 1: ");
        player2Field = new JLabel("Player 2: ");
        actions = new JLabel("What will you do?");
        player1Name = new JTextField(10);
        player2Name = new JTextField(10);
        start = new JButton("Start Game");
        startRandom = new JButton("Start Game: Random");
        startHuman = new JToggleButton("Start Game: Human");
        attack = new JButton("Attack");
        swap = new JButton("Swap");
        heal = new JButton("Heal");
        status = new JButton("Show Player Status");
        newGame = new JButton("New Game");
        output = new JTextArea(30, 55);
        output.setEditable(false);
        width = w;
        height = h;
        human = false;
    }

    /**
        Sets up the flow layout and container for the GUI.
     */
    public void setupSimpleApp() {
        Container cp = frame.getContentPane();
        FlowLayout flow = new FlowLayout();
        cp.setLayout(flow);
        frame.setSize(width, height);
        frame.setTitle("Card Game");
        cp.add(player1Field);
        cp.add(player1Name);
        cp.add(player2Field);
        cp.add(player2Name);
        cp.add(start);
        cp.add(startRandom);
        cp.add(startHuman);
        cp.add(actions);
        cp.add(attack);
        cp.add(swap);
        cp.add(heal);
        cp.add(status);
        cp.add(newGame);
        cp.add(output);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
        Sets up the buttons, the button listeners, and the action listeners. Controls what the buttons do in relation to the game.
     */
    public void setUpButtonListeners() {
        resetGame();
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Object action = ae.getSource();
                if (startHuman.isSelected()) {
                    human = true;
                } else {
                    human = false;
                }
                if (action == start || action == startRandom) {
                    String play1 = player1Name.getText();
                    String play2 = player2Name.getText();
                    if (action == startRandom) {
                        random = true;
                    } else if (action == start) {
                        random = false;
                    }
                    player1 = new Player(play1);
                    player2 = new Player(play2);
                    gameMaster = new GameMaster(player1, player2, random, human);
                    output.setText("Welcome, " + player1.getName() + " and " + player2.getName() + "!\n");
                    output.append("The game begins.\n\n");
                    output.append(gameMaster.dealCard() + "\n");
                    start.setEnabled(false);
                    startRandom.setEnabled(false);
                    startHuman.setEnabled(false);
                    attack.setEnabled(true);
                    swap.setEnabled(true);
                    heal.setEnabled(true);
                    status.setEnabled(true);
                } else if (action == attack) {
                    output.setText(gameMaster.play("ATTACK") + "\n");
                } else if (action == swap) {
                    output.setText(gameMaster.play("SWAP") + "\n");
                } else if (action == heal) {
                    output.setText(gameMaster.play("HEAL") + "\n");
                } else if (action == status) {
                    output.setText(gameMaster.play("STATUS") + "\n");
                } else if (action == newGame) {
                    resetGame();
                    output.append("Starting a new game...");
                }
                if (gameMaster.hasWinner()) {
                    attack.setEnabled(false);
                    swap.setEnabled(false);
                    heal.setEnabled(false);
                    status.setEnabled(false);
                    newGame.setEnabled(true);
                    output.append("Game Over!\n\nPlay again? Click New Game to play again.");
                }
            }
        };
        start.addActionListener(buttonListener);
        startRandom.addActionListener(buttonListener);
        attack.addActionListener(buttonListener);
        swap.addActionListener(buttonListener);
        heal.addActionListener(buttonListener);
        status.addActionListener(buttonListener);
        newGame.addActionListener(buttonListener);
    }

    /**
        Resets values if the player wants to start a new game.
     */
    private void resetGame() {
        output.setText("");
        start.setEnabled(true);
        startRandom.setEnabled(true);
        startHuman.setEnabled(true);
        attack.setEnabled(false);
        swap.setEnabled(false);
        heal.setEnabled(false);
        status.setEnabled(false);
        newGame.setEnabled(false);
        player1 = null;
        player2 = null;
        gameMaster = null;
        random = false;
    }
}