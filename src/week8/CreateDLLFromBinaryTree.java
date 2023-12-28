package week8;

//	Question: given a binary tree, 
//		create a doubly linked list which is the in order traversal of the tree
class Node1 {
	int data;
	Node1 left, right;
	Node1(int data) {
		this.data = data;
		left = right = null;
	}
}

class Solution1 {
	Node1 modify(Node1 node)  
	{ 
		if (node == null) 
			return node; 

		if (node.left != null)  
		{ 
			Node1 left = modify(node.left); 
			while(left.right != null) {
				left = left.right;
			}
			left.right = node;
			node.left = left;
		} 
		if (node.right != null)  
		{ 
			Node1 right = modify(node.right);
			while(right.left != null) {
				right = right.left;
			}
			right.left = node;
			node.right = right;
		} 
		return node; 
	}

	Node1 treeToDoublyLinkedList(Node1 node) {
		if(node == null) {
			return node;
		}
		node = modify(node);
		while(node.left != null) {
			node = node.left;
		}
		return node;
	}

	void display(Node1 head) {
		System.out.println("Doubly linked list for the tree");
		Node1 current = head;
		while(current != null) {
			System.out.print(current.data + " ");
			current = current.right;
		}
	}
}

public class CreateDLLFromBinaryTree {
	public static void main(String[] args) {
		/** Input tree is as follows
		 * 				3
		 * 			2		6
		 *		1	   4 5		7
		 *
		 */
		Node1 root = new Node1(3);
		root.left = new Node1(2);
		root.left.left = new Node1(1);
		root.left.right = new Node1(4);
		root.right = new Node1(6);
		root.right.left = new Node1(5);
		root.right.right = new Node1(7);
		Solution1 s = new Solution1();
		Node1 dllHead = s.treeToDoublyLinkedList(root);
		s.display(dllHead);
	}

}
