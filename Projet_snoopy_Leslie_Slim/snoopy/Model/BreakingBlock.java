package snoopy.Model;

public class BreakingBlock extends Entity {
    private Board board;
    private Snoopy noop;
    private boolean spacePressed = false;

    /**
     * Constructor of BreakingBlock
     * @param x position of the block on the axis up to down
     * @param y position of the block on the axis left to right
     * @param board the board of the game
     * @param noop snoopy
     */
    public BreakingBlock(int x, int y, Board board, Snoopy noop) {
        super(x, y);
        this.board = board;
        this.noop = noop;
    }
    /**
     * This method manages the actions of the breaking block and its interactions with snoopy
     */
    public void Action() {
        if(( X ==noop.getX() + 1 && Y== noop.getY() && noop.getDirection() == Direction.S)
                || (X ==noop.getX() - 1 && Y== noop.getY()  && noop.getDirection()==Direction.N)
                || (X ==noop.getX()  && Y== noop.getY() +1 && noop.getDirection() == Direction.E)
                || (X ==noop.getX()  && Y== noop.getY() -1 && noop.getDirection() == Direction.O)){
            if(spacePressed ){
                board.getBoard()[X][Y] =board.getBoard()[X][Y].replace("1", "0");// "0";
            }
        }
    }

    public void setSpacePressed(boolean b){
        this.spacePressed = b;
        this.Action();

    }

}
