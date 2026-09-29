class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // If the start is ')' or end is '(', it's instantly impossible
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }
        
        // The maximum possible path length is m + n - 1. 
        // Therefore, the maximum possible balance cannot exceed (m + n) / 2.
        int maxBalance = (m + n) / 2;
        
        // memo[row][col][balance]
        Boolean[][][] memo = new Boolean[m][n][maxBalance + 1];
        
        return dfs(grid, 0, 0, 0, maxBalance, memo);
    }
    
    private boolean dfs(char[][] grid, int r, int c, int balance, int maxBalance, Boolean[][][] memo) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Update balance based on current cell
        if (grid[r][c] == '(') {
            balance++;
        } else {
            balance--;
        }
        
        // If balance drops below 0, this path is invalid
        if (balance < 0) {
            return false;
        }
        
        // If we reached the bottom-right cell
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }
        
        // If balance exceeds max possible required balance for remaining path
        if (balance > maxBalance) {
            return false;
        }
        
        // Check memoization table
        if (memo[r][c][balance] != null) {
            return memo[r][c][balance];
        }
        
        boolean res = false;
        if (r + 1 < m) {
            res = res || dfs(grid, r + 1, c, balance, maxBalance, memo);
        }
        if (!res && c + 1 < n) {
            res = res || dfs(grid, r + 1 - 1, c + 1, balance, maxBalance, memo); // simplified to c + 1
        }
        if (!res && c + 1 < n) {
            res = res || dfs(grid, r, c + 1, balance, maxBalance, memo);
        }
        
        return memo[r][c][balance] = res;
    }
}