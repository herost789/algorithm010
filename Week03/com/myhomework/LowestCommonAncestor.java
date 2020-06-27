package com.myhomework;

public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //当left和right同时为空，说明左右子树都不包含p，q，返回null
        if (left == null && right == null) return null;
        //p,q其中一个在root的右子树中，此时right指向 p（假设为p）；
        //p,q两节点都在root的右子树中，此时的right指向最近公共祖先节点 ；

        if (left == null) return right;
        if (right == null) return left;
        //当左右子树都不为空，说明p, q分列在root的异侧（分别在左/右子树）
        //因此root为最近公共祖先，返回root
        return root;

    }
}
