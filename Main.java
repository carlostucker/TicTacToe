package tictactoe;
import java.util.*;
public class Main {

    public static boolean turnX = true; //controls which user symbol is used

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        String input = "_________";//9 empty cells
        boolean gameWon = false;
        char[][] board = convertArray(input); //converts String input into
        printBoard(board); //print the game board according to user input
        
        while(!gameWon){
            int[] num = count(board); //stores occurrences of each character in num variable
            board = userInput(board); //update board with user input co-ordinates
            printBoard(board); //prints contents of the board
            
            String check = winCheck(board); //stores winning character otherwise is N
            
            //checking win conditions
            if(check.equals("O")||check.equals("X"))
            {
                System.out.println(check+" wins");
                gameWon = true;
            }
            else
            {
                //check if no empty spaces for draw condition
                if(num[2]==0)
                {
                    System.out.println("Draw");
                }
                //check for empty spaces for game not finished condition
                else if(num[2]>0)
                {
                    System.out.println("Game not finished");
                }
            }
        }    
    }
    
    public static char[][]userInput(char[][] board)
    {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        int row;
        int col;
        
        do {
            System.out.println("Enter the coordinates: ");
            while(!sc.hasNextInt()) {
                String input = sc.next();
                System.out.print("You should enter numbers!");
            }
            row = sc.nextInt();
            col = sc.nextInt();
            
            if( (row>3) || (col>3))
            {
                System.out.println("Coordinates should be from 1 to 3!");
            }
            else if(board[row-1][col-1]!=('_'))
            {
                System.out.println("This cell is occupied! Choose another one!");
            }
            else
            {
                if(turnX) {
                    board[row - 1][col - 1] = 'X';
                    turnX = false;
                }else{
                    board[row - 1][col - 1] = 'O';
                    turnX = true;
                }
                exit = true;
            }
        } while (!exit);
        
        return board;
    }
    
    public static String winCheck(char[][] board)
    {
        String output = "";
        for(int i=0;i<3;i++) {
            char hFirst = board[i][0]; //first character in horizontal check
            char vFirst = board[0][i]; //first character in vertical check
            //horizontal check
            if(board[i][1]==hFirst&&board[i][2]==hFirst)
            {
                output+= hFirst; //returns first character in horizontal check
            }
            //vertical check
            else if(board[1][i]==vFirst&&board[2][i]==vFirst)
            {
                output+= vFirst; //returns first character in vertical check
            }
        }
        
        //left diagonal check
        char topLeftChar = board[0][0];
        char topRightChar = board[0][2];
        if(board[1][1]==topLeftChar&&board[2][2]==topLeftChar)
        {
            output+= topLeftChar; //returns character in top left corner
        } 
        //right diagonal check
        else if(board[1][1]==topRightChar&&board[2][0]==topRightChar)
        {
            output+= topRightChar; //returns character in top right corner
        }
        
        return output; //returning string of characters
    }
    
    
    public static char[][] convertArray(String input)
    {
        char[][] board = new char[3][3];
        int count = 0;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                board[i][j] = input.charAt(count);
                count+=1;
            }
        }
        return board;
    }
    
    public static int[] count(char[][] board)
    {
        int xNum = 0; //number of X occurrences
        int oNum = 0; //number of O occurrences
        int blankNum = 0; //number of blank space occurrences
        
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[i].length;j++){
                //check if character at position i is X
                if(board[i][j]=='X')
                {
                    xNum+=1;
                }
                //check if character at position i is O
                else if(board[i][j]=='O')
                {
                    oNum +=1;    
                } 
                //check if character at position i is blank
                else
                {
                    blankNum+=1;
                }
            }
        }
        return new int[]{xNum,oNum,blankNum};
    }
    
    public static void printBoard(char[][] input)
    {
        System.out.println("---------");
        
        for(int i=0;i<input.length;i++)
        {
            System.out.print("|");
            for(int j=0;j<input[0].length;j++)
            {
                System.out.print(" "+input[i][j]);
            }
            System.out.print(" |");
            System.out.println();
            
        }
        System.out.println("---------");
    }
}
