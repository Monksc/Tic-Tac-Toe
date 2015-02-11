// imports
import java.util.*;

/**
 * This is the main class for Tic Tac Toe
 * You get to play Tic Tac Toe
 * It has a menu with options and help
 */
public class TicTacToe {

    // member data
    Scanner scanner = new Scanner(System.in);
    Board boardObject = new Board();
    ComputerAI computerAI = new ComputerAI();

    private final String ANSI_CLS = "\u001b[2J";

    private String [] player1 = {"X", "Player"};
    private String [] player2 = {"O", "Computer"};
    private String firstTurn = "Player 2";
    private String placeHolder = ""; // just takes the place of Strings
    private String userInput = ""; // used for userInput
    private Boolean repeat = true;
    private Boolean showComputerBrain = true;
    

    public TicTacToe() {
    }
    
    public void mainMenu () {
        
        repeat = true;
        while (repeat){
        System.out.println("\t Main Menu \n" + 
                                "1) Start Game \n" + 
                                "2) Options \n" + 
                                "3) Help \n" +
                                "exit");
                        
        String userInput = scanner.nextLine();   
        
        
        switch (userInput){
            
        case "1":
            try {
                newGame();
            } catch(Exception e){
                System.out.println(e.toString());
            }
            break;
        case "2":
            options();
            break;
        case "3":
            help();
            break;
        case "exit":
            repeat = false;
            break;
        default:
            repeat = true;
            break;
        }
        }
        clearScreen();
    }
    
    public void newGame() throws Exception {
        
        Boolean gameOver = false;
        boardObject.resetBoard();
        
        int turns = 0;
        
        // first Player turn is first
        if (firstTurn.equals("Player 1")){
            
            while (!gameOver){
                
                // didplays board
                boardObject.displayBoardFromDoubleStringArray();
                
                // Shows who won if someone did win
                if (!boardObject.checkIfSomeoneWon().equals("null")){
                    System.out.println("Congradulations " + boardObject.checkIfSomeoneWon() + "!!\n You Won!!!");
                    gameOver = true;
                }
                
                // checks for a tie
                else if (turns == 9){
                    System.out.println("Game over!! You tied!");
                    gameOver = true;
                }
                
                // First Player turns
                else if (turns%2==0){
                    
                    // Done If their Is a human being playing as Player 1
                    if (player1[1].equals("Player")){
                        
                        do {
                            
                            try {
                                System.out.print("Type the number where you want to go: ");
                                int position = scanner.nextInt();
                                
                                if (boardObject.checkIfSpotIsTaken(position - 1)){
                                    repeat = true;
                                }
                                else {
                                    boardObject.addToBoard(position - 1,player1[0]);
                                    repeat = false;
                                }
                            
                            } catch (Exception e){
                                repeat = true;
                            }
                        } while (repeat);
                    }
                    
                    // Done if their is a computer playing as Player 1
                    else {
                        try {
                            boardObject.addToBoard(computerAI.computerTurn(boardObject.getBoard(), showComputerBrain) - 1, player1[0]);
                        }catch (Exception e){
                            System.out.println("Computer turn is " + (computerAI.computerTurn(boardObject.getBoard(), showComputerBrain) - 1) + e.toString());
                        }
                    }
                }
                
                // Second Players turn
                else {
                    
                    // Done If their Is a human being playing as Player 2
                    if (player2[1].equals("Player")){
                        
                        do {
                            
                            try {
                                System.out.print("Type the number where you want to go: ");
                                int position = scanner.nextInt();
                                
                                if (boardObject.checkIfSpotIsTaken(position - 1)){
                                    repeat = true;
                                }
                                else {
                                    boardObject.addToBoard(position - 1,player2[0]);
                                    repeat = false;
                                }
                            
                            } catch (Exception e){
                                repeat = true;
                            }
                        } while (repeat);
                    }
                    
                    // Done if a computer is player 2
                    else {
                        try {
                            boardObject.addToBoard(computerAI.computerTurn(boardObject.getBoard(), showComputerBrain) - 1, player2[0]);
                        }catch (Exception e){
                            System.out.println("Computer turn is " + (computerAI.computerTurn(boardObject.getBoard(), showComputerBrain) - 1) + e.toString());
                        }
                        //scanner.nextInt();
                    }
                }
                
                // does after someone took a turn
                turns ++; 
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    System.out.println(e.toString());
                }
                clearScreen();
            } 
            
        }
        
        // if second player turn is first
        else {
        
             while (!gameOver){
                
                // didplays board
                boardObject.displayBoardFromDoubleStringArray();
                
                // Shows who won if someone did win
                if (!boardObject.checkIfSomeoneWon().equals("null")){
                    System.out.println("Congradulations " + boardObject.checkIfSomeoneWon() + "!!\n You Won!!!");
                    gameOver = true;
                }
                
                // checks for a tie
                else if (turns == 8){
                    System.out.println("Game over!! You tied!");
                    gameOver = true;
                }
                
                // Second Player turns
                else if (turns%2==0){
                    
                    // Done If their Is a human being playing as Player 1
                    if (player2[1].equals("Player")){
                        
                        do {
                            
                            try {
                                System.out.print("Type the number where you want to go: ");
                                int position = scanner.nextInt();
                                
                                if (boardObject.checkIfSpotIsTaken(position)){
                                    repeat = true;
                                }
                                else {
                                    boardObject.addToBoard(position,player2[0]);
                                    repeat = false;
                                }
                            
                            } catch (Exception e){
                                repeat = true;
                            }
                        } while (repeat);
                    }
                    
                    // Done if their is a computer playing as Player 1
                    else {
                        try {
                            boardObject.addToBoard(computerAI.computerTurn(boardObject.getBoard(), showComputerBrain) - 1, player2[0]);
                        } catch (Exception e){
                            System.out.println(e.toString());
                        }
                    }
                }
                
                // First Players turn
                else {
                    
                    // Done If their Is a human being playing as Player 2
                    if (player1[1].equals("Player")){
                        
                        do {
                            
                            try {
                                System.out.print("Type the number where you want to go: ");
                                int position = scanner.nextInt();
                                
                                if (boardObject.checkIfSpotIsTaken(position - 1)){
                                    repeat = true;
                                }
                                else {
                                    boardObject.addToBoard(position - 1,player1[0]);
                                    repeat = false;
                                }
                            
                            } catch (Exception e){
                                repeat = true;
                            }
                        } while (repeat);
                    }
                    
                    // Done if a computer is player 2
                    else {
                        try {
                            boardObject.addToBoard(computerAI.computerTurn(boardObject.getBoard(), showComputerBrain) - 1, player1[0]);    
                        } catch(Exception e) {
                            System.out.println(e.toString());
                        }
                    }
                }
                
                // does after someone took a turn
                turns ++; 
                clearScreen();
            } 
            
        }
        
        mainMenu();
    }
    
    public void options () {
        repeat = true;
        System.out.print("\t Options \n" +
                            "1) Player 1 is [" + player1[0] + "] and Player 2 is [" + player2[0] + "] \n" +
                            "2) " + firstTurn + " goes first \n" +
                            "3) " + player1[1] + " vs " + player2[1] + "\n" +
                            "4) ");
        if (showComputerBrain) {
            System.out.print("Does ");
        }
        else {System.out.print("Does not ");}
        
        System.out.print("show computer's brain");
        
        System.out.println();
        
        userInput = scanner.nextLine();
        
        switch (userInput) {
            case "1":
                placeHolder = player1[0];
                player1[0] = player2[0];
                player2[0] = placeHolder;
                break;
            case "2":
                switch(firstTurn){
                    case "Player 1":
                        firstTurn = "Player 2";
                        break;
                    case "Player 2":
                        firstTurn = "Player 1";
                        break;
                    default:
                        firstTurn = "Player 1";
                        break;
                }
                
                break;
            case "3":
                System.out.println("Is Player 1 a \"Player\" or a \"Computer\"?");
                userInput = scanner.nextLine();
                
                if (userInput.toLowerCase().indexOf("player") >= 0){
                    player1[1] = "Player";
                }
                
                else {
                    player1[1] = "Computer";
                }
                
                System.out.println("Is Player 2 a \"Player\" or a \"Computer\"?");
                userInput = scanner.nextLine();
                
                if (userInput.toLowerCase().indexOf("player") >= 0){
                    player2[1] = "Player";
                }
                
                else {
                    player2[1] = "Computer";
                }
                break;
            case "4":
                if (showComputerBrain){
                    showComputerBrain = false;
                }
                else {showComputerBrain = true;}
                break;
            default:
                repeat = false;
                break;
        }
        
        if (repeat) options();
        
        mainMenu();
    }
    
    public void help () {
        System.out.println("I hope this helps.");
        mainMenu();
    }

    public void clearScreen () {
        System.out.print(ANSI_CLS);
        System.out.flush();
    }
}