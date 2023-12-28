package week11;

// heap.java
// demonstrates heaps
// to run this program: C>java HeapApp
import java.io.*;
////////////////////////////////////////////////////////////////
class Node
{
	private int iData; // data item (key)
	// -------------------------------------------------------------
	public Node(int key) // constructor

	{ iData = key; }
	// -------------------------------------------------------------
	public int getKey()
	{ return iData; }
	// -------------------------------------------------------------
	public void setKey(int id)
	{ iData = id; }
	// -------------------------------------------------------------
} // end class Node
////////////////////////////////////////////////////////////////
class PriorityQueue
{
	private Node[] heapArray;
	private int maxSize; // size of array
	private int currentSize; // number of nodes in array
	// -------------------------------------------------------------
	public PriorityQueue(int mx) // constructor
	{
		maxSize = mx;
		currentSize = 0;
		heapArray = new Node[maxSize]; // create array
	}
	// -------------------------------------------------------------
	public boolean isEmpty()
	{ return currentSize==0; }
	// -------------------------------------------------------------
	public boolean enqueue(int key)
	{
		if(currentSize==maxSize)
			return false;
		Node newNode = new Node(key);
		heapArray[currentSize] = newNode;
		trickleUp(currentSize++);
		return true;
	} // end insert()
	// -------------------------------------------------------------
	public void trickleUp(int index)
	{
		int parent = (index-1) / 2;
		Node bottom = heapArray[index];

		while( index > 0 &&
				heapArray[parent].getKey() < bottom.getKey() )
		{
			heapArray[index] = heapArray[parent]; // move it down
			index = parent;
			parent = (parent-1) / 2;
		} // end while
		heapArray[index] = bottom;
	} // end trickleUp()
	// -------------------------------------------------------------
	public Node dequeue() // delete item with max key
	{ // (assumes non-empty list)
		Node root = heapArray[0];
		heapArray[0] = heapArray[--currentSize];
		trickleDown(0);
		return root;
	} // end remove()
	// -------------------------------------------------------------
	public void trickleDown(int index)
	{
		int largerChild;
		Node top = heapArray[index]; // save root
		while(index < currentSize/2) // while node has at
		{ // least one child,
			int leftChild = 2*index+1;
			int rightChild = leftChild+1;
			// find larger child
			if(rightChild < currentSize && // (rightChild exists?)
					heapArray[leftChild].getKey() <
					heapArray[rightChild].getKey())
				largerChild = rightChild;
			else
				largerChild = leftChild;
			// top >= largerChild?
			if( top.getKey() >= heapArray[largerChild].getKey() )
				break;
			// shift child up
			heapArray[index] = heapArray[largerChild];
			index = largerChild; // go down
		} // end while
		heapArray[index] = top; // root to index

	} // end trickleDown()
	// -------------------------------------------------------------
	public boolean change(int index, int newValue)
	{
		if(index<0 || index>=currentSize)
			return false;
		int oldValue = heapArray[index].getKey(); // remember old
		heapArray[index].setKey(newValue); // change to new
		if(oldValue < newValue) // if raised,
			trickleUp(index); // trickle it up
		else // if lowered,
			trickleDown(index); // trickle it down
		return true;
	} // end change()
	// -------------------------------------------------------------
	public void display()
	{
		System.out.print("heapArray: "); // array format
		for(int m=0; m<currentSize; m++)
			if(heapArray[m] != null)
				System.out.print( heapArray[m].getKey() + " ");
			else
				System.out.print("-- ");
		System.out.println();
		// heap format
		int nBlanks = 32;
		int itemsPerRow = 1;
		int column = 0;
		int j = 0; // current item
		String dots = "...............................";
		System.out.println(dots+dots); // dotted top line
		while(currentSize > 0) // for each heap item
		{
			if(column == 0) // first item in row?
				for(int k=0; k<nBlanks; k++) // preceding blanks
					System.out.print(" ");
			// display item
			System.out.print(heapArray[j].getKey());

			if(++j == currentSize) // done?
				break;
			if(++column==itemsPerRow) // end of row?
			{
				nBlanks /= 2; // half the blanks
				itemsPerRow *= 2; // twice the items
				column = 0; // start over on
				System.out.println(); // new row
			}
			else // next item on row
				for(int k=0; k<nBlanks*2-2; k++)
					System.out.print(" "); // interim blanks
		} // end for
		System.out.println("\n"+dots+dots); // dotted bottom line
	} // end display()
	// -------------------------------------------------------------
} // end class Heap
////////////////////////////////////////////////////////////////
class PriorityQueueWithLogN
{
	public static void main(String[] args) throws IOException
	{
		int value, value2;
		PriorityQueue priorityQueue = new PriorityQueue(31); // make a Heap; max size 31
		boolean success;
		priorityQueue.enqueue(70); // insert 10 items
		priorityQueue.enqueue(40);
		priorityQueue.enqueue(50);
		priorityQueue.enqueue(20);
		priorityQueue.enqueue(60);
		priorityQueue.enqueue(100);
		priorityQueue.enqueue(80);
		priorityQueue.enqueue(30);
		priorityQueue.enqueue(10);
		priorityQueue.enqueue(90);
		while(true) // until [Ctrl]-[C]
		{
			System.out.print("Enter first letter of ");

			System.out.print("show, enqueue, dequeue, change: ");
			int choice = getChar();
			switch(choice)
			{
			case 's': // show
				priorityQueue.display();
				break;
			case 'e': // enqueue
				System.out.print("Enter value to insert: ");
				value = getInt();
				success = priorityQueue.enqueue(value);
				if( !success )
					System.out.println("Can’t insert; heap full");
				break;
			case 'd': // dequeue
				if( !priorityQueue.isEmpty() )
					priorityQueue.dequeue();
				else
					System.out.println("Can’t remove; heap empty");
				break;
			case 'c': // change
				System.out.print("Enter current index of item: ");
				value = getInt();
				System.out.print("Enter new key: ");
				value2 = getInt();
				success = priorityQueue.change(value, value2);
				if( !success )
					System.out.println("Invalid index");
				break;
			default:
				System.out.println("Invalid entry\n");
			} // end switch
		} // end while
	} // end main()
	//-------------------------------------------------------------
	public static String getString() throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;

	}
	//-------------------------------------------------------------
	public static char getChar() throws IOException
	{
		String s = getString();
		return s.charAt(0);
	}
	//-------------------------------------------------------------
	public static int getInt() throws IOException
	{
		String s = getString();
		return Integer.parseInt(s);
	}
	//-------------------------------------------------------------
} // end class HeapApp
////////////////////////////////////////////////////////////////