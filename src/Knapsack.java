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

import java.lang.reflect.Array;
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

        // Create ArrayList of type Project
        ArrayList<Project> projects = new ArrayList<>();

        // Populate ArrayList
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

        // Format session output
        System.out.println("Number of projects = " + totalProjects);
        System.out.println("Done");

        // Create two-dimensional array to hold knapsack problem
        // In this program, the array will be horizontal, as I find it easier to populate
        int[][] knapsack = new int[totalProjects + 1][availableWorkWeeks + 1];

        // initialize base case (java does this automatically but it doesn't hurt to do it just in case)
        // Set every row of first column to 0
        for (int i = 0; i <= availableWorkWeeks; i++) {
            knapsack[0][i] = 0;
        }

        // Populate array with maximum values
        // Iterate through projects (columns)
        for (int i = 1; i <= totalProjects; i++) {
            // Iterate through work weeks (rows)
            for (int j = 0; j <= availableWorkWeeks; j++) {
                // if current project (column) needs more work weeks than currently available (row), set value to left value (base case)
                if (projects.get(i - 1).workWeeksNeeded > j) {
                    knapsack[i][j] = knapsack[i - 1][j];
                } else {
                    // Maximize potential value at given indices if available work weeks are greater than work weeks needed
                    knapsack[i][j] = Math.max(knapsack[i - 1][j - projects.get(i - 1).workWeeksNeeded] + projects.get(i - 1).netProfit, knapsack[i - 1][j]);
                }
            }
        }

        // Instantiate another ArrayList to hold solution (projects that maximize profit)
        ArrayList<Project> solution = new ArrayList<>();

        // Recover best solution
        for (int i = totalProjects; i >= 1; i--) {
            // Base case
            if (i == 0) {
                break;
            } else {
                for (int j = availableWorkWeeks; j > 1; j--) {
                    // Base case
                    if (i == 0) {
                        break;
                    }
                    // if the current maximum value was NOT gotten by including the project, add the previous project to the solution
                    if (knapsack[i - 1][j - projects.get(i - 1).workWeeksNeeded] + projects.get(i - 1).netProfit != knapsack[i][j]) {
                        break;
                    } else {
                        solution.add(projects.get(i - 1));
                        j -= projects.get(i - 1).workWeeksNeeded - 1;
                        i--;
                    }
                }
            }
        }

        // Output solution to file
        outputWriter.println("Number of projects available: " + totalProjects);
        outputWriter.println("Available employee work weeks: " + availableWorkWeeks);
        outputWriter.println("Number of projects chosen: " + solution.size());

        // Calculate profit
        int profit = 0;
        for (Project p : solution) {
            profit += p.netProfit;
        }
        outputWriter.println("Total profit: " + profit);

        // Output projects chosen
        for (Project p : solution) {
            outputWriter.println(p.toString());
        }

        outputWriter.close();
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

        // Create toString method
        public String toString() {
            return projectName + " " + workWeeksNeeded + " " + netProfit;
        }
    }
}