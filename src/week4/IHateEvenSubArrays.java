package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 3) I hate even subarrays: You are given a binary string (string which contains 0's and 1's), you
	  need to perform several operations on this string. In each operation, choose a non-empty,
	  even-length substring containing only 0's or only 1's, then remove it from the string.
	  Your goal is to minimize the final length of the string after performing several such
	  operations, each could remove a different substring meeting the requirement. It is possible
	  that the final string may become empty, in that case print "EMPTY" without quotes.
	  And it can be proved that there is always an unique string with minimal length after
	  performing such operations.
	  Input:
	  First line of input contains an integer T denoting number of testcases.
	  Next T lines of input contains a binary string S.
	  Output:
	  for each testcase print the required minimal string.
	  Constraints:
	  1 <= T <= 10
	  1 <= |S| <= 105
	  Test Cases:
	  Input:-
	  2
	  101001
	  1001
	  Output:-
	  10
	  EMPTY
	  Explanation: for the first test case, first remove substring "00", now string will become
	  "1011", now remove "11", hence "10" will be the resulting string.
 */

//stack.java
//demonstrates stacks
//to run this program: C>java StackApp
////////////////////////////////////////////////////////////////
class StackXYZ
{
	private int maxSize; // size of stack array
	private long[] stackArray;
	private int top; // top of stack
	//--------------------------------------------------------------
	public StackXYZ(int s) // constructor
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
} // end class StackXYZ

class Solution3 {
	private String empty = "EMPTY";
	public void iHateEvenSubArrays(String input) {
		StackXYZ stack = new StackXYZ(input.length());
		for(int i=0;i<input.length();i++) {
			long current = input.charAt(i) - '0';
			if(!stack.isEmpty() && stack.peek() == current) {
				stack.pop();
			} else {
				stack.push(current);
			}
		}
		if(stack.isEmpty()) {
			System.out.println(empty);
			return;
		}
		String resultReverse = "";
		while(!stack.isEmpty()) {
			resultReverse = resultReverse + stack.pop();
		}
		for(int i = resultReverse.length() - 1;i>=0;i--) {
			System.out.print(resultReverse.charAt(i));
		}
		System.out.println("");
	}
}

public class IHateEvenSubArrays {

	public static void main(String[] args) {
		try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
			int testCases = Integer.parseInt(bufferedReader.readLine());
			while(testCases != 0) {
				String input = bufferedReader.readLine();
				Solution3 s = new Solution3();
				s.iHateEvenSubArrays(input);
				testCases--;
			}
		}catch(IOException error) {
			error.printStackTrace();
		}
	}

}
