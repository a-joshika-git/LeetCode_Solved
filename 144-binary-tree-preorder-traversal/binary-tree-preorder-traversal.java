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
    public List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<Integer> l=new LinkedList<>();
        preorder(l,root);
        return l;
    }
    public void preorder(LinkedList l,TreeNode root)
    {
        if(root==null)
        {
            return;
        }
        l.add(root.val);
        preorder(l,root.left);
        preorder(l,root.right);
    }
}