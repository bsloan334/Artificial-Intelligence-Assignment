// Authors: Corey Harris, Kendra Hensley, John Jacobs, Bert Sloan
// Class: CS4242 - Artificial Intelligence
// Assignment: Programming Assignment 1
// Date: 15 September 2018
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
