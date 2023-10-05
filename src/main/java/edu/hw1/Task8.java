package edu.hw1;

public class Task8 {
    private static final int[] xMoves = new int[] {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] yMoves = new int[] {1, 2, 2, 1, -1, -2, -2, -1};

    @SuppressWarnings("MagicNumber")
    public static boolean knightBoardCapture(int[][] board) {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (board[i][j] == 1) {
                    for (int c = 0; c < 8; ++c) {
                        if (i + xMoves[c] >= 0 && i + xMoves[c] < 8 && j + yMoves[c] >= 0 && j + yMoves[c] < 8) {
                            if (board[i + xMoves[c]][j + yMoves[c]] == 1) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
