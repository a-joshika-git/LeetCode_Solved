class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        long sum = 0;
        
        // Iterate over all possible divisors k of n
        for (int k = 1; k <= n; k++) {
            if (n % k == 0) {
                if (isSortable(nums, k)) {
                    sum += k;
                }
            }
        }
        return (int) sum;
    }

    private boolean isSortable(int[] nums, int k) {
        int n = nums.length;
        int prevMax = -1;
        
        // Process each block of length k
        for (int i = 0; i < n; i += k) {
            int drops = 0;
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            
            for (int j = 0; j < k; j++) {
                int curr = nums[i + j];
                int next = nums[i + (j + 1) % k];
                
                // Count cyclic drops (where an element is greater than its next cyclic neighbor)
                if (curr > next) {
                    drops++;
                }
                if (curr < min) min = curr;
                if (curr > max) max = curr;
            }
            
            // A block can be rotated into non-decreasing order iff it has <= 1 drop
            if (drops > 1) {
                return false;
            }
            
            // Ensure non-decreasing order between consecutive blocks
            if (i > 0 && prevMax > min) {
                return false;
            }
            
            prevMax = max;
        }
        
        return true;
    }
}