package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
Find Range: Given an unordered array of integers find the range of values in the array.
Input to Function:
n – size of array
Array elements separated by space
Output Format:
Range of Values
Test Cases:
Input:-
5
10 8 6 1 7
Output:-
9
Input:-
10
1 8 2 9 4 5 10 5 6 12
Output:-
11
Input:-
8
5 2 10 15 8 4 2 6
Output:-
13
*/

class Solution2 {
    public int getMaximum(int arr[]) {
        int maximum = Integer.MIN_VALUE;
        for(int i = 0;i<arr.length;i++) {
            if(maximum < arr[i]) {
                maximum = arr[i];
            }
        }
        return maximum;
    }
    public int getMinimum(int arr[]) {
        int minimum = Integer.MAX_VALUE;
        for(int i = 0;i<arr.length;i++) {
            if(minimum > arr[i]) {
                minimum = arr[i];
            }
        }
        return minimum;
    }
    public void printRange(int maximum, int minimum) {
        System.out.println(maximum - minimum);
    }
}

public class FindRange {
    public static void main(String args[]) {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {            
            int n = Integer.parseInt(bufferedReader.readLine());
            String arrayInput = bufferedReader.readLine();
            String arrayElements[] = arrayInput.split(" ");
            int arr[] = new int[n];
            for( int i=0;i< n;i++) {
                arr[i] = Integer.parseInt(arrayElements[i]);
            }
            Solution2 s = new Solution2();
            int maxElement = s.getMaximum(arr);
            int minElement  = s.getMinimum(arr);
            s.printRange(maxElement, minElement);
        }catch(IOException exception) {
            exception.printStackTrace();
        }
    }
}
