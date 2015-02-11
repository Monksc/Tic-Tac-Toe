import java.util.Random;

public class ComputerAI {

    
    // member data
    private String board [][] = new String [3][3];
    private String flippedBoard [][] = new String [3][3];
    Random rndm = new Random ();
    
    // defualt constructor
    public ComputerAI(){
        
    }
    
    public int computerTurn(String [][] board, Boolean showComputerBrain) throws Exception{
        
        if (showComputerBrain){
            typePhrase("AI Turn");
            System.out.println();
        }
        
        if (amountOfMoves(board) == 0){
            if (showComputerBrain) typePhrase("First turn");
            return 1;
        }
        
        if (showComputerBrain){
            typePhrase("Checking To see if one may win");
            System.out.println();
        }
        
        if (checkIfSomeoneMayWin(board) != -1){
            return checkIfSomeoneMayWin(board);
        }
        
        if (showComputerBrain){
            typePhrase("Flipping board ");
            flippedBoard = board;
        }
        int count = 0;
        while (flippedBoard[0][0].equals(" ") && flippedBoard[1][0].equals(" ") && count < 5){
            flippedBoard = flipBoard(flippedBoard);
            count++;
        }
        if (showComputerBrain) {
            typePhrase(count + " times");
            System.out.println();
        }
        count = count%4;
        
        if (showComputerBrain){
            typePhrase("Checking for weird moves");
            System.out.println();
        }
        // checking for weird moves
        if (checkingForWeirdMoves(flippedBoard) != -1) return flippingTheReturnValue(count, checkingForWeirdMoves(flippedBoard));
        
        if (showComputerBrain){
            typePhrase("Checking for middle");
            System.out.println();
        }
        if (board[1][1].equals(" ")){
            return 5;
        }
        
        if (showComputerBrain){
            typePhrase("Checking for Corner");
            System.out.println();
        }
        
        // getting the corner
        if (flippedBoard[0][0].equals(" ")) return flippingTheReturnValue(count, 1);
        if (flippedBoard[2][0].equals(" ")) return flippingTheReturnValue(count, 3);
        if (flippedBoard[0][2].equals(" ")) return flippingTheReturnValue(count, 7);
        if (flippedBoard[2][2].equals(" ")) return flippingTheReturnValue(count, 9);
        
        if (showComputerBrain) {
            typePhrase("Checking for a Side");
            System.out.println();
        }
        
        // getting the sides
        if (flippedBoard[1][0].equals(" ")) return flippingTheReturnValue(count, 2);
        if (flippedBoard[2][1].equals(" ")) return flippingTheReturnValue(count, 6);
        if (flippedBoard[0][1].equals(" ")) return flippingTheReturnValue(count, 4);
        if (flippedBoard[1][2].equals(" ")) return flippingTheReturnValue(count, 2);
        
        return 9;
    }
    
    public int checkingForWeirdMoves(String[][] board){
        
        if (!board[0][0].equals(" ") && !board[1][1].equals(" ") && board[2][2].equals(board[0][0]) && !board[1][1].equals(board[0][0])){
            
            if (amountOfMoves(board) == 3){
                return 2;
            }
            else if (amountOfMoves(board) > 3) {
            
                if (board[1][0].equals(" ") && board[2][0].equals(" ") && board[2][1].equals(" ")){
                    return 3;
                }
                if (board[0][1].equals(" ") && board[0][2].equals(" ") && board[1][2].equals(" ")){
                    return 7;
                }
            }
        }
        
        if (!board[0][0].equals(" ") && !board[2][2].equals(" ")){
            if (amountOfMoves(board) == 2){
                return 9;
            }
        }
        
        if (!board[0][0].equals(" ") && board[1][1].equals(" ") && amountOfMoves(board) == 2) {
            
            if (board[1][0].equals(" ") && board[2][0].equals(" ") && board[2][1].equals(" ")){
                
                return 3;   
            }
            else {
             
                return 7;   
            }
        }
        
        return -1;
    }
    
    public int checkIfSomeoneMayWin(String[][] board){
        
        String players [] = {"O", "X"};
        
        for (String player:players) {
        
            for (int i = 0; i < 3; i++) {
                if (forWin(board[0][i], board[1][i], board[2][i], player, i, "x") != -1){
                   return forWin(board[0][i], board[1][i], board[2][i], player, i, "x");
                }
            
                if (forWin(board[i][0], board[i][1], board[i][2], player, i, "y") != -1){
                        return forWin(board[i][0], (board[i][1]), board[i][2], player, i, "y");
                }
            }
            
            if (forWinDiagonals(board[0][0], board[1][1], board[2][2], player) != -1){
                switch (forWinDiagonals(board[0][0], board[1][1], board[2][2], player)) {
                    case 1:
                        return 1;
                    case 2:
                        return 5;
                    case 3:
                        return 9;
                }
            }
            if (forWinDiagonals(board[0][2], board[1][1], board[2][0], player) != -1){
                switch (forWinDiagonals(board[0][2], board[1][1], board[2][0], player)) {
                    case 1:
                        return 7;
                    case 2:
                        return 5;
                    case 3:
                        return 3;
                }
            }
            
        }
        
        return -1;
    }
    
    public int forWinDiagonals (String a, String b, String c, String d) {
        
        if (amountInLineTheSameAsStringD(a,b,c,d) == 2){
            
            if (a.equalsIgnoreCase(" ")) return 1;
            if (b.equalsIgnoreCase(" ")) return 2;
            if (c.equalsIgnoreCase(" ")) return 3;
        }
        
        return -1;
    }
    
    // only for non diagonals
    public int forWin(String a, String b, String c, String d, int rowOrCollum, String xOrY){
        
        int other = 0;
        
        if (amountInLineTheSameAsStringD(a,b,c,d) == 2){
            String array [] = {a,b,c};
        
            for (String l:array) {
                if (l.equals(" ")){    
                    
                    // checks to see what l actually is a,b,c. a stays the same
                    if (l.equals(b))
                        other = 1;
                    else if (l.equals(c))
                        other = 2;
                    
                    if (xOrY.equals("x")){
                        return boardArrayToInt(other,rowOrCollum);
                    }
                    else if (xOrY.equals("y")){
                        return boardArrayToInt(rowOrCollum,other);
                    }
                }
            }
        }
        
        return -1;
    }
    
    public int boardArrayToInt (int x, int y) {
        return y*3 + x + 1;
    }
    
    public int amountInLineTheSameAsStringD(String a, String b, String c, String d){
        
        int count = 0;
        String array [] = {a,b,c};
        
        for (String l:array) {
            if (l.equals(d)){    
                count ++;
            }
        }
        
        return count;
    }
    
    public String [][] flipBoardTimes (String boardToBeFlipped[][], int times){
        
        for (int i = 0; i < times; i++) {
            boardToBeFlipped = flipBoard(boardToBeFlipped);
        }
        
        return boardToBeFlipped;
    }
    
    public String [][] flipBoard(String boardToBeFlipped[][]){
        
        String returnedFlippedBoard [][] = new String[3][3];
        
        /* Flipps it Clock Wise */
        
        // middle 
        returnedFlippedBoard[1][1] = boardToBeFlipped[1][1];
        
        // corners
        returnedFlippedBoard[0][0] = boardToBeFlipped[0][2];
        returnedFlippedBoard[2][0] = boardToBeFlipped[0][0];
        returnedFlippedBoard[2][2] = boardToBeFlipped[2][0];
        returnedFlippedBoard[0][2] = boardToBeFlipped[2][2];
        
        // sides
        returnedFlippedBoard[1][0] = boardToBeFlipped[0][1];
        returnedFlippedBoard[2][1] = boardToBeFlipped[1][0];
        returnedFlippedBoard[1][2] = boardToBeFlipped[2][1];
        returnedFlippedBoard[0][1] = boardToBeFlipped[1][2];
        
        return returnedFlippedBoard;
    }
    
    public int amountOfMoves (String[][] board) {
        
        int count = 0;
        
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (!board[x][y].equals(" ")) {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    public int flippingTheReturnValue (int amountFlipped, int position){
        
        for (int i = 0; i < amountFlipped; i++){
            switch (position) {
                case 1:
                    position = 7;
                    continue;
                case 2:
                    position = 4;
                    continue;
                case 3:
                    position = 1;
                    continue;
                case 4:
                    position = 8;
                    continue;
                case 6:
                    position = 2;
                    continue;
                case 7:
                    position = 9;
                    continue;
                case 8:
                    position = 6;
                    continue;
                case 9:
                    position = 3;
                    continue;
                default:
                    break;
            }
        }
        
        return position;
    }
    
    /** 
     * Types out the letters slowly
     * 
     * @param phrase is what you want to be printed out
     * @param seconds is the seconds it takes beetween each letter written
     */
    public void typePhrase (String phrase) throws InterruptedException {
        
        int index = 0;
        
        for (int i = 0; i < phrase.length(); i++){
            
            long seconds = 100 + rndm.nextInt(100);
            
            try {
                Thread.sleep(seconds);
                System.out.print(phrase.substring(index, ++index));
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }
            
        }
    }
}