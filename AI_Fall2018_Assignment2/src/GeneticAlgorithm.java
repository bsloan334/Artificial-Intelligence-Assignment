import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class GeneticAlgorithm {
    // Static algorithm parameters
    private int NUM_QUEENS;     // Width and height of board equal to number of Queens in problem
    private int POPULATION_SIZE;     // Default starting population size
    private int MAX_ITERATIONS; // Maximum number of cycles before manual termination of program
    private double BREEDING_REQUIREMENT; // Percentage threshold of inbreeding of two selections

    // Instance variables
    private ArrayList<Chromosome> population;
    private ArrayList<Chromosome> solutionList;
    public Random rand = new Random();
    public int iteration;
    public int populationSize;

    // Defaul Constructor
    public GeneticAlgorithm(int num) {
        NUM_QUEENS = num;
        POPULATION_SIZE = 100;
        MAX_ITERATIONS = 1000;
        population = new ArrayList<Chromosome>();
        solutionList = new ArrayList<Chromosome>();
        rand = new Rand();
        iteration = 0;
        populationSize = 0;
    }

    public boolean Run()
    {
        boolean complete = false;
        Chromosome chromo = null;

        InitializeBoard();

        while(!complete)
        {
            populationSize = population.size();

            for(int i = 0; i < populationSize; i++)
            {
                chromo = population.get(i);
                if(chromo.GetCrashes() == 0)
                {
                    complete = true;
                }
            }
            if(iteration == MAX_ITERATIONS)
            {
                complete = true;
            }

            EvaluateFitness();
        }

        return complete;
    }

    private void InitializeBoard() {
        Chromosome newChromosome = null;
        int index = 0;
        int position = 0;

        for (int i = 0; i < NUM_QUEENS; i++) {
            newChromosome = new Chromosome(NUM_QUEENS);
            do {
                positioon = rand.nextInt(NUM_QUEENS - 1);
            } while (newChromosome.board.contains(position) == false);
            newChromosome.board.add(position);
        }
    }

    private void EvaluateFitness()
    {
        Chromosome tempChromo = null;
        double highScore = 0.0;
        double lowScore = 0.0;

        // Compute high and low score as a weighted percentage
        lowScore = Collection.max(population).GetCrashes();
        highScore = lowScore - Collection.min(population).GetCrashes();

        for(int i = 0; i < populationSize; i++)
        {
            tempChromo = population.get(i);
            tempChromo.SetFitnessScore((lowScore - tempChromo.GetCrashes()) * 100.0 / highScore);
        }
    }

	public void KWayTournamentSelection()
	{
		int numToSelect = rand.nextInt(10, 30);
		double totalFitnessScore = 0.0;
		Chromosome firstChromosome;
		Chromosome secondChromosome;
		boolean complete = false;

		for (Chromosome c : population)
		{
			totalFitnessScore += c.GetFitness();
		}
		totalFitnessScore *= 0.01;


	}
}