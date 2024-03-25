package snoopy.Model;

public class YellowBird extends Entity{
    private Board board;
    private Snoopy noop;
    private boolean birdIsPickedUp;

    /**
     * Constructor of InvincibleBlock
     * @param x position of the bird on the axis up to down
     * @param y position of the bird on the axis left to right
     * @param board the board of the game
     * @param noop snoopy
     */
    public YellowBird(int x, int y, Board board, Snoopy noop) {
        super(x,y);
        this.board = board;
        this.noop = noop;
        this.birdIsPickedUp = false;
    }

    /**
     * Empty constructor, doesn't initialize attributes
     */
    public void yellowBird() {

    }

    /**
     * This method sets the state of the attribute birdIsPickedUp to true once it has been picked by Snoopy
     * @param birdIsPickedUp the new state of birdIsPickedUp
     */
    public void setBirdIsPickedUp(boolean birdIsPickedUp) {
        this.birdIsPickedUp = birdIsPickedUp;
    }
    /**
     * This method manages the interactions of the yellow bird with snoopy
     */
    protected void Action() {
        if (X == noop.getX() && Y == noop.getY()) {

            board.getBoard()[X][Y] = board.getBoard()[X][Y].replace("9O", "0");
            board.getBoard()[X][Y] = board.getBoard()[X][Y].replace("9E", "0");
            birdIsPickedUp = true;
            //System.out.println("Snoopy is on a yellow bird, x:"+X+" y:"+Y+" , PV left"+noop.getLife());
        }
    }

    /**
     * @return if the yellow bird has been picked up by snoopy
     */
    public boolean IsPickedUp() {
        return birdIsPickedUp;
    }
}
