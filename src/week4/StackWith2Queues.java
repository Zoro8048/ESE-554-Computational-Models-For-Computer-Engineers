package week4;

/*
 * 1) Stack with 2 Queues: You are given the Queue class from the textbook (pg138). Use two
      queues to implement a Stack class with all its constituent methods (see pg120).
 */

////////////////////////////////////////////////////////////////
class Queue
{
	private int maxSize;
	private long[] queArray;
	private int front;
	private int rear;
	private int nItems;
	//--------------------------------------------------------------
	public Queue(int s) // constructor
	{
		maxSize = s;
		queArray = new long[maxSize];
		front = 0;
		rear = -1;
		nItems = 0;
	}
	//--------------------------------------------------------------
	public void insert(long j) // put item at rear of queue
	{
		if(rear == maxSize-1) // deal with wraparound
			rear = -1;
		queArray[++rear] = j; // increment rear and insert
		nItems++; // one more item
	}
	//--------------------------------------------------------------
	public long remove() // take item from front of queue
	{
		long temp = queArray[front++]; // get value and incr front
		if(front == maxSize) // deal with wraparound
			front = 0;
		nItems--; // one less item
		return temp;
	}
	//--------------------------------------------------------------
	public long peekFront() // peek at front of queue
	{

		return queArray[front];
	}
	//--------------------------------------------------------------
	public boolean isEmpty() // true if queue is empty
	{
		return (nItems==0);
	}
	//--------------------------------------------------------------
	public boolean isFull() // true if queue is full
	{
		return (nItems==maxSize);
	}
	//--------------------------------------------------------------
	public int size() // number of items in queue
	{
		return nItems;
	}
	//--------------------------------------------------------------
} // end class Queue
////////////////////////////////////////////////////////////////

class Stack {

	Queue q1;
	Queue q2;

	public Stack(int size) {
		q1 = new Queue(size);
		q2 = new Queue(size);
	}

	public void push(long data) {
		q1.insert(data);
	}

	public long pop() {
		while(!q1.isEmpty() && q1.size() != 1) {
			q2.insert(q1.remove());
		}
		long result = q1.remove();
		while(!q2.isEmpty()) {
			q1.insert(q2.remove());
		}
		return result;
	}

	public long peek() {
		while(!q1.isEmpty() && q1.size() != 1) {
			q2.insert(q1.remove());
		}
		long result = q1.peekFront();
		q2.insert(q1.remove());
		while(!q2.isEmpty()) {
			q1.insert(q2.remove());
		}
		return result;
	}

	public boolean isEmpty() {
		return q1.isEmpty();
	}

	public boolean isFull() {
		return q1.isFull();
	}
}

public class StackWith2Queues {

	public static void main(String[] args) {
		Stack theStack = new Stack(10); // make new stack
		theStack.push(20); // push items onto stack
		theStack.push(40);
		theStack.push(60);
		theStack.push(80);
		while( !theStack.isEmpty() ) // until it’s empty,
		{ // delete item from stack
			long value = theStack.pop();
			System.out.print(value); // display it
			System.out.print(" ");
		} // end while
		System.out.println("");
	}

}
