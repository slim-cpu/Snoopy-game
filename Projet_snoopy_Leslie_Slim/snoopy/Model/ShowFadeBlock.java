package java.snoopy.Model;

public class ShowFadeBlock extends Entity {

    private boolean disappeared;
    public Board board;

    private Board MVT;
    
    private snoopy.Model.Snoopy noop;

    /**
     * Constructor of ShowFadeBlock
     * @param i position of the block on the axis up to down
     * @param j position of the block on the axis left to right
     * @param b the board of the game
     * @param noop snoopy
     */
    public ShowFadeBlock( int i, int j,Board b, snoopy.Model.Snoopy noop) {
        super(i, j);
        this.board = b;
        this.noop = noop;
        this.disappeared = false;
    }
    /**
     * This method manages the actions of the ShowFade block and its interactions with snoopy
     */
    protected void Action() {
        System.out.println("fade "+toString());
        /*
        if(noop.getX() -1 ==  X  && noop.getY() == Y){
            board.getBoard()[X][Y] = board.getBoard()[X][Y].replace("5", "0");
            disappeared = true;
        } if (noop.getX() +1 ==  X  && noop.getY() == Y){
            board.getBoard()[X][Y] = board.getBoard()[X][Y].replace("5", "0");
            disappeared = true;
        } if(noop.getX()  ==  X && noop.getY()-1 == Y){
            board.getBoard()[X][Y] = board.getBoard()[X][Y].replace("5", "0");
            disappeared = true;
        } if(noop.getX()  ==  X && noop.getY()+1 == Y){
            board.getBoard()[X][Y] = board.getBoard()[X][Y].replace("5", "0");
            disappeared = true;
        }*/
        if((noop.getX() -1 ==  X  && noop.getY() == Y) || (noop.getX() +1 ==  X  && noop.getY() == Y) || (noop.getX()  ==  X && noop.getY()-1 == Y) || (noop.getX()  ==  X && noop.getY()+1 == Y)){
            board.getBoard()[X][Y] = board.getBoard()[X][Y].replace("5", "0");
            disappeared = true;
            System.out.println("Fade Block : block disappear fadblock x:"+X+" y:"+Y+"; noop.getX()"+noop.getX()+" noop.getY()"+noop.getY());

        }

    }
    /*
    public void fade() {
        System.out.println("fade"+toString());
        boolean disappear = false;
        if(board.board[noop.getX()][noop.getY()] == board.board[X - 1][Y]){
            board.board[X][Y] = 0;
            disappear = true;
        } if (board.board[noop.getX()][noop.getY()] == board.board[X + 1][Y]){
            board.board[X][Y] = 0;
            disappear = true;
        } if(board.board[noop.getX()][noop.getY()] == board.board[X][Y - 1]){
            board.board[X][Y] = 0;
            disappear = true;
        } if(board.board[noop.getX()][noop.getY()] == board.board[X][Y + 1]){
            board.board[X][Y] = 0;
            disappear = true;
        }
        if(disappear){
            System.out.println("Fade Block : block disappear fadblock x:"+X+" y:"+Y+"; noop.getX()"+noop.getX()+" noop.getY()"+noop.getY());
        }

    }
     */

    @Override
    public String toString() {
        return "x:"+this.X+" y:"+this.Y;
    }
}
