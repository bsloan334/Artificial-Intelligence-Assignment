// Authors: Corey Harris, Kindra Hensley, John Jacobs, Bert Sloan
// Class: CS4242 - Artificial Intelligence
// Assignment: Programming Assignment 1
// Date: 15 September 2018

/*
    This class will be used to determine where the vacuum can move
    and if it does move to a dirty square, it will clean the dirt
 */

package VacuumCleaner;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Vacuum {

    //Variables to hold the total number of moves/cleans
    private int countMoves = 0;
    private int countCleans = 0;
    public Square room;
    private int lastMove;
    public ArrayList<Square> visitedRooms = new ArrayList<Square>();
    private String direction;

    //if the room is dirty, the vacuum will clean it
    public void Clean() {
        if(room.dirty == true) {
            //clean
            room.dirty = false;
            System.out.println("Vacuum cleaned the room!");
            countCleans++;
        } else {
            System.out.println("Room is not dirty...");
        }
    }

    //this will move the vacuum left, right, up, or down randomly
    public String MoveVacuum() {
        boolean moved = false;
        // This list keeps track of the move attempts that are not the one
        // the vacuum came from or one that has already been attempted.
        // The vacuum should only attempt to move into the three
        int moveAttempts = 0;

        // Get list of directions the vacuum can move from current position
        // Limit random move generation to those options
        List<Integer> validDirections = room.GetAvailableDirections();
        if(lastMove > 0) {
            validDirections.remove(validDirections.indexOf(lastMove));
        }

        while(moved != true && moveAttempts < validDirections.size()) {

            System.out.println("Attempting to move vacuum...");

            int randomIndex  = ThreadLocalRandom.current().nextInt(validDirections.size());
            int random = validDirections.get(randomIndex);

            if(random != lastMove) {
                moved = CheckMove(random);
                if(moved)
                {
                    Clean();
                }
                moveAttempts++;
            }
            else
            {
                System.out.println("Program prevented vacuum from moving backwards");
            }
        }
        if (!moved && moveAttempts == validDirections.size()) {
            System.out.println("Vacuum has visited and cleaned all rooms!");
            direction = "finished";
        }
        return direction;
    }

    //this will check to see that the room you are trying to go is valid
    private boolean CheckMove(int move) {
        switch(move) {
            case 1:
                if(room.GetLeft() != null && visitedRooms.contains(room.GetLeft()) == false){
                    visitedRooms.add(room.GetLeft());
                    SetRoom(room.GetLeft());
                    direction = "left";
                    lastMove = 3;
                    System.out.println("Vacuum moved left");
                    countMoves++;
                    return true;
                }
                break;
            case 2:
                if(room.GetUp() != null && visitedRooms.contains(room.GetUp()) == false){
                    visitedRooms.add(room.GetUp());
                    SetRoom(room.GetUp());
                    direction = "up";
                    lastMove = 4;
                    System.out.println("Vacuum moved up");
                    countMoves++;
                    return true;
                }
                break;
            case 3:
                if(room.GetRight() != null&& visitedRooms.contains(room.GetRight()) == false){
                    visitedRooms.add(room.GetRight());
                    SetRoom(room.GetRight());
                    direction = "right";
                    lastMove = 1;
                    System.out.println("Vacuum moved right");
                    countMoves++;
                    return true;
                }
                break;
            case 4:
                if(room.GetDown() != null && visitedRooms.contains(room.GetDown()) == false){
                    visitedRooms.add(room.GetDown());
                    SetRoom(room.GetDown());
                    direction = "down";
                    lastMove = 2;
                    System.out.println("Vacuum moved down");
                    countMoves++;
                    return true;
                }
                break;

        }
        return false;
    }

    public void SetRoom(Square room) {
        this.room = room;
    }

    public int GetMoves() {
        return countMoves;
    }

    public int GetCleans() {
        return countCleans;
    }
}
