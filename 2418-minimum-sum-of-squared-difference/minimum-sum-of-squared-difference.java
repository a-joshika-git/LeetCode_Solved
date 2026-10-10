class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int maxDiff = 0;
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        long k = (long) k1 + k2;
        int[] count = new int[maxDiff + 1];
        for (int d : diff) {
            count[d]++;
        }

        for (int i = maxDiff; i > 0; i--) {
            if (count[i] > 0) {
                long take = Math.min(k, count[i]);
                count[i] -= (int) take;
                count[i - 1] += (int) take;
                k -= take;
                if (k == 0) break;
            }
        }

        long result = 0;
        for (int i = 1; i <= maxDiff; i++) {
            if (count[i] > 0) {
                result += (long) i * i * count[i];
            }
        }
        return result;
    }
}