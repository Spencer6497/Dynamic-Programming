/*
    Spencer Peace
    CSC 364-001
    HW2: Dynamic Programming
    Dr. Jeff Ward

    This program prompts the user for a number of work weeks available, the name of the
    input and output files, reads the available projects from the input file, solves the
    corresponding knapsack problem (without repetition), and writes to the output file
    a summary of the results, including expected profit and a list of the best projects
    for the company to undertake.
*/

import java.util.*;
import java.io.*;

public class Knapsack {
    public static void main(String[] args) throws FileNotFoundException {
        // Declare int var to hold available work weeks, total number of projects
        int availableWorkWeeks, totalProjects = 0;

        // Declar string var to hold input and output files
        String inputFile, outputFile;

        // Prompt user for work weeks
        System.out.print("Enter the number of available employee work weeks: ");

        // Instantiate new scanner object that takes user command-line input
        Scanner userInput = new Scanner(System.in);

        // Grab user input and parse as int, save to variable
        String workWeekString = userInput.next();
        availableWorkWeeks = Integer.parseInt(workWeekString);

        // Prompt user for input and output filenames, save to variables
        System.out.print("Enter the name of the input file: ");
        inputFile = userInput.next();
        System.out.print("Enter the name of the output file: ");
        outputFile = userInput.next();

        // Instantiate new scanner object that takes file input
        Scanner inputScanner = new Scanner(new File(inputFile));
        // Instantiate new printwriter object that outputs to a file
        PrintWriter outputWriter = new PrintWriter(new File(outputFile));

        // Create ArrayList of type Project, populate ArrayList
        ArrayList<Project> projects = new ArrayList<>();
        while (inputScanner.hasNextLine()) {
            // Increment totalProjects
            totalProjects++;
            // Grab each line, split into parts
            String line = inputScanner.nextLine();
            String[] splitLine = line.split(" ");
            // Create project object for each line and add to projects
            projects.add(new Project(splitLine[0], Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2])));
        }
        // Close input scanner
        inputScanner.close();

        System.out.println("Number of projects = " + totalProjects);
        System.out.println("Done");

        // Test

    }
    // Create Project class to hold projects
    private static class Project {
        // Create instance variables
        String projectName;
        int workWeeksNeeded, netProfit;

        // Create constructor
        private Project(String name, int workWeeks, int profit) {
            // Set instance variables
            this.projectName = name;
            this.workWeeksNeeded = workWeeks;
            this.netProfit = profit;
        }
    }
}