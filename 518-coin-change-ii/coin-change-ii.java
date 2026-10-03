class Solution {
    public int change(int amount, int[] coins){
        int dp[][] = new int[coins.length][amount+1];
        for(int i=0;i<coins.length;i++)
        {
            dp[i][0]=1;
        }
        for(int i=1;i<=amount;i++)
        {
            if(i%coins[0]==0)
            {
                dp[0][i]=1;
            }
            else
            {
                dp[0][i]=0;
            }
        }
        for(int i=1;i<coins.length;i++)
        {
            for(int j=1;j<=amount;j++)
            {
                if(j<coins[i])
                {
                    dp[i][j]=dp[i-1][j];
                }
                else{
                    int exc= dp[i-1][j];
                    int inc= dp[i][j-coins[i]];
                    dp[i][j]=inc+exc;
                }
            }
       }
       return dp[coins.length-1][amount];
}
}
// class Solution {
//     public int change(int amount, int[] coins) {
//         int[] dp = new int[amount + 1];
        
//         // Base case: One way to make amount 0 (using no coins)
//         dp[0] = 1;
        
//         // Iterate through each coin
//         for (int coin : coins) {
//             // Update the dp array for all amounts from 'coin' to 'amount'
//             for (int i = coin; i <= amount; i++) {
//                 dp[i] += dp[i - coin];
//             }
//         }
        
//         return dp[amount];
//     }
// }