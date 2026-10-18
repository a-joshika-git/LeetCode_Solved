import java.util.ArrayList;
import java.util.List;

public class Solution {
    private static final int MOD = 1000000007;
    private long[][] table;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        table = new long[n + 1][n + 1];
        
        // Step 1: Precompute Pascal's Triangle for combinations C(n, k)
        for (int i = 0; i <= n; i++) {
            table[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                table[i][j] = (table[i - 1][j - 1] + table[i - 1][j]) % MOD;
            }
        }

        // Step 2: Convert array to a List for easy sub-list manipulation
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        // Subtract 1 because the problem asks for *other* ways to reorder (excluding the original array)
        return (int) (countWays(list) - 1 + MOD) % MOD;
    }

    private long countWays(List<Integer> nums) {
        if (nums.size() <= 2) {
            return 1;
        }

        int root = nums.get(0);
        List<Integer> leftSubtree = new ArrayList<>();
        List<Integer> rightSubtree = new ArrayList<>();

        // Partition elements into left and right subtrees based on the BST property
        for (int i = 1; i < nums.size(); i++) {
            int val = nums.get(i);
            if (val < root) {
                leftSubtree.add(val);
            } else {
                rightSubtree.add(val);
            }
        }

        long leftWays = countWays(leftSubtree);
        long rightWays = countWays(rightSubtree);

        int leftSize = leftSubtree.size();
        int rightSize = rightSubtree.size();

        // Combinations formula: C(leftSize + rightSize, leftSize) % MOD
        long combinations = table[leftSize + rightSize][leftSize];

        // Multiply combinations by the number of ways to form left and right subtrees
        long totalWays = (combinations * leftWays) % MOD;
        totalWays = (totalWays * rightWays) % MOD;

        return totalWays;
    }
}