import javax.swing.*;
import java.awt.*;
import java.io.File;

public class JeuView extends JPanel {
    public final static double CELL_WIDTH = 58.0;
    private final int row = 10;
    private final int column = 10;
    private JLabel[][] matrixLabels;

    public JeuView() {
        initialiseGrassBackGround();
        initialize();
    }

    public void initialiseGrassBackGround() {
        setLayout(null);
        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < column - 1; j++) {
                JLabel imageView = new JLabel();
                imageView.setBounds((int) (j * CELL_WIDTH), (int) (i * CELL_WIDTH), (int) CELL_WIDTH, (int) CELL_WIDTH);
                ImageIcon icon = new ImageIcon(new File("src/main/resources/img/Herbe1.png").toURI().toString());
                imageView.setIcon(icon);
                add(imageView);
            }
        }
    }

    public void initialize() {
        matrixLabels = new JLabel[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                JLabel imageView = new JLabel();
                imageView.setBounds((int) (j * CELL_WIDTH), (int) (i * CELL_WIDTH), (int) CELL_WIDTH, (int) CELL_WIDTH);
                ImageIcon icon = new ImageIcon(new File("src/main/resources/img/empty.png").toURI().toString());
                imageView.setIcon(icon);
                matrixLabels[i][j] = imageView;
                add(imageView);
            }
        }
        drawGreen();
        drawWall();
    }

    private void drawGreen() {
        for (int i = 2; i < row - 2; i++) {
            for (int j = 2; j < column - 2; j++) {
                JLabel imageView = new JLabel();
                imageView.setBounds((int) (j * CELL_WIDTH), (int) (i * CELL_WIDTH), (int) CELL_WIDTH, (int) CELL_WIDTH);
                ImageIcon icon = new ImageIcon(new File("src/main/resources/img/Herbe1.png").toURI().toString());
                imageView.setIcon(icon);
                add(imageView);
            }
        }
    }

    public void drawWall() {
        // Code for drawing walls
    }
}
