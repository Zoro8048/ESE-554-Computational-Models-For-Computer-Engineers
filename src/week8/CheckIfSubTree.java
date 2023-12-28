package week8;


// Question: examine if one binary tree is a subtree in another one;

class Node {
	int data;
	Node left, right;
	Node(int data) {
		this.data = data;
		left = right = null;
	}
}

class Solution {
	boolean areIdentical(Node root1, Node root2) {
		if(root1 == null && root2 == null) {
			return true;
		}
		if(root1 == null || root2 == null) {
			return false;
		}
		return (root1.data == root2.data) && areIdentical(root1.left, root2.left) && areIdentical(root1.right, root2.right);
	}

	boolean isSubTree(Node source, Node target) {
		if(target == null) {
			return true;
		}
		if(source == null) {
			return false;
		}

		if(areIdentical(source, target)) {
			return true;
		}
		return isSubTree(source.left, target) || isSubTree(source.right, target);
	}

}

public class CheckIfSubTree {
	public static void main(String args[]) {
		Solution s = new Solution();
		/** Tree 1 is as follows
		 * 		10
		 *    4    6
		 *     30
		 */
		Node root1 = new Node(10);
		root1.left = new Node(4);
		root1.left.right = new Node(30);
		root1.right = new Node(6);
		/** Tree 2 is as follows
		 * 		4
		 *       30
		 *   
		 */
		Node root2 = new Node(4);
		root2.right = new Node(30);
		if(s.isSubTree(root1, root2)) {
			System.out.println("One binary tree is a subtree of another one");
		}else {
			System.out.println("One binary tree is not a subtree of another one");
		}
	}
}
