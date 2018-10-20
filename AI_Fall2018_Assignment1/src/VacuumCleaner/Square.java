// Authors: Corey Harris, Kindra Hensley, John Jacobs, Bert Sloan
// KSU ID's (respectively): 000677104, 000153288, 000523935, 000723607
// Class: CS4242 - Artificial Intelligence Online
// Assignment: Programming Assignment 1
// Due Date: 17 September 2018

package VacuumCleaner;

import java.util.ArrayList;
import java.util.List;

public class Square {

    public boolean dirty;
    //Initialize all possible directions to null
    private Square left, up, right, down = null;

    //Constructor without any parameters
    public Square ()
    {
        this.left = null;
        this.up = null;
        this.right = null;
        this.down = null;
        this.dirty = false;
    }

    //Constructor with parameters
    public Square(Square left, Square up, Square right, Square down, boolean dirty) {
        this.left = left;
        this.up = up;
        this.right = right;
        this.down = down;
        this.dirty = dirty;
    }

    //These are all the getters for the directions
    public Square GetLeft() {
        return left;
    }

    public Square GetUp() {
        return up;
    }

    public Square GetRight() {
        return right;
    }

    public Square GetDown() {
        return down;
    }

    //These are all the setters for the directions
    public void SetLeft(Square left) {
        this.left = left;
    }

    public void SetUp(Square up) {
        this.up = up;
    }

    public void SetRight(Square right) {
        this.right = right;
    }

    public void SetDown(Square down) {
        this.down = down;
    }

    //This is a list of all the available directions the vacuum can go
    public List<Integer> GetAvailableDirections()
    {
        List<Integer> options = new ArrayList<>();

        if(this.left != null)
        {
            options.add(1);
        }
        if(this.up != null)
        {
            options.add(2);
        }
        if(this.right != null)
        {
            options.add(3);
        }
        if(this.down != null)
        {
            options.add(4);
        }
        return options;
    }
}
