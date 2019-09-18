/*
    Spencer Peace
    CSC 364-001
    HW2: Dynamic Programming
    Dr. Jeff Ward
    This program prompts the user for a string and displays a maximum length increasing subsequence of characters.
    This program is entirely longer than it needs to be, but I cannot seem to shorten it and it works.
 */

import java.util.*;
import java.io.*;
import java.lang.Math.*;

import static java.util.Arrays.*;
import static java.util.List.*;

public class MaxIncreasingSubseq {
    public static <string> void main(String[] args) {
        // Create initial char array to hold string
        char[] string;

        // Create scanner object to read user input as string
        Scanner scanner = new Scanner(System.in);

        // Ask the user for input
        System.out.print("Enter a string: ");
        string = scanner.next().toCharArray();

        // Create initial int arrays to hold score and prev
        int[] score = new int[string.length], prev = new int[string.length];

        // Populate score array with default score of 1
        fill(score, 1);

        // Populate prev array with default index -1
        fill(prev, -1);

        // Initialize max score and index of max score
        int max = 0, maxIndex = 0;

        // Calculate scores
        for (int i = 1; i < string.length; i++) {
            for (int j = 0; j < i; j++) {
                // if element at j is < element at i, increment prev and score
                if (string[j] < string[i]) {
                    // Increment prev
                    if ((score[j] + 1) > score[i]) {
                        prev[i] = j;
                    }
                    // Increment score
                    score[i] = Math.max(score[i], score[j] + 1);
                }
            }
            // Compute max score, index of max score
            if (max < score[i]) {
                max = score[i];
                maxIndex = i;
            }
        }

        // Create arraylist to hold correctly sorted longest increasing subsequence
        ArrayList<Character> result = new ArrayList<Character>();

        // populate arraylist with correct result
        while (maxIndex > -1) {
            result.add(0, string[maxIndex]);
            maxIndex = prev[maxIndex];
        }

        // print out correct result
        for(char c : result) {
            System.out.print(c);
        }
    }
}
