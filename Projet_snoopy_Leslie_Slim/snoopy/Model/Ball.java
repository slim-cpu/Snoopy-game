package java.snoopy.Model;

public class Ball extends Entity {

    private final int speed = 4;
    private int xPosition;
    private int yPosition;
    private boolean touchWall = false;
    private Board snoopyBoard;
    private Direction d;

    /**
     * Ball constructor
     * @param x position variation from up to down
     * @param y position variation from left to right
     * @param b the board that the ball is running on
     * @param d the direction of teh ball
     */
    public Ball(int x, int y, Board b, Direction d) {
        super(x, y);
        this.snoopyBoard = b;
        this.xPosition = x;
        this.yPosition = y;
        this.d = d;

    }

    /**
     * This setter allows to set the position of the ball
     * @param x position variation from up to down
     * @param y position variation from left to right
     * @param b the board that the ball is running on
     */
    public void setBall (int x, int y,  Board b){//[12][22]
        this.xPosition = x;
        this.yPosition = y;
        this.d = Direction.SO;
       // System.out.println(toString());
    }

    // the wall is at index x = 0, y = 0, so the max index is x = 21, y = 11

    /**
     * This method manages the different movements of the ball and its interactions with snoopy
     */
    public void Action() {
        /*
       advacing meaning : depending on the direction we will be facing, the coordinates shall changes or not accordingly.
            SO : x = x +1 y = y +1	en bas a droite
            SE : x = x -1 y =  y +1	en bas a gauche
            NE : x = x -1 y = y -1  	en haut a dauche
            NO : x = x +1 y = y -1	en haut a droite
         *///System.out.println("ball movement"+toString());
            if (d == Direction.SO && snoopyBoard.getBoard()[xPosition][yPosition].contains("7SO")) { // = 7A
                if(yPosition == 20){
                    d = Direction.SE;
                    snoopyBoard.getBoard()[xPosition][yPosition] = snoopyBoard.getBoard()[xPosition][yPosition].replace("7SO", "7SE");
                } else if (xPosition == 10){
                    d = Direction.NO;
                    snoopyBoard.getBoard()[xPosition][yPosition] = snoopyBoard.getBoard()[xPosition][yPosition].replace("7SO", "7NO");
                }else{
                    snoopyBoard.getBoard()[xPosition][yPosition] = snoopyBoard.getBoard()[xPosition][yPosition].replace("7SO", "");
                    snoopyBoard.getBoard()[xPosition+1][yPosition+1] += "7SO";

                    xPosition += 1;
                    yPosition += 1;
                }



            } else if (d == Direction.NO && snoopyBoard.getBoard()[xPosition][yPosition].contains("7NO")) {

                if(yPosition == 20){
                    d = Direction.NE;
                    snoopyBoard.getBoard()[xPosition][yPosition] = snoopyBoard.getBoard()[xPosition][yPosition].replace("7NO", "7NE");
                } else if (xPosition == 1){
                    d = Direction.SO;
                    snoopyBoard.getBoard()[xPosition][yPosition] = snoopyBoard.getBoard()[xPosition][yPosition].replace("7NO", "7SO");
                }else{
                    snoopyBoard.getBoard()[xPosition][yPosition] = snoopyBoard.getBoard()[xPosition][yPosition].replace("7NO", "");
                    snoopyBoard.getBoard()[xPosition-1][yPosition+1] += "7NO";
                    yPosition += 1;
                    xPosition -= 1;
                }


            } else if (d == Direction.NE && snoopyBoard.getBoard()[xPosition][yPosition].contains("7NE")) {
                if(yPosition == 1){
                    d = Direction.NO;
                    snoopyBoard.getBoard()[xPosition][yPosition] = snoopyBoard.getBoard()[xPosition][yPosition].replace("7NE", "7NO");
                } else if (xPosition == 1){
                    d = Direction.SE;
                    snoopyBoard.getBoard()[xPosition][yPosition] = snoopyBoard.getBoard()[xPosition][yPosition].replace("7NE", "7SE");
                }else{
                    snoopyBoard.getBoard()[xPosition][yPosition] = snoopyBoard.getBoard()[xPosition][yPosition].replace("7NE", "");
                    snoopyBoard.getBoard()[xPosition-1][yPosition-1] += "7NE";
                    xPosition -= 1;
                    yPosition -= 1;
                }


            } else if (d == Direction.SE && snoopyBoard.getBoard()[xPosition][yPosition].contains("7SE")) {
                if(yPosition == 1){
                    d = Direction.SO;
                    snoopyBoard.getBoard()[xPosition][yPosition] = snoopyBoard.getBoard()[xPosition][yPosition].replace("7SE", "7SO");
                } else if (xPosition == 10){
                    d = Direction.NE;
                    snoopyBoard.getBoard()[xPosition][yPosition] = snoopyBoard.getBoard()[xPosition][yPosition].replace("7SE", "7NE");
                }else{
                    snoopyBoard.getBoard()[xPosition][yPosition] = snoopyBoard.getBoard()[xPosition][yPosition].replace("7SE", "");
                    snoopyBoard.getBoard()[xPosition+1][yPosition-1] += "7SE";

                    yPosition -= 1;
                    xPosition += 1;
                }
            }
        }

    /**
     * sets the x position of the ball
     * @param xPosition
     */
    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    /**
     * sets the y position of the ball
     * @param yPosition
     */
    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    /**
     * sets the state of touchWall
     * @param touchWall
     */
    public void setTouchWall(boolean touchWall) {
        this.touchWall = touchWall;
    }

    /**
     * sets the Board of snoopy
     * @param snoopyBoard
     */
    public void setSnoopyBoard(Board snoopyBoard) {
        this.snoopyBoard = snoopyBoard;
    }

    /**
     * @return the speed of the ball
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * @return the x position of the ball
     */
    public int getxPosition() {
        return xPosition;
    }

    /**
     * @return the y position of the ball
     */
    public int getyPosition() {
        return yPosition;
    }

    /**
     * @return if there is a collision between teh ball and the wall
     */
    public boolean isTouchWall() {
        return touchWall;
    }

    /**
     * @return the board of snoopy
     */
    public Board getSnoopyBoard() {
        return snoopyBoard;
    }

    @Override
    public String toString() {
        return "Ball{" +
                "speed=" + speed +
                ", xPosition=" + xPosition +
                ", yPosition=" + yPosition +
                ", touchWall=" + touchWall +
                ", snoopyBoard=" + snoopyBoard +
                '}';
    }
}
