package snoopy.Model;

public class PushingBlock extends Entity {

    private Board board;
    private Snoopy noop;
    private Direction direction;
    private boolean pushed = false;

    /**
     * Constructor of PushingBlock
     * @param i position of the block on the axis up to down
     * @param j position of the block on the axis left to right
     * @param b the board of the game
     * @param noop snoopy
     * @param d the direction of teh pushing block
     */
    public PushingBlock( int i, int j,Board b, Snoopy noop, Direction d){
        //localisation du bloc
        super(i,j);
        this.board = b;
        this.noop = noop;
        //direction du bloc
        this.direction = d;
    }

    /**
     * This method allows us to push the pushing block to the right
     */
    private void isPushedToTheRight(){

                if(noop.getX() == X
                        && noop.getY()==Y-1
                        && board.getBoard()[X][Y+1].contains("0")
                        && direction == noop.getDirection() && direction== Direction.E
                        && Y != 20
                ){

                    board.getBoard()[X][Y] = board.getBoard()[X][Y].replace("2E", "0");
                    board.getBoard()[X][Y+1] = board.getBoard()[X][Y+1].replace("0", "4");

                    Y++;
                    pushed = true;


                }
    }

    /**
     * This method allows us to push the pushing block to the left
     */
    private void isPushedToTheLeft(){
                if(noop.getX() == X
                        && noop.getY()==Y+1
                        && board.getBoard()[X][Y-1].contains("0")
                        && direction == noop.getDirection()
                        && direction== Direction.O
                        && Y!=1){//snoopy à droite && rien à gauche sauf ptet la balle? non j'ai décidé que on pourrait pas push le bloc si ya la balle a gauche


                    board.getBoard()[X][Y] = board.getBoard()[X][Y].replace("2O", "0");
                    board.getBoard()[X][Y-1] = board.getBoard()[X][Y-1].replace("0", "4");
                    Y--;
                    pushed = true;

                }
    }
    /**
     * This method allows us to push the pushing block up
     */
    private void isPushedToTheTop(){
                if(noop.getX() == X+1
                        && noop.getY()==Y
                        && board.getBoard()[X-1][Y].contains("0")
                        && direction == noop.getDirection() && direction== Direction.N
                        &&X != 1){//snoopy en bas && rien en haut sauf ptet la balle? non j'ai décidé que on pourrait pas push le bloc si ya la balle en haut

                    board.getBoard()[X][Y] = board.getBoard()[X][Y].replace("2N", "0");
                    board.getBoard()[X-1][Y] = board.getBoard()[X-1][Y].replace("0", "4");
                    X--;
                    pushed = true;
                }

                //System.out.println("isPushedToTheTop : false"+ "(noop.getX() == X+1 ):"+(noop.getX() == X+1 )+"\n"+ "(noop.getX()):"+(noop.getX())+"\n"+ "(X+1):"+(X+1)+"\n"+ "(noop.getY()==Y ):"+(noop.getY()==Y  )+"\n"+ "(board.getBoard()[X-1][Y].contains(\"0\") ):"+(board.getBoard()[X-1][Y].contains("0"))+"\n"+ "(direction == noop.getDirection() && direction== Direction.N ):"+(direction == noop.getDirection() && direction== Direction.N)+"\n");
    }
    /**
     * This method allows us to push the pushing block down
     */
    private void isPushedToTheBottom(){
                if(noop.getX() == X-1
                        && noop.getY()==Y
                        && board.getBoard()[X-1][Y].contains("0")
                        && direction == noop.getDirection() && direction== Direction.S
                        && X != 10){//snoopy en haut && rien en bas sauf ptet la balle? non j'ai décidé que on pourrait pas push le bloc si ya la balle en bas

                    board.getBoard()[X][Y] = board.getBoard()[X][Y].replace("2S", "0");
                    board.getBoard()[X-1][Y] = board.getBoard()[X-1][Y].replace("0", "4");

                    X++;
                    pushed = true;
                }
    }

    /**
     * This method manages the movements of the pushing block and its interactions with snoopy
     */
    public void Action(){
        //check if X,Y contains 2N,2S,2E,2O, if not throw illegal argument exception
        if( ! board.getBoard()[X][Y].contains("2N") && ! board.getBoard()[X][Y].contains("2S") && ! board.getBoard()[X][Y].contains("2E") && ! board.getBoard()[X][Y].contains("2O")){
            //throw new IllegalArgumentException("PushingBlock Action() : X,Y doesn't contain 2N,2S,2E,2O on board given");
        } else if(direction == Direction.E && Y<20 && !pushed){
            isPushedToTheRight();
            //System.out.println("pushing block to the right "+toString());
        } else if(direction == Direction.O && Y>1 && !pushed){
            isPushedToTheLeft();
            //System.out.println("pushing block to the left "+toString());
        } else if(direction == Direction.N && X>1&& !pushed ){
            //System.out.println("pushing block to the top "+toString()+"__");
            isPushedToTheTop();
        } else if(direction == Direction.S && X<10 && !pushed ){
            isPushedToTheBottom();
            //System.out.println("pushing block to the bot "+toString());
        }
    }


    /*

    public void pushingBlock(){
      if((board.board[noop.getX()][noop.getY()]== board.board[X-1][Y]) ){

      }
    }

        //ShowFadeBlock
        if((board.board[showFadeBlock.getX()][showFadeBlock.getY()] == board.board[X+1][Y]) && (board.board[noop.getX()][noop.getY()] == board.board[X-1][Y])){
            board.board[X][Y] = board.board[X][Y];
        }
        if((board.board[showFadeBlock.getX()][showFadeBlock.getY()] == board.board[X-1][Y]) && (board.board[noop.getX()][noop.getY()] == board.board[X+1][Y])){
            board.board[X][Y] = board.board[X][Y];
        }
        if((board.board[showFadeBlock.getX()][showFadeBlock.getY()] == board.board[X][Y+1]) && (board.board[noop.getX()][noop.getY()] == board.board[X][Y-1])){
            board.board[X][Y] = board.board[X][Y];
        }
        if((board.board[showFadeBlock.getX()][showFadeBlock.getY()] == board.board[X][Y-1]) && (board.board[noop.getX()][noop.getY()] == board.board[X][Y+1])){
            board.board[X][Y] = board.board[X][Y];
        }
        //TreadMillBlock
        if((board.board[treadMillBlock.getX()][treadMillBlock.getY()] == board.board[X+1][Y]) && (board.board[noop.getX()][noop.getY()] == board.board[X-1][Y])){
            board.board[X][Y] = board.board[X][Y];
        }
        if((board.board[treadMillBlock.getX()][treadMillBlock.getY()] == board.board[X-1][Y]) && (board.board[noop.getX()][noop.getY()] == board.board[X+1][Y])){
            board.board[X][Y] = board.board[X][Y];
        }
        if((board.board[treadMillBlock.getX()][treadMillBlock.getY()] == board.board[X][Y+1]) && (board.board[noop.getX()][noop.getY()] == board.board[X][Y-1])){
            board.board[X][Y] = board.board[X][Y];
        }
        if((board.board[treadMillBlock.getX()][treadMillBlock.getY()] == board.board[X][Y-1]) && (board.board[noop.getX()][noop.getY()] == board.board[X][Y+1])){
            board.board[X][Y] = board.board[X][Y];
        }
        //TrappedBlock
        if((board.board[treadMillBlock.getX()][treadMillBlock.getY()] == board.board[X+1][Y]) && (board.board[noop.getX()][noop.getY()] == board.board[X-1][Y])){
            board.board[X][Y] = board.board[X][Y];
        }
        if((board.board[treadMillBlock.getX()][treadMillBlock.getY()] == board.board[X-1][Y]) && (board.board[noop.getX()][noop.getY()] == board.board[X+1][Y])){
            board.board[X][Y] = board.board[X][Y];
        }
        if((board.board[treadMillBlock.getX()][treadMillBlock.getY()] == board.board[X][Y+1]) && (board.board[noop.getX()][noop.getY()] == board.board[X][Y-1])){
            board.board[X][Y] = board.board[X][Y];
        }
        if((board.board[treadMillBlock.getX()][treadMillBlock.getY()] == board.board[X][Y-1]) && (board.board[noop.getX()][noop.getY()] == board.board[X][Y+1])){
            board.board[X][Y] = board.board[X][Y];
        }



    public void setX(int X) {this.X=X;}
    public void setY(int Y) {this.Y=Y;}
    public int getX() {return X;}
    public int getY() {return Y;}

     */

    @Override
    public String toString() {
        String r = "x:"+this.X+" y:"+this.Y;
        return r;
    }
}

