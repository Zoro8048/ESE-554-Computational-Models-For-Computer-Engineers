package week4;

/*
 * 2) Queue with 2 Stacks: You are given the Stack class from the textbook (pg120). Use two
      stacks to implement a Queue class with all its constituent methods (see pg138).
 */


//stack.java
//demonstrates stacks
//to run this program: C>java StackApp
////////////////////////////////////////////////////////////////
class StackX
{
	private int maxSize; // size of stack array
	private long[] stackArray;
	private int top; // top of stack
	//--------------------------------------------------------------
	public StackX(int s) // constructor
	{
		maxSize = s; // set array size
		stackArray = new long[maxSize]; // create array
		top = -1; // no items yet
	}
	//--------------------------------------------------------------
	public void push(long j) // put item on top of stack
	{
		stackArray[++top] = j; // increment top, insert item
	}

	//--------------------------------------------------------------
	public long pop() // take item from top of stack
	{
		return stackArray[top--]; // access item, decrement top
	}
	//--------------------------------------------------------------
	public long peek() // peek at top of stack
	{
		return stackArray[top];
	}
	//--------------------------------------------------------------
	public boolean isEmpty() // true if stack is empty
	{
		return (top == -1);
	}
	//--------------------------------------------------------------
	public boolean isFull() // true if stack is full
	{
		return (top == maxSize-1);
	}
	//--------------------------------------------------------------
} // end class StackX

class QueueX {
	private StackX s1;
	private StackX s2;
	private int nItems;

	public QueueX(int size) {
		s1 = new StackX(size);
		s2 = new StackX(size);
		nItems = 0;
	}

	public void insert(long data) {
		s1.push(data);
		nItems++;
	}

	public long remove() {
		while(!s1.isEmpty()) {
			s2.push(s1.pop());
		}
		long result = s2.pop();
		while(!s2.isEmpty()) {
			s1.push(s2.pop());
		}
		nItems--;
		return result;
	}

	public long peekFront() {
		while(!s1.isEmpty()) {
			s2.push(s1.pop());
		}
		long result = s2.peek();
		while(!s2.isEmpty()) {
			s1.push(s2.pop());
		}
		return result;
	}

	public boolean isEmpty() {
		return s1.isEmpty();
	}

	public boolean isFull() {
		return s1.isFull();
	}

	public int size() {
		return nItems;
	}

}

public class QueueWith2Stacks {

	public static void main(String[] args) {
		QueueX theQueue = new QueueX(5);
		theQueue.insert(10); // insert 4 items
		theQueue.insert(20);
		theQueue.insert(30);
		theQueue.insert(40);
		theQueue.remove(); // remove 3 items
		theQueue.remove(); // (10, 20, 30)
		theQueue.remove();
		theQueue.insert(50); // insert 4 more items
		theQueue.insert(60); // (wraps around)
		theQueue.insert(70);
		theQueue.insert(80);
		while( !theQueue.isEmpty() ) // remove and display
		{ // all items

			long n = theQueue.remove(); // (40, 50, 60, 70, 80)
			System.out.print(n);
			System.out.print(" ");
		}
		System.out.println("");
	}

}
