// Authors: Corey Harris, Kindra Hensley, John Jacobs, Bert Sloan
// Class: CS4242 - Artificial Intelligence
// Assignment: Programming Assignment 1
// Date: 15 September 2018

/*
    This class will be used to determine where the vacuum can move
    and if it does move to a dirty square, it will clean the dirt
 */

package VacuumCleaner;

public class Vacuum {

    //Variables to hold the total number of moves/cleans
    private int countMoves = 0;
    private int countCleans = 0;
    private Square room;

    //if the room is dirty, the vacuum will clean it
    public void Clean() {
        if(room.dirty == true) {
            //clean
            room.dirty = false;
            countCleans++;
        } else {
            //do we need to do anything else?
        }
    }

    //this will move the vacuum left, right, up, or down randomly
    public void MoveVacuum() {
        boolean moved = false;

        while(moved != true) {
            int random = (int)((Math.random() * 4) + 1);
            moved = CheckMove(random);
            countMoves++;
        }
    }

    //this will check to see that the room you are trying to go is valid
    private boolean CheckMove(int room) {
        switch(room) {
            case 1:
                if(room.GetLeft() != null){
                    SetRoom(room.GetLeft());
                    return true;
                }
                break;
            case 2:
                if(room.GetUp() != null){
                    SetRoom(room.GetUp());
                    return true;
                }
                break;
            case 3:
                if(room.GetRight() != null){
                    SetRoom(room.GetRight());
                    return true;
                }
                break;
            case 4:
                if(room.GetDown() != null){
                    SetRoom(room.GetDown());
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
