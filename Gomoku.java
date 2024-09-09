import java.util.Scanner;

public class Gomoku {
    private static final int BOARD_SIZE = 15;
    private static final char EMPTY = '.';
    private static final char[] PLAYERS = {'X', 'O'};

    private char[][] board;
    private int currentPlayer;

    public Gomoku() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
        currentPlayer = 0;
    }

    public void printBoard() {
        System.out.print("  ");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.printf("%2d", i);
        }
        System.out.println();

        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.printf("%2d", i);
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(" " + board[i][j]);
            }
            System.out.println();
        }
    }

    public boolean isValidMove(int x, int y) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE && board[y][x] == EMPTY;
    }

    public void makeMove(int x, int y) {
        board[y][x] = PLAYERS[currentPlayer];
    }

    public boolean checkWin(int x, int y) {
        char player = PLAYERS[currentPlayer];
        int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};

        for (int[] dir : directions) {
            int count = 1;
            for (int i = 1; i <= 4; i++) {
                int nx = x + i * dir[0];
                int ny = y + i * dir[1];
                if (nx >= 0 && nx < BOARD_SIZE && ny >= 0 && ny < BOARD_SIZE && board[ny][nx] == player) {
                    count++;
                } else {
                    break;
                }
            }
            for (int i = 1; i <= 4; i++) {
                int nx = x - i * dir[0];
                int ny = y - i * dir[1];
                if (nx >= 0 && nx < BOARD_SIZE && ny >= 0 && ny < BOARD_SIZE && board[ny][nx] == player) {
                    count++;
                } else {
                    break;
                }
            }
            if (count >= 5) {
                return true;
            }
        }
        return false;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printBoard();
            System.out.println("Player " + PLAYERS[currentPlayer] + "'s turn");

            int x, y;
            while (true) {
                System.out.print("Enter your move (x y): ");
                x = scanner.nextInt();
                y = scanner.nextInt();

                if (isValidMove(x, y)) {
                    break;
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            }

            makeMove(x, y);

            if (checkWin(x, y)) {
                printBoard();
                System.out.println("Player " + PLAYERS[currentPlayer] + " wins!");
                break;
            }

            currentPlayer = 1 - currentPlayer;
        }

        scanner.close();
    }

    public static void main(String[] args) {
        Gomoku game = new Gomoku();
        game.play();
    }
}
