package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Find Missing Number(Sorted Array): You are given a sorted array of integers from 1 to n.
// There is one missing number in the array. Find the missing number. The obvious solution is
to go through all the elements and find the missing element. We can however do it faster.
Think how can we reduce the runtime of the function.
Input to Function:
Array elements
Output:
Missing element
Test Cases:
Input:-
1 2 3 4 5 7 8 9 10
Output:-
6
*/

class Solution3 {
    // (Other way to find by summation)
    // Time complexity => O(n) as we need to loop through the array for summation
    public void findMissingNumber(String input) {
        // Input array is already sorted as given on question
        String arrayElements[] = input.split(" ");
        int n = Integer.parseInt(arrayElements[arrayElements.length - 1]);
        int sum = 0;
        for(int i=0;i<arrayElements.length;i++) {
            sum = sum + Integer.parseInt(arrayElements[i]);
        }
        int totalSum = (n*(n+1))/2;
        System.out.println(totalSum - sum);
    }
    // Solution through binary search
    // Time complexity => O(log(n)) as binary search is being used.
     public void findMissingNumberByBinarySearch(String input) {
        // Input array is already sorted as given on question
        String arrayElements[] = input.split(" ");
        int leftBound = 0;
        int rightBound = arrayElements.length - 1;
        int startElemt = Integer.parseInt(arrayElements[0]);

        while (leftBound <= rightBound) {
            int midIndex = leftBound + (rightBound - leftBound) / 2;
            int expectedValue = startElemt + midIndex;

            int midElement = Integer.parseInt(arrayElements[midIndex]);
            if (midElement == expectedValue) {
                leftBound = midIndex + 1;
            } else {
                rightBound = midIndex - 1;
            }
        }
        System.out.println(startElemt + leftBound);
    }
}


public class FindMissingNumber {
    public static void main(String args[]) {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = bufferedReader.readLine();
            Solution3 s = new Solution3();
            // s.findMissingNumber(input);
            s.findMissingNumberByBinarySearch(input);
        }catch(IOException exception) {
            exception.printStackTrace();
        }
    }
}
