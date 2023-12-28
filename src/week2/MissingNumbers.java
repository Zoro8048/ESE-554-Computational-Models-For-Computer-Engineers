package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Missing Numbers: An Array has numbers from 1 to n (unordered). There are some missing
numbers in the array. Print the missing numbers.
Inputs to Function:
n – highest integer of the array
Array elements separated by space
Output Format:
Numbers missing in the array separated by space or one after the other.
Test Cases:
Input:-
10
1 2 3 4 6 7 8 9 10
Output:-
5
Input:-
10
1 2 4 5 6 7 9 10
Output:-
3 8
Input:-
6
1 2 4 5 6
Output:-
3
*/

class Solution1 {
    // Time and space complexity => O(n)
    public void findMissingNumbers(String n, String inputArray) {
        boolean numCheck[] = new boolean[Integer.parseInt(n)];
        String elements[] = inputArray.split(" ");
        for(int i=0;i<elements.length;i++) {
            int num = Integer.parseInt(elements[i]);
            numCheck[num - 1] = true;
        }
        String result = "";
        for(int i = 0;i<numCheck.length;i++) {
            if(!numCheck[i]) {
                result += (i + 1) + " ";
            }
        }
        if(result.isEmpty()) {
            System.out.println("No missing elements");
        }else {
            System.out.println(result);
        }
    }
}

class MissingNumbers {
    public static void main(String args[]) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String n = bufferedReader.readLine();
            String arrayInput = bufferedReader.readLine();
            Solution1 s = new Solution1();
            s.findMissingNumbers(n, arrayInput);
        }catch(IOException exception) {
            exception.printStackTrace();
        }
    }
}
