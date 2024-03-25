package snoopy.Model;

public class TreadMillBlock extends Entity {
    private Board board;
    private Snoopy noop;
    private Direction direction;

    /**
     * Constructor of TreadMillBlock
     * @param X position of the block on the axis up to down
     * @param Y position of the block on the axis left to right
     * @param b the board of the game
     * @param noop snoopy
     */
    public TreadMillBlock(int X, int Y,Board b , Snoopy noop , Direction d) {
        //localisation du bloc
        super(X, Y);
        this.noop = noop;
        this.direction = d;
        this.board = b;
    }

    /**
     * This method manages the actions of the TreadMill block and its interactions with snoopy
     */
    public void Action(){

        if (X == noop.getX() && Y == noop.getY() && direction == Direction.E) {//move noop to the right

            board.getBoard()[X][Y] = board.getBoard()[X][Y].replace("8"+noop.getDirection(), "");
            board.getBoard()[X][Y+1] += "8"+Direction.E;
            noop.setDirection(direction);
            noop.setY(noop.getY() + 1);
        } else if (X == noop.getX() && Y == noop.getY() && direction == Direction.O) {//move noop to the left
            board.getBoard()[X][Y] = board.getBoard()[X][Y].replace("8"+noop.getDirection(), "");
            board.getBoard()[X][Y-1] += "8"+Direction.O;
            noop.setDirection(direction);
            noop.setY(noop.getY() - 1);
        } else if (X == noop.getX() && Y == noop.getY() && direction == Direction.N) {//move noop to the top
            board.getBoard()[X][Y] = board.getBoard()[X][Y].replace("8"+noop.getDirection(), "");
            board.getBoard()[X-1][Y] += "8"+Direction.N;
            noop.setDirection(direction);
            noop.setX(noop.getX() - 1);
        } else if (X == noop.getX() && Y == noop.getY() && direction == Direction.S) {//move noop to the bottom
            board.getBoard()[X][Y] = board.getBoard()[X][Y].replace("8"+noop.getDirection(), "");
            board.getBoard()[X+1][Y] += "8"+Direction.S;
            noop.setDirection(direction);
            noop.setX(noop.getX() + 1);
        }

    }

    @Override
    public String toString() {
        String r = "x:" + this.X + " y:" + this.Y ;
        return r;
    }
}
