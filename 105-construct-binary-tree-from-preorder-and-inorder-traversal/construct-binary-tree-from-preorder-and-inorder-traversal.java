class Solution {
    private int preorderIndex = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        return helper(preorder, inorder, 0, inorder.length - 1);
    }

    private TreeNode helper(int[] preorder, int[] inorder, int left, int right) {
        // Base case: if there are no elements to construct the tree
        if (left > right) {
            return null;
        }

        // Get the current root value from preorder and advance the pointer
        int rootVal = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootVal);

        // Find the root's index in inorder manually within the current bounds
        int mid = left;
        while (inorder[mid] != rootVal) {
            mid++;
        }

        // Build the left and right subtrees recursively
        root.left = helper(preorder, inorder, left, mid - 1);
        root.right = helper(preorder, inorder, mid + 1, right);

        return root;
    }
}