
package GamePkg;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Classe représentant une balle dans le jeu.
 * @see Entity
 */
public class Balle extends Entity {
    int vitesseX;
    int vitesseY;

    boolean mouvementImpossible;
    boolean demarrage;
    Timer vitesse = new Timer();

    /**
     * Constructeur de la classe Balle.
     */
    public Balle() {
        animation = false;
        collision = false;
        identifiant = 7;
        deplacement = true;
        x = 0;
        y = 0;
        vitesseX = 1;
        vitesseY = 1;
        mouvementImpossible = true;
        demarrage = false;
    }

    /**
     * Méthode pour obtenir l'état de collision de la balle.
     */
    @Override
    public boolean estEnCollision() {
        return collision;
    }

    /**
     * Méthode pour définir si la balle peut être poussée.
     */
    @Override
    void setPoussable(boolean poussable) {

    }

    /**
     * Méthode pour vérifier si la balle peut être poussée.
     */
    @Override
    boolean estPoussable() {
        return false;
    }

    /**
     * Méthode pour obtenir l'identifiant de téléportation.
     */
    @Override
    int getIdentifiantTeleportation() {
        return 0;
    }

    /**
     * Méthode pour mettre à jour la position de la balle.
     * @param map la carte du jeu
     * @param entities la liste des entités du jeu
     */
    @Override
    void mettreAJourPosition(int[][] map, ArrayList<Entity> entities) {
        if (mouvementImpossible && demarrage) {
            dernierX = x;
            dernierY = y;
            x += vitesseX;
            y += vitesseY;
            if (x + vitesseX > 9 || x + vitesseX < 0) {
                vitesseX = -vitesseX;
            }
            if (y + vitesseY > 19 || y + vitesseY < 0) {
                vitesseY = -vitesseY;
            }
            if (map[x + vitesseX][y] == 1 || map[x + vitesseX][y] == 2 || map[x + vitesseX][y] == 3 || map[x + vitesseX][y] == 4) {
                vitesseX = -vitesseX;
            }
            if (map[x][y + vitesseY] == 1 || map[x][y + vitesseY] == 2 || map[x][y + vitesseY] == 3 || map[x][y + vitesseY] == 4) {
                vitesseY = -vitesseY;
            }
            if (map[x + vitesseX][y + vitesseY] == 1 || map[x + vitesseX][y + vitesseY] == 2 || map[x + vitesseX][y + vitesseY] == 3 || map[x + vitesseX][y + vitesseY] == 4) {
                vitesseX = -vitesseX;
                vitesseY = -vitesseY;
            }
            mouvementImpossible = false;
            vitesse.schedule(new TimerTask() {
                @Override
                public void run() {
                    mouvementImpossible = true;
                }
            }, 250);
        }
    }

    /**
     * Méthode pour mettre à jour la position de la balle pour l'algorithme d'IA.
     * @param map la carte du jeu
     */
    void mettreAJourPositionIA(int[][] map) {
        dernierX = x;
        dernierY = y;
        x += vitesseX;
        y += vitesseY;
        if (x + vitesseX > 9 || x + vitesseX < 0) {
            vitesseX = -vitesseX;
        }
        if (y + vitesseY > 19 || y + vitesseY < 0) {
            vitesseY = -vitesseY;
        }
        if (map[x + vitesseX][y] == 1 || map[x + vitesseX][y] == 2 || map[x + vitesseX][y] == 3 || map[x + vitesseX][y] == 4) {
            vitesseX = -vitesseX;
        }
        if (map[x][y + vitesseY] == 1 || map[x][y + vitesseY] == 2 || map[x][y + vitesseY] == 3 || map[x][y + vitesseY] == 4) {
            vitesseY = -vitesseY;
        }
        if (map[x + vitesseX][y + vitesseY] == 1 || map[x + vitesseX][y + vitesseY] == 2 || map[x + vitesseX][y + vitesseY] == 3 || map[x + vitesseX][y + vitesseY] == 4) {
            vitesseX = -vitesseX;
            vitesseY =
