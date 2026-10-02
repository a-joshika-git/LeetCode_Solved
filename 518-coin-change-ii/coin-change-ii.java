class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        
        // Base case: One way to make amount 0 (using no coins)
        dp[0] = 1;
        
        // Iterate through each coin
        for (int coin : coins) {
            // Update the dp array for all amounts from 'coin' to 'amount'
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        
        return dp[amount];
    }
}