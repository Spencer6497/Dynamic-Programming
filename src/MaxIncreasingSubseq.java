/*
    Spencer Peace
    CSC 364-001
    HW2: Dynamic Programming
    Dr. Jeff Ward
    This program prompts the user for a string and displays a maximum length increasing subsequence of characters.
 */

import java.util.*;
import java.io.*;

public class MaxIncreasingSubseq {
    public static void main(String[] args) {
        // Create initial char array to hold string
        char[] string;

        // Create scanner object to read user input as string
        Scanner scanner = new Scanner(System.in);

        // Ask the user for input
        System.out.print("Enter a string: ");
        string = scanner.next().toCharArray();

        // Test
        for (char c : string) {
            System.out.print(c);
        }

    }
}
