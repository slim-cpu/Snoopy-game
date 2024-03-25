package snoopy.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Button jouerB;
    private Panel firstPaneAnchor;

    private Panel scoresAnchorPane;
    private List listviewScores;
    private Label LabelScore;
    private Button retourBChargerPartie;
    private Button validerBChargerPartie;

    private Button validerBmdp;
    private Button retourBmdp;
    private TextArea motdepasseTextArea;
    private Panel motdepasseAnchor;

    private Panel chargerUnePartieAnchor;
    private Button retourB;
    private Button validerB;
    private TextArea chargerUnePartieTextArea;

    /**
     * Constructor
     */
    public Controller(){}

    /**
     * This method tells us that the button "Charger une partie" has been clicked
     * @param actionEvent
     * @throws IOException
     */
    public void retourClickedChargerPartie(ActionEvent actionEvent) throws IOException {
        // Implementer le changement de pane
    }

    /**
     * This method tells us that the button "Valider" in "Charger une Partie" has been clicked
     * @param actionEvent
     */
    public void validerClickedChargerPartie(ActionEvent actionEvent) {
        String s = chargerUnePartieTextArea.getText();
        System.out.println(s);
    }

    /**
     * This method tell us that the button "Mot de passe" has been clicked
     * @param actionEvent
     * @throws IOException
     */
    public void retourClickedmdp(ActionEvent actionEvent) throws IOException {
        // Implementer le changement de pane
    }

    /**
     * This method tells us that the button "Valider" in "Mot de passe" has been clicked
     * @param actionEvent
     */
    public void validerClickedmdp(ActionEvent actionEvent) {
        String s = motdepasseTextArea.getText();
        System.out.println(s);
    }

    /**
     * This method is used when we click on the button "Jouer"
     * @throws IOException
     */
    public void JouerCliked() throws IOException {
        // Implementer le changement de pane
    }

    /**
     * This method is used when we click on the button "Retour"
     * @param actionEvent
     * @throws IOException
     */
    public void retourClicked(ActionEvent actionEvent) throws IOException {
        // Implementer le changement de pane
    }

    /**
     * This method allows us to go from a pane to another one using a loader
     * A loader allows fxml files to be used in java
     * @param fxmlFileName
     * @param callingAnchor
     * @throws IOException
     */
    public void changePane(String fxmlFileName, Panel callingAnchor) throws IOException {
        // Implementer le changement de pane
    }

    /**
     * This method allows us to load scores from a text file when the fxml file is intialized in the code
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Implementer l'initialisation
    }
}
