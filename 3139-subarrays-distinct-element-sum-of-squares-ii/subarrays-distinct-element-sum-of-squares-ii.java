import java.util.HashMap;
import java.util.Map;

public class Solution {
    private static final int MOD = 1000000007;

    static class SegmentTree {
        int n;
        long[] sum;      // Sum of counts
        long[] sumSq;    // Sum of squares of counts
        long[] lazy;     // Lazy propagation values

        public SegmentTree(int n) {
            this.n = n;
            this.sum = new long[4 * n];
            this.sumSq = new long[4 * n];
            this.lazy = new long[4 * n];
        }

        private void push(int node, int l, int r) {
            if (lazy[node] != 0) {
                long v = lazy[node];
                int mid = (l + r) / 2;
                apply(2 * node, l, mid, v);
                apply(2 * node + 1, mid + 1, r, v);
                lazy[node] = 0;
            }
        }

        private void apply(int node, int l, int r, long v) {
            long len = (r - l + 1);
            // (x + v)^2 = x^2 + 2*v*x + v^2
            sumSq[node] = (sumSq[node] + 2 * v * sum[node] % MOD + v * v % MOD * len % MOD) % MOD;
            sum[node] = (sum[node] + v * len % MOD) % MOD;
            lazy[node] = (lazy[node] + v) % MOD;
        }

        public void update(int node, int l, int r, int ql, int qr, long v) {
            if (ql > r || qr < l) return;
            if (ql <= l && r <= qr) {
                apply(node, l, r, v);
                return;
            }
            push(node, l, r);
            int mid = (l + r) / 2;
            update(2 * node, l, mid, ql, qr, v);
            update(2 * node + 1, mid + 1, r, ql, qr, v);
            sum[node] = (sum[2 * node] + sum[2 * node + 1]) % MOD;
            sumSq[node] = (sumSq[2 * node] + sumSq[2 * node + 1]) % MOD;
        }

        public long querySumSq() {
            return sumSq[1];
        }
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        SegmentTree tree = new SegmentTree(n);
        Map<Integer, Integer> lastSeen = new HashMap<>();
        long totalSumSq = 0;

        for (int j = 0; j < n; j++) {
            int prev = lastSeen.getOrDefault(nums[j], -1);
            // Update the range [prev + 1, j] by adding 1 to the distinct counts
            tree.update(1, 0, n - 1, prev + 1, j, 1);
            lastSeen.put(nums[j], j);
            
            // Add the sum of squares of all subarrays ending at j
            totalSumSq = (totalSumSq + tree.querySumSq()) % MOD;
        }

        return (int) totalSumSq;
    }
}