package snoopy.Model;

import javax.net.ssl.SNIHostName;

public class Snoopy extends Entity {
    private int PV = 3;//when 0 game over
    private Direction direction;
    private Board snoopyBoard ;

    /**
     *
     * @param X the position of snoopy on the up to down axis
     * @param Y the position of snoopy on the left to right axis
     * @param b the board of the game
     * @param d the direction of snoopy
     */
    public Snoopy(int X, int Y,Board b, Direction d) {
        super(X, Y);
        this.snoopyBoard = b;
        this.direction = d;
        // this.snoopyBoard = new Board();
    }

    /**
     * This method sets the direction of Snoopy
     * @param d the new direction
     */
    public void setDirection(Direction d) {
        this.direction = d;
    }

    /**
     * @return the current direction of snoopy
     */
    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        String r = "x:" + this.X + " y:" + this.Y + "d:";
        return r;
    }

    /**
     * This method removes a life from snoopy
     * @param i nulber of lives lost
     */
    public void loseLife(int i) {
        this.PV -= i;
    }

    /**
     * @return the number of snoopy's lives left
     */
    public int getLife() {
        return PV;
    }

    /**
     * This method manages the interactions of snoopy
     */
    public void Action() {

        //touch trap
        //touch ball
        //touch bird

    }
}
