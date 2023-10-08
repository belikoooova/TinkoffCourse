package edu.hw1;

public class Task8 {
    private Task8() {
    }

    private static final int[] XMOVES = new int[] {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] YMOVES = new int[] {1, 2, 2, 1, -1, -2, -2, -1};
    private static final int BOARD_SIZE = 8;

    public static boolean knightBoardCapture(int[][] board) {
        for (int i = 0; i < BOARD_SIZE; ++i) {
            for (int j = 0; j < BOARD_SIZE; ++j) {
                if (board[i][j] == 1 && !isMoveAllowed(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isMoveAllowed(int[][] board, int i, int j) {
        for (int c = 0; c < BOARD_SIZE; ++c) {
            if (i + XMOVES[c] >= 0 && i + XMOVES[c] < BOARD_SIZE && j + YMOVES[c] >= 0 && j + YMOVES[c] < BOARD_SIZE
                && (board[i + XMOVES[c]][j + YMOVES[c]] == 1)) {
                return false;
            }
        }
        return true;
    }
}
