// Authors: Corey Harris, Kindra Hensley, John Jacobs, Bert Sloan
// Class: CS4242 - Artificial Intelligence
// Assignment: Programming Assignment 1
// Date: 15 September 2018

package VacuumCleaner;

import java.util.ArrayList;
import java.util.List;

public class Square {

    public boolean dirty;
    private Square left, up, right, down = null;

    public Square ()
    {
        this.left = null;
        this.up = null;
        this.right = null;
        this.down = null;
        this.dirty = false;
    }

    public Square(Square left, Square up, Square right, Square down, boolean dirty) {
        this.left = left;
        this.up = up;
        this.right = right;
        this.down = down;
        this.dirty = dirty;
    }

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
