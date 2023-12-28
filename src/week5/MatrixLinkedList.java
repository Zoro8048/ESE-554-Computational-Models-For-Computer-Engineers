package week5;

class MatrixNode {
	int value;
	MatrixNode right;
	MatrixNode down;

	public MatrixNode(int value) {
		this.value = value;
		this.right = null;
		this.down = null;
	}
}

class MatrixLinkedList {
	MatrixNode topLeft;
	int rowCount;
	int columnCount;

	MatrixLinkedListIterator matrixLinkedListIterator;

	public MatrixLinkedListIterator getMatrixLinkedListIterator() {
		return matrixLinkedListIterator;
	}

	public MatrixLinkedList(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.topLeft = new MatrixNode(0);
		matrixInit();
	}

	private void matrixInit() {
		MatrixNode currentRow = topLeft;
		for(int i = 0; i < rowCount; i++) {
			MatrixNode currentColumn = currentRow;
			for(int j = 0; j < columnCount - 1; j++) {
				currentColumn.right = new MatrixNode(0);
				currentColumn = currentColumn.right;
			}
			if(i < rowCount - 1) {
				currentRow.down = new MatrixNode(0);
				currentRow = currentRow.down;
			}
		}
		matrixLinkedListIterator = new MatrixLinkedListIterator(this);
	}

	public void insert(int row, int col, int key) {
		if(row < 0 || row >= rowCount || col < 0 || col >= columnCount) {
			System.out.print("Out of bound");
			return;
		}
		MatrixNode current = topLeft;
		for(int i = 0; i < row; i++) {
			current = current.down;
		}
		for(int j = 0; j < col; j++) {
			current = current.right;
		}
		current.value = key;
	}

	public void delete(int row, int col) {
		if (row < 0 || row >= rowCount || col < 0 || col >= columnCount) {
			System.out.println("Invalid row or column index.");
			return;
		}

		MatrixNode current = topLeft;
		MatrixNode prev = null;

		// Traverse to the node to be deleted
		for (int i = 0; i < row; i++) {
			prev = current;
			current = current.down;
		}
		for (int j = 0; j < col; j++) {
			prev = current;
			current = current.right;
		}

		// Check if the node exists
		if (current == null) {
			System.out.println("Link does not exist at the specified location.");
			return;
		}

		// Skip the deleted node
		if (prev != null) {
			prev.right = current.right;
		} else {
			// Deletion is in the first row
			topLeft = current.right;
		}

		if (row < rowCount - 1) {
			// Skip the deleted node in the down direction
			current = topLeft;
			for (int i = 0; i < row + 1; i++) {
				current = current.down;
			}
			prev = current;
			for (int j = 0; j < col; j++) {
				prev = current;
				current = current.right;
			}
			prev.down = current.down;
		}
	}

	public void displayMatrix() {
		System.out.println("\nDisplay Start\n");
		MatrixNode current = topLeft;
		while(current != null) {
			MatrixNode columnNode = current;
			while(columnNode != null) {
				boolean lastElement = columnNode.right == null;
				System.out.print(columnNode.value + (lastElement ? "" :" -> "));
				columnNode = columnNode.right;
			}
			System.out.println("");
			current = current.down;
		}
		System.out.println("\nDisplay End\n");
	}

}

class MatrixLinkedListIterator {

	int row;
	int column;
	MatrixNode current;
	MatrixLinkedList matrixLinkedList;

	MatrixLinkedListIterator(MatrixLinkedList matrixLinkedList) {

		row = column = 1;
		current = matrixLinkedList.topLeft;
		this.matrixLinkedList = matrixLinkedList;
	}

	public MatrixNode up() {
		current = goToNode(row - 1, column);
		return current;
	}

	public MatrixNode down() {
		current = goToNode(row + 1, column);
		return current;
	}

	public MatrixNode left() {
		current = goToNode(row, column - 1);
		return current;
	}

	public MatrixNode right() {
		current = goToNode(row, column + 1);
		return current;
	}

	public int get() {
		return current.value;
	}

	public void insert(int key) {
		System.out.println("Inserting " + key + " at row "+ row +" and column " + column + " in the matrix list");
		current.value = key;
	}

	public void delete() {
		System.out.println("Deleting " + current.value + " at row "+ row +" and column " + column +
				" from the matrix list");
		current.value = -1;
	}

	public void display() {
		matrixLinkedList.displayMatrix();
	}

	private MatrixNode goToNode(final int row, final int column) {

		if (row <= 0 || column <= 0 || row > matrixLinkedList.rowCount || column > matrixLinkedList.columnCount) {
			System.out.println("row " + row + " and column " + column + " are out of bounds");
			return current;
		}

		MatrixNode listNode = matrixLinkedList.topLeft;

		for (int i = 1; i < row; i++) {
			listNode = listNode.down;
		}

		for (int i = 1; i < column; i++) {
			listNode = listNode.right;
		}

		current = listNode;
		this.row = row;
		this.column = column;

		return current;
	}
}

class Matrix {
	public static void main(String args[]) {
		MatrixLinkedList matrix = new MatrixLinkedList(3, 3);
		matrix.displayMatrix();
		matrix.insert(0, 0, 1);
		matrix.insert(0, 1, 2);
		matrix.insert(0, 2, 3);
		matrix.insert(1, 0, 4);
		matrix.insert(1, 1, 5);
		matrix.insert(1, 2, 6);
		matrix.insert(2, 0, 7);
		matrix.insert(2, 1, 8);
		matrix.insert(2, 2, 9);
		matrix.displayMatrix();
		matrix.delete(2, 2);
		matrix.displayMatrix();
		matrix.delete(1, 1);
		matrix.displayMatrix();

		MatrixLinkedList matrixLinkedList2 = new MatrixLinkedList(3, 3);
		MatrixLinkedListIterator matrixLinkedListIterator = matrixLinkedList2.getMatrixLinkedListIterator();
		matrixLinkedListIterator.display();

		matrixLinkedListIterator.insert(1);

		matrixLinkedListIterator.right();
		matrixLinkedListIterator.insert(2);

		matrixLinkedListIterator.right();
		matrixLinkedListIterator.insert(3);

		matrixLinkedListIterator.down();
		matrixLinkedListIterator.insert(6);

		matrixLinkedListIterator.left();
		matrixLinkedListIterator.insert(5);

		matrixLinkedListIterator.left();
		matrixLinkedListIterator.insert(4);

		matrixLinkedListIterator.down();
		matrixLinkedListIterator.insert(7);

		matrixLinkedListIterator.right();
		matrixLinkedListIterator.insert(8);

		matrixLinkedListIterator.right();
		matrixLinkedListIterator.insert(9);

		matrixLinkedListIterator.display();

		matrixLinkedListIterator.right();
		matrixLinkedListIterator.delete();
		matrixLinkedListIterator.display();

	}
}