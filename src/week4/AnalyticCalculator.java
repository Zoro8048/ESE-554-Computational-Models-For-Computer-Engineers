package week4;


/*
 * 4) Arithmetic Calculator: Develop an arithmetic calculator that can take an expression of
 *    arbitrary lengths, levels of parentheses, and multi-digit numbers, and produce the final
 *    result.   
 */


// Imported class from textbook
class StackXY
{
	private int maxSize; // size of stack array
	private long[] stackArray;
	private int top; // top of stack
	//--------------------------------------------------------------
	public StackXY(int s) // constructor
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
} // end class StackXY



class Solution {

	public long performOperation(char op, long b, long a) {
		switch (op) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '/':
			if (b == 0) {
				throw new UnsupportedOperationException("Can't divide by zero");
			}
			return a / b;
		default:
			return 0;
		}
	}

	public boolean hasPrecedence(char op1, char op2) {
		if (op2 == '(' || op2 == ')') {
			return false;
		}
		if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
			return false;
		} else {
			return true;
		}
	}

	public long analyticCalculator(String expression) {
		char arr[] = expression.toCharArray();

		StackXY numbers = new StackXY(expression.length());
		StackXY operations = new StackXY(expression.length());

		for(int i=0; i < arr.length; i++) {
			if(arr[i] == ' ') {
				continue;
			}

			if(arr[i] >= '0' && arr[i] <= '9') {
				StringBuffer s = new StringBuffer();

				while(i < arr.length && arr[i] >= '0' && arr[i] <= '9') {
					s.append(arr[i++]);
				}
				
				numbers.push(Long.parseLong(s.toString()));
				i--;
			
			} else if(arr[i] == '(') {
				
				operations.push((long)arr[i]);
				
			}else if(arr[i] == ')') {
				
				while(operations.peek() != '(') {
					numbers.push(performOperation((char)operations.pop(), numbers.pop(), numbers.pop()));
				}
				operations.pop();
				
			}else if(arr[i] == '+' || arr[i] == '-' || arr[i] == '*' || arr[i] == '/') {
				
				while(!operations.isEmpty() && hasPrecedence(arr[i], (char)operations.peek())) {
					numbers.push(performOperation((char)operations.pop(), numbers.pop(), numbers.pop()));
				}
				operations.push(arr[i]);
				
			}
		}
		
		while(!operations.isEmpty()) {
			
			numbers.push(performOperation((char)operations.pop(), numbers.pop(), numbers.pop()));
			
		}

		return numbers.pop();
	}
}

public class AnalyticCalculator {

	public static void main(String[] args) {
		String input = "(100*(2+12))/14";
		Solution s = new Solution();
		System.out.println(s.analyticCalculator(input));
	}
}