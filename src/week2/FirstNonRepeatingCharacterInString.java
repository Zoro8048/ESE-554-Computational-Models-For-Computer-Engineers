package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
First Non Repeating character in a string: Strings can be thought of as character arrays. With
that thought in mind if you are given a String (all lowercase letters), find the first nonrepeating character in the String(character array). First non-repeating character means if
there are more than one non repeating character in the array you return which comes first
in the array. If there are no repeating characters then return “NULL”.
Input to Function:
String to be checked
Output:
First non-repeating character
Test Cases:
Input:-
computerengineer
Output:-
c
Input:-
electricalengineer
Output:-
t
Input:-
anna
Output:-
NULL
 */

class Solution5 {
    static char baseElement = 'a';
    static char resultInitializer = 'A';
    static String nullText = "NULL";
    // Time complexity => O(n) as we're travesing through array.
    public void findFirstNonRepeatingCharacterInString(String input) {
        int arr[] = new int[26];
        for(int i=0;i<input.length();i++) {
            char currentElement = input.charAt(i);
            arr[currentElement - baseElement]++;
        }
        char result = resultInitializer;
        for(int i=0;i<input.length();i++) {
            char currentElement = input.charAt(i);
            if(arr[currentElement - baseElement] == 1) {
                result = currentElement;
                break;
            }
        }
        if(result == resultInitializer) {
            System.out.println(nullText);
        }else {
            System.out.println(result);
        }
    }
}

public class FirstNonRepeatingCharacterInString {
    public static void main(String args[]) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = bufferedReader.readLine();
            Solution5 s = new Solution5();
            s.findFirstNonRepeatingCharacterInString(input);
        }catch(IOException exception) {
            exception.printStackTrace();
        }
    }
}
