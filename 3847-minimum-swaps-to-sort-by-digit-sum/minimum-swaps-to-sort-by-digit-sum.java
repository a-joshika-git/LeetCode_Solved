import java.util.Arrays;

public class Solution {
    
    // Helper method to calculate the sum of the digits of a number
    private static int sumOfDigits(int n) {
        int s = 0;
        while (n > 0) {
            s += n % 10;
            n /= 10;
        }
        return s;
    }

    // Helper class to store digit sum, value, and original index
    static class Element implements Comparable<Element> {
        int digitSum;
        int value;
        int originalIndex;

        public Element(int digitSum, int value, int originalIndex) {
            this.digitSum = digitSum;
            this.value = value;
            this.originalIndex = originalIndex;
        }

        @Override
        public int compareTo(Element other) {
            if (this.digitSum != other.digitSum) {
                return Integer.compare(this.digitSum, other.digitSum);
            }
            return Integer.compare(this.value, other.value);
        }
    }

    public static int minSwaps(int[] nums) {
        int n = nums.length;
        Element[] elements = new Element[n];

        // Populate the elements array with (digit_sum, value, original_index)
        for (int i = 0; i < n; i++) {
            elements[i] = new Element(sumOfDigits(nums[i]), nums[i], i);
        }

        // Sort based on the custom rules (digit sum ascending, then value ascending)
        Arrays.sort(elements);

        // Find permutation cycles to determine minimum swaps
        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            // If already visited or already in the correct position
            if (visited[i] || elements[i].originalIndex == i) {
                continue;
            }

            // Traverse the cycle
            int cycleSize = 0;
            int curr = i;

            while (!visited[curr]) {
                visited[curr] = true;
                curr = elements[curr].originalIndex;
                cycleSize++;
            }

            // A cycle of size k requires k - 1 swaps
            if (cycleSize > 0) {
                swaps += (cycleSize - 1);
            }
        }

        return swaps;
    }
}