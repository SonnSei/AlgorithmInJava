package 树.莫里斯遍历;


import utils.TreeNode;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2019/12/25 13:17
 * @Author Cheng
 */
public class Main {
    public static void inOrderMorrisTraversal(TreeNode root) {
        TreeNode cur = root;
        TreeNode pre = null;
        StringBuilder ret = new StringBuilder();
        while (cur != null) {
            if (cur.left == null) {
                ret.append(cur.val);
                ret.append("-->");
                cur = cur.right;
            } else {
                // 找到前驱节点
                pre = cur.left;
                while(pre.right!=null && pre.right!=cur)
                    pre=pre.right;

                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    pre.right = null;
                    ret.append(cur.val);
                    ret.append("-->");
                    cur = cur.right;
                }
            }
        }
        System.out.println(ret.toString());
    }


    public static void preOrderMorrisTraversal(TreeNode root) {
        TreeNode cur = root;
        TreeNode pre = null;
        StringBuilder ret = new StringBuilder();
        while (cur != null) {
            if (cur.left == null) {
                ret.append(cur.val);
                ret.append("-->");
                cur = cur.right;
            } else {
                // 找到前驱节点
                pre = cur.left;
                while(pre.right!=null && pre.right!=cur)
                    pre=pre.right;

                if (pre.right == null) {
                    ret.append(cur.val);
                    ret.append("-->");
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    pre.right = null;
                    cur = cur.right;
                }
            }
        }
        System.out.println(ret.toString());
    }

    public static void postorderMorrisTraversal(TreeNode root) {
        TreeNode dump = new TreeNode(0);
        dump.left = root;
        TreeNode cur = dump;
        TreeNode pre = null;
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
            } else {
                pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }

                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    printReverst(cur.left, pre);
                    pre.right = null;
                    cur = cur.right;
                }
            }

        }
    }

    private static void printReverst(TreeNode from, TreeNode to) {
        reverse(from, to);
        TreeNode p = to;
        while (true) {
            System.out.print(p.val+"-->");
            if(p == from)
                break;;
            p = p.right;
        }
        reverse(to, from);
    }

    private static void reverse(TreeNode from, TreeNode to) {
        if (from == to) {
            return;
        }

        TreeNode x = from,y = to;
        TreeNode temp;
        while (true) {
            temp = y.right;
            y.right = x;
            x = y;
            y = temp;
            if (x == to) {
                break;
            }
        }
    }
}
