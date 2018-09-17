package VacuumCleaner;// Authors: Corey Harris, Kindra Hensley, John Jacobs, Bert Sloan
// Class: CS4242 - Artificial Intelligence
// Assignment: Programming Assignment 1
// Date: 15 September 2018
import java.util.Random;
// VacuumCleaner.Environment class
// This class is designed to define a new set of bounds, which will determine
// the world in which the vacuum is capable of operating
public class Environment
{
    private int horizontalCount;
    //private int verticalCount;
    private Square rooms[];
    private Random rand = new Random();

    // Boundaries Constructor
    // <Parameters> The Boundaries class takes two parameters, which are used to
    // define the height and width of the environment. </Parameters>
    public Environment(int x)
    {
        if (x > 0)
        {
            horizontalCount = x;
            //verticalCount = y;
        }
    }

    /*public Environment(VacuumCleaner.Square array[])
    {
        if (inputList.length > 0)
        {
            rooms = array;
            horizontalCount = array.length;
            //verticalCount = array[0].length;
        }
    }*/

    private void Run()
    {
        // Add two equal sized rooms to the environment
        PopulateEnvironment();
    }

    // Populate VacuumCleaner.Environment Method
    // <Description> This method creates the layout of the environment,
    // by placing two equal-sized areas next to each other, each
    // equaling half of the total area of the environment. </Description>
    // <Parameters> None </Parameters>
    private void PopulateEnvironment()
    {
        rooms = new VacuumCleaner.Square[horizontalCount];
        InitializeRooms();
    }

    // Initialize Rooms Method
    // <Description> This method is intended to create the initial state of each room
    // Upon initialization, the environment will assign the room a random bool dirtiness value
    // and apply the linked relationships between rooms
    // <Parameters> None </Parameters>
    private void InitializeRooms()
    {
        // Iterate over rooms to create new VacuumCleaner.Square object
        // Set for only two rooms currently
        if(rooms.length > 1) {
            for (int i = 0; i < rooms.length; i++) {
                Square currentRoom = new Square();
                rooms[i] = currentRoom;

                // If not in the corner room, set current and previous square accordingly
                if(i > 0) {
                    SetLeftRight(i);
                }

                // Assign room a dirtiness value
                currentRoom.dirty = GetRoomCondition();
            }
        }
    }

    // Set Left Right Method
    // <Description> Assign the current square's left relation to the previous room,
    // and assign that square's right relation to the current square. </Description>
    // <Parameters> int index - specifies index of current square in rooms array </Parameters>
    private void SetLeftRight(int index)
    {
        Square square = rooms[index];
        square.SetLeft(rooms[index-1]);
        rooms[index-1].SetRight(square);
    }

    // Set Room Condition
    // <Description> Randomly assign room a value for dirty or clean
    // Boolean value set to true if dirty </Description>
    // <Parameters> None </Parameters>
    private boolean GetRoomCondition()
    {
        // Retrieve random value between 0.0 and 1.0
        // If in lower half [0, 0.5), evaluate as zero -> set state to clean
        // If in upper half [0.5, 1.0), evaluate as one -> set state to dirty
        if(rand.nextInt() < 0.5)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}