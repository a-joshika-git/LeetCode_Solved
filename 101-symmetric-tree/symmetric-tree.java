/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean check(TreeNode n1,TreeNode n2){
        if(n1==null && n2==null) return true;
        if(n1==null || n2==null) return false;
        if(n1.val!=n2.val) return false;
        boolean r1=check(n1.left,n2.right);
        if(r1==false) return false;
        boolean r2=check(n1.right,n2.left);
        if(r2==false) return false;
        return true;
    }
    public boolean isSymmetric(TreeNode root) {
        boolean r=check(root,root);
        return r;
    }
}