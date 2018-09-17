// Authors: Corey Harris, Kindra Hensley, John Jacobs, Bert Sloan
// KSU ID's (respectively): 000677104, 000153288, 000523935, 000723607
// Class: CS4242 - Artificial Intelligence Online
// Assignment: Programming Assignment 1
// Due Date: 17 September 2018

import VacuumCleaner.Environment;
// Main entry point of program
public class Main
{
    public static void main(String[]args)
    {
        int x = 2;
        int y = 2;
        // Initialize environment for program
        System.out.println("Creating a new environment with " + x + "x" + y + " rooms.");
        Environment env = new Environment(x, y);

        // NOTE: This program is assuming that no square will become dirty after it has been
        // cleaned, so the vacuum will be able to maintain a list of cleaned rooms and report
        // back when it is finished.
        env.Run();
    }
}
