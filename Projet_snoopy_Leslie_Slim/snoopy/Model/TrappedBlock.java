package snoopy.Model;

public class TrappedBlock extends Entity {
    private Snoopy noop;
    private Board board;
    boolean triggered = false;

    /**
     * Constructor of TrappedBlock
     * @param x position of the block on the axis up to down
     * @param y position of the block on the axis left to right
     * @param b the board of the game
     * @param noop snoopy
     */
    public TrappedBlock(int x, int y, Board b, Snoopy noop) {
        super(x, y);
        this.noop = noop;
        board = b;
    }

    /**
     * This method manages the actions of the TrappedBlock block and its interactions with snoopy
     */
    protected void Action() {
        //check if snoopy is on a trapped block
        if (X == noop.getX() && Y == noop.getY() && !triggered) {
            noop.loseLife(1);
            System.out.println("Snoopy is on a trapped block, x:"+X+" y:"+Y+" , PV left"+noop.getLife());
            board.getBoard()[X][Y] = board.getBoard()[X][Y].replace("3", "0");
            triggered = true;
        }
    }

    /**
     * @return if the trapped block has been triggered
     */
    public boolean triggered() {
        return triggered;
    }
}
