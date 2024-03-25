package snoopy;

import snoopy.Controller.JeuDeBaseController;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Main extends Object {

    public static final int Stage_width = 1440;//75% of 1920
    public static final int Stage_height = 810;//75% of 1080 //coucou

    public static void main(String[] args) {
        // Create an instance of the Main class and call its start method
        Main main = new Main();
        try {
            main.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {
        // Create a Swing window
        Frame frame = new Frame();
        frame.setSize(Stage_width, Stage_height);
        frame.setResizable(false);
        frame.setTitle("Snoopy");

        // Create a controller to handle keyboard events
        JeuDeBaseController controller = new JeuDeBaseController();

        // Add the controller as an event filter to the window
        frame.addKeyListener(controller);

        // Display the window
        frame.setVisible(true);
    }
}
