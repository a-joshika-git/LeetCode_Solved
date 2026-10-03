class Solution {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        int INF = amount + 1; // A safe value representing infinity (since min coins can't exceed amount)
        
        // Base case: 0 amount requires 0 coins for any coin combination
        for (int i = 0; i < n; i++) {
            dp[i][0] = 0;
        }
        
        // Base case: Initialize the first coin row
        for (int j = 1; j <= amount; j++) {
            if (j % coins[0] == 0) {
                dp[0][j] = j / coins[0];
            } else {
                dp[0][j] = INF; // Cannot form this amount with only the first coin
            }
        }
        
        // Fill the rest of the DP table using choices (Include vs Exclude)
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j < coins[i]) {
                    dp[i][j] = dp[i - 1][j]; // Cannot include the current coin
                } else {
                    int exc = dp[i - 1][j];             // Exclude current coin
                    int inc = 1 + dp[i][j - coins[i]]; // Include current coin (stay at row i since we have infinite supply)
                    dp[i][j] = Math.min(exc, inc);     // We want the *fewest* number of coins
                }
            }
        }
        
        int result = dp[n - 1][amount];
        // If the result is greater than or equal to INF, it means the amount cannot be made
        return result >= INF ? -1 : result;
    }
}