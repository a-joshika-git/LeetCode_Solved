import java.util.Stack;

class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0); // Base score for the current scope
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(0);
            } else {
                int innerScore = stack.pop();
                int outerScore = stack.pop();
                // If innerScore is 0, it represents "()", scoring 1.
                // Otherwise, it represents "(A)", scoring 2 * innerScore.
                stack.push(outerScore + Math.max(2 * innerScore, 1));
            }
        }
        
        return stack.peek();
    }
}