// Authors: Corey Harris, Kindra Hensley, John Jacobs, Bert Sloan
// KSU ID's (respectively): 000677104, 000153288, 000523935, 000723607
// Class: CS4242 - Artificial Intelligence Online
// Assignment: Programming Assignment 1
// Due Date: 17 September 2018

package VacuumCleaner;
import java.util.Random;


// VacuumCleaner.Environment class
// This class is designed to define a new set of bounds, which will determine
// the world in which the vacuum is capable of operating
public class Environment
{
    private int horizontalCount;
    private int verticalCount;
    private Square rooms[][];
    private Vacuum vacuum = new Vacuum();
    private Random rand = new Random();

    // Boundaries Constructor
    // <Parameters> The Boundaries class takes two parameters, which are used to
    // define the height and width of the environment. </Parameters>
    public Environment(int x, int y) {
        if (x > 0) {
            horizontalCount = x;
            verticalCount = y;
        }
    }

    public void Run()
    {
        System.out.println("Running vacuum program...");
        // Fill the environment with rooms and assign a dirty or clean value
        PopulateEnvironment();

        Square startingRoom = rooms[0][0];
        // Place vacuum in the first room
        System.out.println("Placing vacuum in first room...");
        vacuum.room = startingRoom;
        vacuum.visitedRooms.add(startingRoom);

        // Try to clean the initial room
        System.out.println("Start cleaning!");
        vacuum.Clean();

        // String to hold direction of movement or "finished" if all rooms are clean
        String state = "";

        // Continually try to move the vacuum while the room is not clean
        while (!state.equals("finished")) {
            String direction = vacuum.MoveVacuum();
            if(direction == "finished"){
               state = "finished";
            }
        }
        System.out.println("Vacuum cleaned the environment in " + vacuum.GetMoves() +
                " moves, cleaning " + vacuum.GetCleans() + " rooms!");
    }

    // Populate VacuumCleaner.Environment Method
    // <Description> This method creates the layout of the environment,
    // by placing two equal-sized areas next to each other, each
    // equaling half of the total area of the environment. </Description>
    // <Parameters> None </Parameters>
    private void PopulateEnvironment()
    {
        System.out.println("Adding new rooms to environment!");
        rooms = new VacuumCleaner.Square[horizontalCount][verticalCount];
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
                for(int j = 0; j < rooms[0].length; j++) {
                    Square currentRoom = new Square();
                    System.out.println("Room at position (" + i + ", " + j + ") created!");
                    rooms[i][j] = currentRoom;

                    // If not in the corner room, set current and previous square accordingly
                    if (j > 0 ) {
                        SetLeftRight(i, j);
                    }
                    if(i > 0) {
                        SetUpDown(i, j);
                    }

                    // Assign room a dirtiness value
                    currentRoom.dirty = GetRoomCondition();
                    System.out.println("Room at position (" + i + ", " + j + ") successfully initialized!");
                }
            }
        }
    }

    // SetLeftRight Method
    // <Description> Assign the current square's left relation to the previous room,
    // and assign that square's right relation to the current square. </Description>
    // <Parameters> int index - specifies index of current square in rooms array </Parameters>
    private void SetLeftRight(int x, int y)
    {
        System.out.println("Setting left and right relationships for room at position (" + x + ", " + y + ")");
        Square square = rooms[x][y];
        square.SetLeft(rooms[x][y-1]);
        rooms[x][y-1].SetRight(square);
    }

    // SetUpDown Method
    // <Description> Assign the current square's up relation to the equvalent column's row in previous row
    // and assign that square's down relation to the current square. </Description>
    // <Parameters> int index - specifies index of current square in rooms array </Parameters>
    private void SetUpDown(int x, int y)
    {
        System.out.println("Setting up and down relationships for room at position (" + x + ", " + ")");
        Square square = rooms[x][y];
        square.SetUp(rooms[x-1][y]);
        rooms[x-1][y].SetDown(square);
    }

    // SetRoomCondition Method
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
            System.out.println("This room is clean.");
            return false;
        }
        else
        {
            System.out.println("This room is dirty.");
            return true;
        }
    }
}