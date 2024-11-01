import java.util.Scanner;

public class TicTacToe {
    static char[][] board = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};
    static char currentPlayer = 'X';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("RULES:");
        System.out.println("1. The game is played on a 3x3 grid.");
        System.out.println("2. Player 1 is 'X' and Player 2 is 'O'.");
        System.out.println("3. Players take turns to place their mark in an empty cell.");
        System.out.println("4. The first player to get 3 of their marks in a row (horizontally, vertically, or diagonally) wins.");
        System.out.println("5. If all 9 cells are filled and no player has 3 in a row, the game is a draw.");

        do {
            resetBoard();
            playGame(scanner);

            System.out.print("Do you want to play again? (y/n): ");
            playAgain = scanner.next().equalsIgnoreCase("y");

        } while (playAgain);

        System.out.println("Thanks for playing!");
        scanner.close();
    }

    static void playGame(Scanner scanner) {
        boolean gameWon = false;
        int moves = 0;

        while (!gameWon && moves < 9) {
            printBoard();
            System.out.print("Player " + currentPlayer + ", enter a number (1-9) to make a move: ");
            int move = scanner.nextInt();

            if (isValidMove(move)) {
                makeMove(move);
                gameWon = checkWin();
                if (!gameWon) {
                    togglePlayer();
                }
                moves++;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        printBoard();
        if (gameWon) {
            System.out.println("Player " + currentPlayer + " wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }

    static void resetBoard() {
        char start = '1';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = start++;
            }
        }
        currentPlayer = 'X';
    }

    static void printBoard() {
        System.out.println("Current Board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean isValidMove(int move) {
        if (move < 1 || move > 9) {
            return false;
        }

        int row = (move - 1) / 3;
        int col = (move - 1) % 3;

        return board[row][col] != 'X' && board[row][col] != 'O';
    }

    static void makeMove(int move) {
        int row = (move - 1) / 3;
        int col = (move - 1) % 3;
        board[row][col] = currentPlayer;
    }

    static boolean checkWin() {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }
        return false;
    }

    static void togglePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
}
