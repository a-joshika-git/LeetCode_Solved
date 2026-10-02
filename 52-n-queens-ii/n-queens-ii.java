import java.util.Arrays;

class Solution {
    public int totalNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        return backtrack(0, board, n);
    }
    
    private int backtrack(int row, char[][] board, int n) {
        if (row == n) {
            return 1; // Found 1 valid solution
        }
        
        int count = 0;
        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col, n)) {
                board[row][col] = 'Q';              // Place queen
                count += backtrack(row + 1, board, n); // Recurse to next row
                board[row][col] = '.';              // Backtrack (remove queen)
            }
        }
        return count;
    }
    
    private boolean isSafe(char[][] board, int row, int col, int n) {
        // Check column upwards
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') return false;
        }
        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }
}
//class Solution {
//     private int count = 0;

//     public int totalNQueens(int n) {
//         boolean[] cols = new boolean[n];
//         boolean[] diag1 = new boolean[2 * n - 1]; 
//         boolean[] diag2 = new boolean[2 * n - 1]; 
        
//         backtrack(0, n, cols, diag1, diag2);
//         return count;
//     }

//     private void backtrack(int row, int n, boolean[] cols, boolean[] diag1, boolean[] diag2) {
//         if (row == n) {
//             count++;
//             return;
//         }

//         for (int col = 0; col < n; col++) {
//             int d1 = row - col + n - 1;
//             int d2 = row + col;
//             if (cols[col] || diag1[d1] || diag2[d2]) {
//                 continue;
//             }
//             cols[col] = true;
//             diag1[d1] = true;
//             diag2[d2] = true;
//             backtrack(row + 1, n, cols, diag1, diag2);
//             cols[col] = false;
//             diag1[d1] = false;
//             diag2[d2] = false;
//         }
//     }
// }