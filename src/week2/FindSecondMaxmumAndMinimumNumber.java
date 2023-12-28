package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Find Second Maximum Number and Second Minimum Number: Given an unordered array
of n integers find the second maximum and second minimum number in the array. If there
are more than two instances of the greatest number in the array then look for the number
that is smaller than them for the second maximum. Similarly for the second minimum. If all
the numbers in the array are the same then the second maximum and second minimum
function should yield “NULL” as a result.
Input to Function:
Array elements separated by space
Output:
Second Max: x
Second Min: y
Test Cases
Input:-
1 2 4 8 7 6 3 5 9 10
Output:-
Second Max: 9
Second Min: 2
Input:-
1 1 2 3 5 8 9 6 7
Output:-
Second Max: 8
Second Min: 2
Input:-
2 2 2 2 2 2 2 2 2 2
Output:-
Second Max: NULL
Second Min: NULL
*/

class Solution4 {
    static String minPrefix = "Second Min: ";
    static String maxPrefix = "Second Max: ";
    static String nullText = "NULL";
    // Time complexity => O(n) as we're travesing through the array.
    public void findSecondMaxAndMin(String input) {
        
        String[] inputElements = input.split(" ");
        int firstMaximum = Integer.MIN_VALUE;
        int secondMaximum = firstMaximum + 1;
        int firstMinimum = Integer.MAX_VALUE;
        int secondMinimum = firstMinimum - 1;
        for(int i=0;i<inputElements.length;i++) {
            int element = Integer.parseInt(inputElements[i]);
            if(element > firstMaximum) {
                secondMaximum = firstMaximum;
                firstMaximum = element;
            }
            if(element < firstMaximum && secondMaximum < element) {
                secondMaximum = element;
            }
            if(element < firstMinimum) {
                secondMinimum = firstMinimum;
                firstMinimum = element;
            }
            if(element > firstMinimum && secondMinimum > element) {
                secondMinimum = element;
            }
        }
        if(secondMaximum == Integer.MIN_VALUE) {
            System.out.println(maxPrefix + nullText);
            System.out.println(minPrefix + nullText);
        } else {
            System.out.println(maxPrefix + secondMaximum);
            System.out.println(minPrefix + secondMinimum);
        }
    }
}

public class FindSecondMaxmumAndMinimumNumber {
    public static void main(String args[]) {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {            
            String input = bufferedReader.readLine();
            Solution4 s = new Solution4();
            s.findSecondMaxAndMin(input);
        }catch(IOException exception) {
            exception.printStackTrace();
        }
    }
}
