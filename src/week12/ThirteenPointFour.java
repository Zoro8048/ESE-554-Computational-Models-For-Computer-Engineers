package week12;

class WarshallStack {
	private final int SIZE = 30;
	private int[] st;
	private int top;
	public WarshallStack() {
		st = new int[SIZE];
		top = -1;
	}
	public boolean isEmpty() {
		return top == -1;
	}
	public void push(int j) {
		st[++top] = j;
	}
	public int peek() {
		return st[top];
	}
	public int pop() {
		return st[top--];
	}
}

class WarshallVertex {
	public char label;
	public boolean wasVisited;
	public WarshallVertex(char label) {
		this.label = label;
		wasVisited = false;
	}
}

class ThirteenPointFour {

	private final int MAX_VERTICES = 30;
	private WarshallVertex vertexList[];
	private int adjacencyMatrix[][];
	private int numberOfVertices;
	private WarshallStack warshallStack;

	public ThirteenPointFour() {

		vertexList = new WarshallVertex[MAX_VERTICES];
		adjacencyMatrix = new int[MAX_VERTICES][MAX_VERTICES];
		numberOfVertices = 0;

		for (int i = 0; i < MAX_VERTICES; i++) {
			for (int j = 0; j < MAX_VERTICES; j++) {
				adjacencyMatrix[i][j] = 0;
			}
		}

		warshallStack = new WarshallStack();
	}

	public void addVertex(char label) {
		vertexList[numberOfVertices++] = new WarshallVertex(label);
	}

	public void addEdge(int start, int end) {
		adjacencyMatrix[start][end] = 1;
	}

	public void displayVertex(int v) {
		System.out.print(vertexList[v].label);
	}

	public void dfs(int start) {

		vertexList[start].wasVisited = true;
		displayVertex(start);
		warshallStack.push(start);
		while (!warshallStack.isEmpty()) {
			int v = getAdjUnvisitedVertex(warshallStack.peek());

			if(v == -1) {
				warshallStack.pop();
			}
			else {
				vertexList[v].wasVisited = true;
				displayVertex(v);
				warshallStack.push(v);
			}
		}

		for (int i = 0; i < numberOfVertices; i++) {
			vertexList[i].wasVisited = false;
		}
	}

	public int getAdjUnvisitedVertex(int v) {

		for (int i = 0; i < numberOfVertices; i++) {
			if (adjacencyMatrix[v][i] == 1 && vertexList[i].wasVisited == false) {
				return i;
			}
		}
		return -1;
	}

	public void displayConnectivityTable() {
		System.out.println("Connectivity table:");
		for(int j = 0; j < numberOfVertices; j++) {
			dfs(j);
			System.out.println();
		}
	}

	public int[][] warshall() {
		int[][] newAdjacencyMatrix = new int[MAX_VERTICES][MAX_VERTICES];

		for (int i = 0; i < numberOfVertices; i++) {
			for (int j = 0; j < numberOfVertices; j++) {
				newAdjacencyMatrix[i][j] = adjacencyMatrix[i][j];
			}
		}

		for(int y = 0; y < numberOfVertices; y++) {
			for(int x = 0; x < numberOfVertices; x++) {
				if(adjacencyMatrix[y][x] == 1) {
					for(int z = 0; z < numberOfVertices; z++) {
						if (adjacencyMatrix[z][y] == 1) {
							newAdjacencyMatrix[z][x] = 1;
						}
					}
				}
			}
		}

		return newAdjacencyMatrix;
	}

	public int[][] getAdjacencyMatrix() {
		return adjacencyMatrix;
	}

	public void displayMatrix(int[][] matrix) {
		System.out.println("Matrix:");
		for (int i = 0; i < numberOfVertices; i++) {
			for(int j = 0; j < numberOfVertices; j++) {
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		ThirteenPointFour graph = new ThirteenPointFour();
		graph.addVertex('A');
		graph.addVertex('B');
		graph.addVertex('C');
		graph.addVertex('D');
		graph.addVertex('E');
		graph.addVertex('F');
		graph.addEdge(0, 1);
		graph.addEdge(1, 0);
		graph.addEdge(2, 0);
		graph.addEdge(1, 4);
		graph.addEdge(2, 4);
		graph.addEdge(3, 4);
		graph.addEdge(4, 2);
		graph.addEdge(1, 5);

		graph.displayConnectivityTable();
		int[][] newAdjacencyMatrix = graph.warshall();
		graph.displayMatrix(graph.getAdjacencyMatrix());
		graph.displayMatrix(newAdjacencyMatrix);
	}
}