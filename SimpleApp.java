/**
    The SimpleApp class is how the GUI is launched. 
    It uses an area of 800x600 for the window size.
    Sets up the GUI and the button listeners.
 
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

public class SimpleApp {
	public static void main(String[] args) {
		SimpleGUI app = new SimpleGUI(800, 600);
		app.setupSimpleApp();
		app.setUpButtonListeners();
	}
}