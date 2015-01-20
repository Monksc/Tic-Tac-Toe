/**
 * This class can creates a 
 * Tic-Tac-Toe Board and 
 * displays it, add X / O,
 * sets to new board
 * 
 * **************************
 * * IMPORTAINT INFORMATION *
 * **************************
 * For all arrays remeber it starts at 0
 * All Arrays start from top left and go to bottom right
 * 
 * @author Cameron Monks
 * @date 1/13/15
 */

public class Board {
    
    // member data
    String board [][];
    
    // defualt constructor
    public Board(){
        board = new String [3][3];
        resetBoard();
    }
    
    /***********************
     * Miscellaneous Stuff *
     ***********************/
    
    /**
     * Resets the board to " "
     */
    public void resetBoard (){
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                board[x][y] = " ";
            }
        }
    }
    
    /** 
     * It checks for a win and returns winner icon
     * 
     * @return winner
     * @returns "null"
     */
    public String checkIfSomeoneWon(){
    
        String [] players = {"X","O"};
        
        for (String player:players){
            
            for (int i = 0; i < 3; i++){
                if (amountInRowCollumDiagoanl(player,"row",i) ==  3) return player;
                if (amountInRowCollumDiagoanl(player,"collum",i) == 3) return player;
                if (amountInRowCollumDiagoanl(player,"diagonal",i) == 3) return player;
            }
        }
        
        return "null";
    }
    
    /**
     * Checks to see if place is taken
     * 
     * @param int
     * @return true or false
     */
    public Boolean checkIfSpotIsTaken (int position){
        
        if (doubleArrayToArray(board)[position].equals(" ")){
            return false;
        }
        else {
            return true;
        }
    }
     
    /** 
     * It returns how many of the letter you want
     * are in the same row/collum/diagonal
     * 
     * @param String player is the player you want to see hoe many
     *      they have in a row
     * @param String rowCollumOrDiagonal put in the one you want
     * @param int indexOfARowCollumDiagonal put in wich one you want 
     *      row go left to right
     *      collum go up to down
     *      diagonal first is the one that starts from top left and goes down to bottom right
     * @return the amount in rowCollumDiagoanl
     */
    public int amountInRowCollumDiagoanl (String player, String rowCollumOrDiagonal, int indexOfARowCollumDiagonal){
        
        rowCollumOrDiagonal = rowCollumOrDiagonal.toLowerCase();
        
        int count = 0;
        
        switch (rowCollumOrDiagonal) {
            
            case "row":
                for (int i = 0; i < 3; i++) {
                    if (board[i][indexOfARowCollumDiagonal].equals(player)){
                        count++;
                    }
                }
                break;
            case "collum":
                for (int i = 0; i < 3; i++) {
                    if (board[indexOfARowCollumDiagonal][i].equals(player)){
                        count++;
                    }
                }
                break;
            case "diagonal":
                if (indexOfARowCollumDiagonal == 0) {
                        return howManyOfThreeStringsAreLikeTheFourth(board[0][0],board[1][1],board[2][2],player);
                }
                else if (indexOfARowCollumDiagonal == 1){
                        return howManyOfThreeStringsAreLikeTheFourth(board[0][2],board[1][1],board[2][0],player);
                }
                break;
        }
        
        return count;
    }
    
        
    /**
     * It takes 4 String s and sees how many 
     * of the three are like the fourth String
     * 
     * @param String a
     * @param String b
     * @param String c
     * @param String d
     * @return amount
     */
    public int howManyOfThreeStringsAreLikeTheFourth (String a, String b, String c, String d) {
            
        int amount = 0;
            
        if (a.equals(d)){
            amount++;
        }
        if (b.equals(d)){
            amount++;
        }
        if (c.equals(d)){
            amount++;
        }
        
        return amount;
    }

    
    /****************
     * Add to board *
     ****************/
    
    /**
     * Adds a move to the board where x and
     * y are and puts the player
     * 
     * @param int x is the x position on board
     * @param int y is the y position on board
     * @param String player is X or O
     */
    public void addToBoard(int x, int y, String player){
        
        board[x][y] = player;
    }
    
    /**
     * Adds a move to the board where x and
     * y are and puts the player
     * 
     * @param int [] positions is the x and y position on board
     * @param String player is X or O
     */
    public void addToBoard(int [] positions, String player){
           
        board[positions[0]][positions[1]] = player;
    }
    
    /**
     * Adds a move to the board where the position is
     * and puts player their
     * 
     * @param int position
     * @param String player
     */
    public void addToBoard(int position, String player){
        
        int x = 0;
        int y = 0;
        
        for (int i = 0; i < position; i++){
            
            x++;
            
            if (x == 3){
                y += 1;
                x = 0;
            }
        }
        
        addToBoard(x,y,player);
    }

    
    /******************
     * Display Boards *
     ******************/
     
    /**
     * Displays Board from a Double String Array
     */
    public void displayBoardFromDoubleStringArray(){
        displayBoardFromSingleArray(doubleArrayToArray(board));
    }
    
    /**
     * Display board from String
     * 
     * @param strBoard
     */
    public void displayBoardFromString(String strBoard){
        System.out.println(strBoard);
    }
    
    /**
     * Displays Board from a single Array
     */
    public void displayBoardFromSingleArray(String singleArrayBoard []){
        
        String str = "";
        
        for (int i = 0; i < singleArrayBoard.length; i++){
                
            str += singleArrayBoard[i];
            
            // done when its on the right side of board
            if (i == 2 || i == 5){
                str +="\n-----\n";
            }
            else if (i < 8){
                str += "|";
            }
        }
        
        displayBoardFromString(str);
    }
    
    
    /*********************
     * Converting Boards *
     *********************/

    /**
     * Converts String [][] to String []
     * 
     * @param String [][] doubleArray
     * @return String [] singleArray
     */
    public String [] doubleArrayToArray (String [][] doubleArray) {
         
        String [] singleArray = new String [9];
        
        for (int y = 0; y < 3; y++){
            for (int x = 0; x < 3; x++) {
                singleArray[y*3+x] = doubleArray[x][y];
            }
        }
        
        return singleArray;
    }

    
    /***************
     * Get Methods *
     ***************/
        
    /**
     * A simple get method for to get String [][] board
     * 
     * @return board
     */
    public String [][] getBoard () {
        return board;
    }

}