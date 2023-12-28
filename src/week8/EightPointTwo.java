package week8;

import java.util.Stack;

class Node3 {
	char data;
	Node3 leftChild, rightChild;
	Node3(char data) {
		this.data = data;
		leftChild = rightChild = null;
	}
}

class Solution3 {
	private Node3 root;
	public Solution3(String input) {
		root = null;
		Node3[] arr = new Node3[input.length()];
		for(int i = 0; i < input.length(); i++) {
			arr[i] = new Node3(input.charAt(i));
		}
		int noOfElements = input.length();
		while(noOfElements > 1) {
			int fillCounter = 0;
			for(int i = 0; i < noOfElements; i++) {
				if(i % 2 == 1) {
					Node3 node = new Node3('+');
					node.leftChild = arr[i - 1];
					node.rightChild = arr[i];
					arr[fillCounter] = node;
					fillCounter++;
				}
				if (i % 2 == 0 && i == noOfElements - 1) {
					arr[fillCounter] = arr[i];
				}
			}
			noOfElements =  noOfElements - fillCounter;
		}
		root = arr[0];
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

	private void preOrder(Node3 root) {

		if (root != null) {
			System.out.print(root.data + " ");
			preOrder(root.leftChild);
			preOrder(root.rightChild);
		}
	}

	private void inOrder(Node3 root) {

		if (root != null) {
			inOrder(root.leftChild);
			System.out.print(root.data + " ");
			inOrder(root.rightChild);
		}
	}

	private void postOrder(Node3 root) {

		if (root != null) {
			postOrder(root.leftChild);
			postOrder(root.rightChild);
			System.out.print(root.data + " ");
		}
	}

	public void displayTree() {

		Stack<Node3> outer = new Stack<Node3>();
		outer.push(root);
		int numberOfBlanks = 32;
		boolean isRowEmpty = false;

		while (isRowEmpty==false) {
			Stack<Node3> inner = new Stack<Node3>();
			isRowEmpty = true;

			for(int j = 0; j < numberOfBlanks; j++) {
				System.out.print(" ");
			}

			while (outer.isEmpty() == false) {
				Node3 temp = outer.pop();
				if (temp != null) {
					System.out.print(temp.data);
					inner.push(temp.leftChild);
					inner.push(temp.rightChild);

					if (temp.leftChild != null || temp.rightChild != null) {
						isRowEmpty = false;
					}
				} else {
					System.out.print("--");
					inner.push(null);
					inner.push(null);
				}

				for(int j = 0; j < numberOfBlanks * 2 - 2; j++) {
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

public class EightPointTwo {

	public static void main(String[] args) {
		String input = "ABCDEF";
		Solution3 s = new Solution3(input);
		s.displayTree();
		s.traverse(1);
		s.traverse(2);
		s.traverse(3);
	}
}