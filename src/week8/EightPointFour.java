package week8;

import java.util.Stack;


class Node4 {
	char data;
	Node4 leftChild, rightChild;
}

class Solution4 {
	private Node4 root;
	private Stack<Node4> stack;

	public Solution4(String input) {
		stack = new Stack<Node4>();
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*' || input.charAt(i) == '/') {
				Node4 node = new Node4();
				node.data = input.charAt(i);
				node.rightChild = stack.pop();
				node.leftChild = stack.pop();
				stack.push(node);
			} else {
				Node4 node = new Node4();
				node.data = input.charAt(i);
				stack.push(node);
			}
		}
		root = stack.pop();
	}

	public void traverse(int traverseType) {
		switch(traverseType) {
		case 1:
			System.out.print("\nPreorder traversal: ");
			preOrder(root);
			break;
		case 2:
			System.out.print("\nInorder traversal: ");
			inOrder(root);
			break;
		case 3:
			System.out.print("\nPostorder traversal: ");
			postOrder(root);
			break;
		}

		System.out.println();
	}

	private void preOrder(Node4 root) {

		if (root != null) {
			System.out.print(root.data + " ");
			preOrder(root.leftChild);
			preOrder(root.rightChild);
		}
	}

	private void inOrder(Node4 root) {

		if (root != null) {
			System.out.print("(");
			inOrder(root.leftChild);
			System.out.print(root.data + " ");
			inOrder(root.rightChild);
			System.out.print(")");
		}
	}

	private void postOrder(Node4 root) {

		if (root != null) {
			postOrder(root.leftChild);
			postOrder(root.rightChild);
			System.out.print(root.data + " ");
		}
	}

	public void displayTree() {

		Stack<Node4> outer = new Stack<Node4>();
		outer.push(root);
		int numberOfBlanks = 32;
		boolean isRowEmpty = false;

		while (isRowEmpty==false) {
			Stack<Node4> inner = new Stack<Node4>();
			isRowEmpty = true;

			for(int i = 0; i < numberOfBlanks; i++) {
				System.out.print(" ");
			}

			while (outer.isEmpty() == false) {
				Node4 temp = outer.pop();
				if (temp != null) {
					System.out.print(temp.data);
					inner.push(temp.leftChild);
					inner.push(temp.rightChild);

					if(temp.leftChild != null || temp.rightChild != null) {
						isRowEmpty = false;
					}
				} else {
					System.out.print("--");
					inner.push(null);
					inner.push(null);
				}
				for(int i = 0; i < numberOfBlanks * 2 - 2; i++) {
					System.out.print(" ");
				}
			}
			System.out.println();
			numberOfBlanks = numberOfBlanks / 2;
			while (inner.isEmpty() == false) {
				outer.push(inner.pop());
			}
		}
	}

}

public class EightPointFour {
	public static void main(String[] args) {
		Solution4 s = new Solution4("ABC/+");
		s.displayTree();
		s.traverse(1);
		s.traverse(2);
		s.traverse(3);
	}
}