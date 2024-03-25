package snoopy.Model;

public class InvincibleBlock extends Entity {

    private Board board;
    private Snoopy noop;

    /**
     * Constructor of InvincibleBlock
     * @param x position of the block on the axis up to down
     * @param y position of the block on the axis left to right
     * @param board the board of the game
     * @param noop snoopy
     */
    public InvincibleBlock(int x, int y, Board board, Snoopy noop) {
        super(x, y);
        this.board = board;
        this.noop = noop;
    }
    /**
     * This method manages the actions of the invincible block and its interactions with snoopy
     */
    protected void Action() {
    }

}
