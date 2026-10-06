class Solution {
    public int minAddToMakeValid(String s) {
        int openNeeded = 0;   // Tracks unmatched '(' needing a ')'
        int closeNeeded = 0;  // Tracks unmatched ')' needing a '('
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                openNeeded++;
            } else { // c == ')'
                if (openNeeded > 0) {
                    openNeeded--; // Matches with an existing '('
                } else {
                    closeNeeded++; // No matching '(', so we need a new '('
                }
            }
        }
        
        return openNeeded + closeNeeded;
    }
}