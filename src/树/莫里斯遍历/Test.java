package 树.莫里斯遍历;

import utils.TreeNode;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2019/12/25 13:30
 * @Author Cheng
 */
public class Test {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        Main.inOrderMorrisTraversal(node1);
        Main.preOrderMorrisTraversal(node1);
        Main.postorderMorrisTraversal(node1);
    }
}
