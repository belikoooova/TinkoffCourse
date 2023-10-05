package edu.hw1;

public class Task8 {
    private Task8() {
    }

    private static final int[] XMOVES = new int[] {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] YMOVES = new int[] {1, 2, 2, 1, -1, -2, -2, -1};

    @SuppressWarnings("MagicNumber")
    public static boolean knightBoardCapture(int[][] board) {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (board[i][j] == 1) {
                    for (int c = 0; c < 8; ++c) {
                        if (i + XMOVES[c] >= 0 && i + XMOVES[c] < 8 && j + YMOVES[c] >= 0 && j + YMOVES[c] < 8
                            && (board[i + XMOVES[c]][j + YMOVES[c]] == 1)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
