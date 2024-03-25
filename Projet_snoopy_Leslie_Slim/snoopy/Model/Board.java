package snoopy.Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private Snoopy noop;
    private  ArrayList<Entity> allEntityList ;
    private String[][] fullBoard;
    private int currentLevelNumber = 1;
    private int LevelNumberMax = 2;//inclusive
    public int score;
    private int chrono;

    /**
     * The constructor of Board
     */
    public Board() {
        allEntityList = new ArrayList<Entity>();
        this.fullBoard = this.setBoardFromTextFile2("level1.txt", 12, 22);
        //System.out.println("board created:\n"+this.toString());
    }

    /**
     * This method allows us to know if we have completed all the levels successfully
     */
    public void levelFinished() {
        currentLevelNumber++;
        if(currentLevelNumber > LevelNumberMax) {
            gameWon();
        } else {//load next level
            allEntityList = new ArrayList<Entity>();
            this.fullBoard  = this.setBoardFromTextFile2("level"+currentLevelNumber+".txt", 12, 22);
        }
    }

    /**
     * This method allows us to restart a level
     */
    public void restartLevel(){
        System.out.println("restart level()"+currentLevelNumber);
        setBoardFromTextFile2("level"+currentLevelNumber+".txt", 12, 22);
    }
    public boolean timerPassed() {
        if (getSnoopy() == null || getSnoopy().getLife() < 0) {
            throw new IllegalArgumentException("snoopy.Model.Board.timerPassed() : getSnoopy() is null or getSnoopy().getLife() < 0 empty");
        }
        getSnoopy().loseLife(1);
        if(getSnoopy().getLife() == 0){
            System.out.println(" Snoopy is dead");
            gameLost();
        }else{
            restartLevel();
            return true;
        }
        return false;
    }
    public void setChrono(int chrono) {
        this.chrono = chrono;
    }
    public int getChrono() {
        return this.chrono;
    }


    /**
     * This method prints if we won the game and the score
     */
    public void gameWon(){
        System.out.println("Game won"+score);
        //TODO need a name
        String ret = score +"\n";
        try {
            Files.write(Paths.get("src/main/resources/Scores.txt"), ret.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    /**
     * This method prints that we lost when we snoopy runs out of lives
     */
    public void gameLost(){
        if(getSnoopy().getLife() == 0) {
            System.out.println("Game lost");
        }
    }

    /**
     * This method saves the score when we win the game
     * @param nom
     * @param score
     */
    public void SaveScore(String nom, int score) {
        try
        {
            String filename= "Scores.txt";
            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
            fw.write(nom+"___"+score+"\n");//appends the string to the file
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    /**
     * @return new score
     */
    public int setScore() {
        return score+= 100*chrono;
    }

    /**
     * @return the score
     */
    public int getScore() {
        score = setScore();
        return score;
    }

    /**
     * This method moves the ball while the level is not finished
     */
    public void moveBall() {
        for (Entity e : allEntityList) {
            if (e instanceof Ball) {
                e.Action();
            }
        }
        if(this.isAllBirdPickedUp()){
            levelFinished();
        }
    }

    /**
     * This method detects if we have pressed the key space
     */
    public void spacePressed(){

        for (Entity entity : allEntityList) {
            if (entity instanceof BreakingBlock BrkBlk) {
                BrkBlk.setSpacePressed(true);
            }
        }

    }

    /**
     * This method allows snoopy to move up
     */
    public void moveUp() {
        this.move(Direction.N,-1,0);
    }

    /**
     * This method allows snoopy to move down
     */
    public void moveDown() {
        this.move(Direction.S,+1,0);

    }

    /**
     * This method allows snoopy to move right
     */
    public void moveRight() {
        this.move(Direction.E,0,+1);
    }

    /**
     * This method allows snoopy to move left
     */
    public void moveLeft() {
        this.move(Direction.O,0,-1);
    }

    /**
     * This method allows snoopy to move according to the pressed direction from the keyboard
     * @param pressedDirection pressed direction from the keyboard : up, down, est, west
     * @param Xmodifier
     * @param Ymodifier
     */
    public void move(Direction pressedDirection,int Xmodifier,int Ymodifier) {
        for (Entity entity : allEntityList) {
            if (!(entity instanceof PushingBlock pushB) && !(entity instanceof TrappedBlock tb && tb.triggered()) && !(entity instanceof Ball )){
                entity.Action();
            }

        }

        if(     noop.getDirection() == pressedDirection){

            if((noop.getY()<20 && pressedDirection==Direction.E )|| (noop.getY() > 1 && noop.getDirection() == Direction.O)|| (noop.getX() < 10 && noop.getDirection() == Direction.S)|| (noop.getX() > 1 && noop.getDirection() == Direction.N )){  //snoopy goes on a empty case
                //normal or trap bloc
                if(fullBoard[noop.getX()+Xmodifier][noop.getY()+Ymodifier].equals("0")
                        || fullBoard[noop.getX()+Xmodifier][noop.getY()+Ymodifier].equals("3")
                        || fullBoard[noop.getX()+Xmodifier][noop.getY()+Ymodifier].equals("6E")
                        || fullBoard[noop.getX()+Xmodifier][noop.getY()+Ymodifier].equals("6O")
                        || fullBoard[noop.getX()+Xmodifier][noop.getY()+Ymodifier].equals("6S")
                        || fullBoard[noop.getX()+Xmodifier][noop.getY()+Ymodifier].equals("6N")   ){

                    fullBoard[noop.getX()][noop.getY()] = fullBoard[noop.getX()][noop.getY()].replace("8"+pressedDirection , "");
                    noop.setX(noop.getX() + Xmodifier);
                    noop.setY(noop.getY() + Ymodifier);
                    fullBoard[noop.getX()][noop.getY()] += ("8"+pressedDirection);
                    //Bird touched
                } else if(fullBoard[noop.getX()+Xmodifier][noop.getY()+Ymodifier].contains("9")){

                    fullBoard[noop.getX()][noop.getY()] = fullBoard[noop.getX()][noop.getY()].replace("8"+pressedDirection , "");
                    noop.setX(noop.getX() + Xmodifier);
                    noop.setY(noop.getY() + Ymodifier);
                    fullBoard[noop.getX()][noop.getY()] += ("8"+pressedDirection);



                }
                //pushing bloc in front/near snoopy
                //wnoopy pushes a block
                else if(((fullBoard[noop.getX()+Xmodifier][noop.getY()+Ymodifier].contains("2N")&&pressedDirection==Direction.N)|| (fullBoard[noop.getX()+Xmodifier][noop.getY()+Ymodifier].contains("2S")&&pressedDirection==Direction.S)|| (fullBoard[noop.getX()+Xmodifier][noop.getY()+Ymodifier].contains("2E")&&pressedDirection==Direction.E)|| (fullBoard[noop.getX()+Xmodifier][noop.getY()+Ymodifier].contains("2O")&&pressedDirection==Direction.O)) && 1<noop.getX()+Xmodifier && noop.getX()+Xmodifier < 10 && 1< noop.getY()+Ymodifier && noop.getY()+Ymodifier < 20){//empty space behind the pushing block
                    for (Entity entity : allEntityList) {
                        if (entity instanceof PushingBlock pushB){
                            pushB.Action();
                            //allEntityList.remove(pushB);
                        }
                    }

                    fullBoard[noop.getX()][noop.getY()] = fullBoard[noop.getX()][noop.getY()].replace("8"+pressedDirection , "");
                    noop.setX(noop.getX() + Xmodifier);
                    noop.setY(noop.getY() + Ymodifier);
                    fullBoard[noop.getX()][noop.getY()] += ("8"+pressedDirection);
                }
                //snoopy walks on a trap
                else if(fullBoard[noop.getX()+Xmodifier][noop.getY()+Ymodifier].contains("3") && 1<noop.getX()+Xmodifier && noop.getX()+Xmodifier < 10 && 1< noop.getY()+Ymodifier && noop.getY()+Ymodifier < 20){

                    fullBoard[noop.getX()][noop.getY()] = fullBoard[noop.getX()][noop.getY()].replace("8"+pressedDirection , "");
                    noop.setX(noop.getX() + Xmodifier);
                    noop.setY(noop.getY() + Ymodifier);
                    fullBoard[noop.getX()][noop.getY()] += ("8"+pressedDirection);

                }
            }

        }else if (  noop.getDirection() != pressedDirection){
            fullBoard[noop.getX()][noop.getY()] = fullBoard[noop.getX()][noop.getY()].replace("8" + noop.getDirection(), "8"+pressedDirection);
            noop.setDirection(pressedDirection);
        }
        for (Entity entity : allEntityList) {
            if (entity instanceof PushingBlock pushB ){
                //pcushingBlock.setSpacePressed(true);
                //dont do anything
            }else if(!(entity instanceof Ball )){
                entity.Action();
            }
        }
    }

    /**
     * This method sets the elements of the board from a text file passed in the parameters
     * @param NameFile text file
     * @param x
     * @param y
     * @return
     */
    public String[][] setBoardFromTextFile2(String NameFile, int x, int y) {

        //intialize somes variables
        String[][] ret = new String[x][y];
        score = 0;
        //fille the matrix with "0" reason im doing this is because after we dont have to replace 08S with 0 when tmp find 0 because of the switch, its easier this way
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                ret[i][j] = "0";
            }
        }

        String file = null;
        try {
            file = Files.readString(new File("src/main/resources/levels/" + NameFile).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        char[] chararray = file.toCharArray();
        //System.out.println("chararray.length = " + chararray.toString());

        //variables for the loop
        int rowCount = 0;
        int columnCount = 0;

        rowCount =columnCount= 0;
        for (int i = 0; i < chararray.length && columnCount < 22 ; i++) {
            //if  char at i is a space increase column count
            Character tmp = chararray[i];
            if (tmp == ' ') {
                columnCount++;
            } else if (tmp.equals('\n')) {
                rowCount++;
                columnCount = 0;
            } else {
                //empty
                if (tmp == '8') {
                    if (chararray[i + 1] != 'N' && chararray[i + 1] != 'S' && chararray[i + 1] != 'W' && chararray[i + 1] != 'E') {// get the next char to find the direction
                        throw new IllegalArgumentException("Snoopy.Model.setBoardFromText.283 : chararray[i+1] inst NSWE");
                    } else if (chararray[i + 1] == 'N') {
                        ret[rowCount][columnCount] = "08N";
                        noop = new Snoopy(rowCount, columnCount, this, Direction.N);
                        allEntityList.add(noop);
                    } else if (chararray[i + 1] == 'S') {
                        ret[rowCount][columnCount] = "08S";
                        noop = new Snoopy(rowCount, columnCount, this, Direction.S);
                        allEntityList.add(noop);
                    } else if (chararray[i + 1] == 'W') {
                        ret[rowCount][columnCount] = "08W";
                        noop = new Snoopy(rowCount, columnCount, this, Direction.O);
                        allEntityList.add(noop);
                    } else if (chararray[i + 1] == 'E') {
                        ret[rowCount][columnCount] = "08E";
                        noop = new Snoopy(rowCount, columnCount, this, Direction.E);
                        allEntityList.add(noop);
                    }
                }
            }
        }

        //System.out.println("found snoopy"+noop.toString());

        //reset variables for the loop
        rowCount = columnCount= 0;
        for (int i = 0; i < chararray.length && columnCount < 22 ; i++) {
            //if  char at i is a space increase column count
            Character tmp = chararray[i];
            if (tmp.equals(' ')) {
                columnCount++;
            } else if (tmp.equals('\n')) {
                rowCount++;
                columnCount = 0;
            } else {
                switch (tmp) {
                    case '0' -> {/*no need to add a new entity, the absence of one is enough as intel
                        //allEntityList.add(new Entity(rowCount, columnCount, 0));
                        */}
                    case '1' -> {//breakable
                        ret[rowCount][columnCount] = "1";
                        allEntityList.add(new BreakingBlock(rowCount, columnCount, this, noop));

                    }
                    case '2' -> {//pushable
                        if (chararray[i + 1] != 'N' && chararray[i + 1] != 'S' && chararray[i + 1] != 'O' && chararray[i + 1] != 'E') {// get the next char to find the direction
                            throw new IllegalArgumentException("Snoopy.Model.setBoardFromText.283 : chararray[i+1] inst NSWE");
                        } else if (chararray[i + 1] == 'N') {
                            ret[rowCount][columnCount] = "2" + chararray[i + 1] ;
                            allEntityList.add(new PushingBlock(rowCount, columnCount, this, noop, Direction.N));
                        } else if (chararray[i + 1] == 'S') {
                            ret[rowCount][columnCount] = "2" + chararray[i + 1] ;
                            allEntityList.add(new PushingBlock(rowCount, columnCount, this, noop, Direction.S));
                        } else if (chararray[i + 1] == 'O') {
                            ret[rowCount][columnCount] = "2" + chararray[i + 1];
                            allEntityList.add(new PushingBlock(rowCount, columnCount, this, noop, Direction.O));
                        } else if (chararray[i + 1] == 'E') {
                            ret[rowCount][columnCount] = "2" + chararray[i + 1] ;
                            allEntityList.add(new PushingBlock(rowCount, columnCount, this, noop, Direction.E));
                        }

                    }
                    case '3' -> {//trap block no direction
                        ret[rowCount][columnCount] = "3";
                        allEntityList.add(new TrappedBlock(rowCount, columnCount,this, noop));

                    }
                    case '4' -> {//block invincible
                        ret[rowCount][columnCount] = "4";
                        allEntityList.add(new InvincibleBlock(rowCount, columnCount, this, noop));
                    }
                    case '5' -> {                    //block appear/disappear
                        ret[rowCount][columnCount] = "5";
                        allEntityList.add(new ShowFadeBlock(rowCount, columnCount, this, noop));
                    }
                    case '6' -> {//treadmillblock
                        if (chararray[i + 1] != 'N' && chararray[i + 1] != 'S' && chararray[i + 1] != 'W' && chararray[i + 1] != 'E') {// get the next char to find the direction
                            throw new IllegalArgumentException("Snoopy.Model.setBoardFromText.316 : chararray[i+1] inst NSWE");
                        } else if (chararray[i + 1] == 'N') {
                            ret[rowCount][columnCount] = "6" + chararray[i + 1];
                            allEntityList.add(new TreadMillBlock(rowCount, columnCount, this, noop, Direction.N));
                        } else if (chararray[i + 1] == 'S') {
                            ret[rowCount][columnCount] = "6" + chararray[i + 1];
                            allEntityList.add(new TreadMillBlock(rowCount, columnCount, this, noop, Direction.S));
                        } else if (chararray[i + 1] == 'O') {
                            ret[rowCount][columnCount] = "6" + chararray[i + 1];
                            allEntityList.add(new TreadMillBlock(rowCount, columnCount, this, noop, Direction.O));
                        } else if (chararray[i + 1] == 'E') {
                            ret[rowCount][columnCount] = "6" + chararray[i + 1];
                            allEntityList.add(new TreadMillBlock(rowCount, columnCount, this, noop, Direction.E));
                        }
                    }
                    case '7' -> {
                        if (chararray[i + 1] != 'S' && chararray[i + 1] != 'N' && chararray[i + 2] != 'O' && chararray[i + 2] != 'E') {// get the next char to find the direction
                            throw new IllegalArgumentException("Snoopy.Model.setBoardFromText.334 : chararray[i+1] inst NSWE");
                        } else if (chararray[i + 1] == 'S' && chararray[i + 2] == 'O') {
                            ret[rowCount][columnCount] = "07SO";
                            allEntityList.add(new Ball(rowCount, columnCount, this,  Direction.SO));
                        } else if (chararray[i + 1] == 'S' && chararray[i + 2] == 'E') {
                            ret[rowCount][columnCount] = "07SE";
                            allEntityList.add(new Ball(rowCount, columnCount, this,  Direction.SE));
                        } else if (chararray[i + 1] == 'N' && chararray[i + 2] == 'O') {
                            ret[rowCount][columnCount] = "07NO";
                            allEntityList.add(new Ball(rowCount, columnCount, this,  Direction.NO));
                        } else if (chararray[i + 1] == 'N' && chararray[i + 2] == 'E') {
                            ret[rowCount][columnCount] = "07NE";
                            allEntityList.add(new Ball(rowCount, columnCount, this, Direction.NE));
                        }
                    }
                    case '8' -> {}
                    //case '8' : pas besoin de case8, on l'a fait avec
                    case '9' -> {//player
                        //TODO add bird direction dumbass
                        if (chararray[i + 1] != 'O' && chararray[i + 1] != 'E') {// get the next char to find the direction
                            throw new IllegalArgumentException("Snoopy.Model.setBoardFromText.316 : chararray[i+1] inst NSWE");
                        } else if (chararray[i + 1] == 'E') {
                            ret[rowCount][columnCount] = "9" + chararray[i + 1] + "";
                            allEntityList.add(new YellowBird(rowCount, columnCount, this, noop));
                        } else if (chararray[i + 1] == 'O') {
                            ret[rowCount][columnCount] = "9" + chararray[i + 1] + "";
                            allEntityList.add(new YellowBird(rowCount, columnCount, this, noop ));
                        }
                    }
                    case 'N'-> {}
                    case 'S'-> {}
                    case 'O'-> {}
                    case 'E'-> {}
                    case '\n'-> {}
                    case ' '-> {}

                    //default -> throw new IllegalArgumentException("Snoopy.Model.setBoardFromText.352 : chararray[i] = ."+chararray[i]+". other print "+chararray[i] );

                    //if not, check if it's a number, if it is :
                    //depending on the number, add the direction wich will always be the charactere at tehe right (i+1)
                    // if this number does not
                }
            }
        }
        return ret;
    }

    /**
     * This method sets a text file from the board of the game
     * @param board
     * @param filename
     * @param path
     * @throws IOException
     */
    public void setTxtFromBoard(String[][] board, String filename, String path) throws IOException {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                sb.append(board[i][j]+" ");
            }
            //prehaprs sb.stripTrailing() ? or sb.strip() ?
            sb.append("\n");
        }
        String txt = sb.toString();
        //FileUtils.writeStringToFile(new File("test.txt"), "Hello File", forName("UTF-8"));
        path = path + filename;
        Files.write( Paths.get(path), txt.getBytes());
    }

    /**
     *
     * @return Snoopy
     */
    public Snoopy getSnoopy() {
        return noop;
    }

    /**
     *
     * @return the board of the game
     */
    public String[][] getBoard() {
        return fullBoard;
    }
    /*public void update() {
        for (Entity e : allEntityList) {
            e.Action();
        }
    }
    */
    @Override
    public String toString(){
        String ret = "";
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 22; j++) {
                if(fullBoard[i][j].length() >= 4) {
                    ret += "\t"+fullBoard[i][j];

                }else{
                    ret += "\t\t"+fullBoard[i][j];
                }
            }
            ret += "\n";
        }
        return ret;
    }
    //check if all bird are pickedup
    private boolean isAllBirdPickedUp(){
        boolean yes = true;
        for (Entity e : allEntityList) {
            if (e instanceof YellowBird B ) {
                if(!B.IsPickedUp()){
                    yes = false;
                }
            }
        }
        return yes;
    }
}