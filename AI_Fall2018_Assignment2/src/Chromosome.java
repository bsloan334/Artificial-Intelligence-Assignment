/* Chromosome Class
 *
 * This class is design to contain possible solutions to the Genetic Algorithm
 * All conflicts, fitness data, and statistics are tracked
 */

import java.util.ArrayList;

public class Chromosome {
    private int NUM_QUEENS;             // Default size of -> n Queens
    public ArrayList<Integer> queenLayout;               // Contains layout of board and position of queens -> rows are indexed to list positions 0-7, and value of index indcates column
    private int crashes;                // Number of conflicts with other Queens -> Either occupying same space or in an attacking range
    private double fitnessScore;       // Score used to determine viability of board as solution
    private double matingProbability;  // Probability that a particular Chromosome will be selected for mating with another parent
    private boolean selected;          // Boolean if selected for mating

    // Default Chromosome constructor
    // Accepts integer parameter to define number of Queens
    public Chromosome(int q) {
        NUM_QUEENS = q;
        queenLayout = new ArrayList<>(NUM_QUEENS);
        crashes = 0;
        fitnessScore = 0.0;
        matingProbability = 0.0;
        selected = false;
    }

    // Comparison operator - return difference of crashes
    // Negative number implies Chromosome on left side of equation is more fit
    public int Compare(Chromosome target) {
        int diff = this.crashes - target.crashes;
        return diff;
    }

    // Counts number of crashes for a given configuration
    public void CountCrashes()
    {
        // 2D array to represent chess board layout of queens
        int boardConfig[][] = new int[NUM_QUEENS][NUM_QUEENS];
        // Rows and Columns
        int row = 0;
        int col = 0;
        // Diagonals
        // Indeces represent X or Y value of the diagonal position from target Queen
        int diagX[] = new int[] {-1, 1, -1, 1};
        int diagY[] = new int[] {-1, 1, 1, -1}
        // Temp variables
        int tempX, tempY;
        // True if diagonal is out of bounds
        boolean outOfBounds = false;
        // Crashes detected
        int conflicts = 0;

        ClearBoard(boardConfig);
        FillBoard(boardConfig);

        // Check for crashes
        for(int i = 0; i < NUM_QUEENS; i++)
        {
            row = i;
            col = queenLayout.get(i);

            // Check diagonals
            for(int k = 0; k < 4; k++)
            {
                tempX = row;
                tempY = col;

                while(!outOfBounds)
                {
                    tempX += diagX[k];
                    tempY += diagY[k];

                    // Out of Bounds
                    if((tempX < 0 || tempX >= NUM_QUEENS) || (tempY < 0 || tempY >= NUM_QUEENS))
                    {
                        outOfBounds = true;
                    }
                    else
                    {
                        if(boardConfig[tempX][tempY] == 1)
                        {
                            conflicts++;
                        }
                    }
                }
            }
            if(outOfBounds == true)
                outOfBounds = false;
        }
        this.crashes = conflicts;
    }

    private void ClearBoard(int[][] target)
    {
        for(int i = 0; i < NUM_QUEENS; i++)
        {
            for(int j = 0; j < NUM_QUEENS; j++)
            {
                target[i][j] = 0;
            }
        }
    }

    private void FillBoard(int[][] target)
    {
        for(int i = 0; i < NUM_QUEENS; i++)
        {
            target[i][queenLayout.get(i)] = 1;
        }
    }

	public double GetFitness()
	{
		return this.fitnessScore;
	}

    public void SetFitnessScore(double input)
    {
        this.fitnessScore = input;
    }

    public int GetCrashes()
    {
        return this.crashes;
    }

	public void SetCrashes(int input)
	{
		this.cras
	}

	public double GetMatingProbability()
	{
		return this.matingProbability;
	}

	public void SetMatingProbability(double input)
}