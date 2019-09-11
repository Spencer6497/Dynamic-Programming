/*
    Spencer Peace
    CSC 364-001
    HW2: Dynamic Programming
    Dr. Jeff Ward
    This program prompts the user for a string and displays a maximum length increasing subsequence of characters.
 */

import java.util.*;
import java.io.*;
import java.lang.Math.*;

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

        /* Initialize score array; may be unnecessary
        for (int i = 0; i < string.length; i++) {
            score[i] = 1;
        } */

        // Hold max #
        int max = 1;

        // Calculate scores
        for (int i = 0; i < string.length; i++) {
            for (int j = 0; j < i; j++) {
                score[i] = 1 + Math.max(score[j]:  j < i, string[j] < string[i]);
            }
        }

        /* Test
        for (int i : score) {
            System.out.print(i);
        } */

    }
}
