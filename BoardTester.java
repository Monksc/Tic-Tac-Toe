public class BoardTester {

    public static void main(String[] args) {
        
        Board board = new Board();
        
        board.addToBoard(0,0,"O");
        board.addToBoard(0,1,"O");
        board.addToBoard(0,2,"O");
        
        board.addToBoard(1,0," ");
        board.addToBoard(1,1," ");
        board.addToBoard(1,2,"O");
        
        board.addToBoard(2,0," ");
        board.addToBoard(2,1," ");
        board.addToBoard(2,2," ");
        
        if (!board.checkIfSomeoneWon().equals("null")){
            System.out.println("Congradulations " + board.checkIfSomeoneWon() + "!!\nYou have won!!!");
        }
        
        board.addToBoard(1,1,"O");
        
        board.displayBoardFromDoubleStringArray();
        
        if (!board.checkIfSomeoneWon().equals("null")){
            System.out.println("Congradulations " + board.checkIfSomeoneWon() + "!!\nYou have won!!!");
        }
        
    }
}