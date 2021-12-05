/**
 *
 * 从中序与后序遍历序列构造二叉树
 *
 * @author heyu
 * @date 2021/12/5
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {

	private int[] inorder;

	private int[] postorder;

	public TreeNode buildTree(int[] inorder, int[] postorder) {
		this.inorder = inorder;
		this.postorder = postorder;
		return buildTree(0, inorder.length - 1, 0, postorder.length - 1);
	}

	private TreeNode buildTree(int inLeft, int inRight, int postLeft, int postRight) {
		if (postLeft > postRight) {
			return null;
		}
		// 后序遍历的最后一个节点为根节点
		int rootVal = postorder[postRight];
		TreeNode root = new TreeNode(rootVal);
		// 找到中序便利中的根节点所在位置
		int m = inLeft;
		while (rootVal != inorder[m]) {
			m++;
		}
		// inLeft~m - 1 为左子树的中序便利， 那么后序遍历从左到右（m - 1） - inLeft 个节点为左子树的后序遍历
		root.left = buildTree(inLeft, m - 1, postLeft, postLeft + (m - 1 - inLeft));
		// m + 1~inRight 为右子树的中序便利，那么后序遍历从（postRight - 1）从右到左（m + 1 - right）个节点为右子树的后序遍历
		root.right = buildTree(m + 1, inRight, postRight - (inRight - m), postRight - 1);
		return root;
	}

	public static class TreeNode {

		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

}
