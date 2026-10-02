class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList();
        backtrack(0,0,"",n,res);
        return res;
    }
    void backtrack(int op, int cp, String s, int n, List<String> res)
    {
        if(op == cp && op+cp == n*2)
        {
            res.add(s);
            return;
        }
        if(op<n)
        {
            backtrack(op+1,cp,s+"(",n,res);
        }
        if(cp<op)
        {
            backtrack(op,cp+1,s+")",n, res);
        }
    }
}