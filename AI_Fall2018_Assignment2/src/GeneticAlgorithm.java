import java.util.*;

public class GeneticAlgorithm {
    // Static algorithm parameters
    private int NUM_QUEENS;              // Width and height of board equal to number of Queens in problem
    private int MAX_ITERATIONS;          // Maximum number of cycles before manual termination of program
    private int POPULATION_SIZE;         // Default size of the initial population
    private double BREEDING_REQUIREMENT; // Percentage threshold of inbreeding of two selections
    private int OFFSPRING;               // Maximum number of offspring created during crossover

    // Instance variables
    private ArrayList<Chromosome> population = new ArrayList<Chromosome>();
    private List<Chromosome> solutionList;
    public Random rand = new Random();
    public int iteration;
    public int populationSize;

    // Defaul Constructor
    public GeneticAlgorithm(int num) {
        NUM_QUEENS = num;
        POPULATION_SIZE = 50;
        MAX_ITERATIONS = 1000;
        BREEDING_REQUIREMENT = 0.75;
        OFFSPRING = 20;
        population = new ArrayList<Chromosome>();
        solutionList = new ArrayList<Chromosome>();
        rand = new Random();
        iteration = 0;
        populationSize = 0;
    }

    public boolean Run()
    {
        boolean complete = false;
        Chromosome chromo;
        ArrayList<Chromosome> result;
        for(int a = 0; a < POPULATION_SIZE; a++) {
            InitializeBoard();
        }
        while(!complete)
        {
            populationSize = population.size();

            for(int i = 0; i < populationSize; i++)
            {
                chromo = population.get(i);
                if(chromo.GetCrashes() == 0)
                {
                    if(CheckSolutions(chromo) == false)
                        solutionList.add(chromo);
                }
            }
            if(iteration == MAX_ITERATIONS)
            {
                complete = true;
            }


            EvaluateFitness();

            result = KWayTournamentSelection(3);
            population = result;
            populationSize = population.size();

            result = Crossover();
            population = result;
            populationSize = population.size();

            Mutation();
            for(Chromosome c : population)
            {
                c.CountCrashes();
            }

            Repopulate();
            iteration++;
            System.out.println("Iteration: " + iteration);
            System.out.println("Solutions Found: " + solutionList.size());
        }

        return complete;
    }

    private boolean CheckSolutions(Chromosome check)
    {
        Chromosome temp;
        for (int i = 0; i < solutionList.size(); i++)
        {
            temp = solutionList.get(i);
            if(temp.queenLayout.equals(check.queenLayout))
                return true;
        }
        return false;
    }

    private void InitializeBoard() {
        Chromosome newChromosome = new Chromosome(NUM_QUEENS);
        ArrayList<Integer> randList = new ArrayList<Integer> ();
        int position, value;

        for(int i = 0; i <= 7; i++)
        {
            randList.add(i);
        }

        for (int i = 0; i < NUM_QUEENS; i++) {
            if(randList.size() > 1) {
                position = rand.nextInt(randList.size() - 1);
            }
            else
            {
                position = 0;
            }
            value = randList.get(position);
            randList.remove(position);
            newChromosome.queenLayout.add(value);
        }
        newChromosome.CountCrashes();
        population.add(newChromosome);
    }

    private void EvaluateFitness()
    {
        Chromosome tempChromo;
        double highScore;
        double lowScore;

        // Compute high and low score as a weighted percentage
        lowScore = GetLowScore(population);
        highScore = lowScore - GetHighScore(population);

        for(int i = 0; i < populationSize; i++)
        {
            tempChromo = population.get(i);
            tempChromo.SetFitnessScore((lowScore - tempChromo.GetCrashes()) * 100.0 / highScore);
        }
    }

    private double GetHighScore(ArrayList<Chromosome> inputList)
    {
        double bestScore = 28;
        Chromosome temp;
        for(int i = 0; i < populationSize; i++)
        {
            temp = population.get(i);
            if(temp.GetCrashes() < bestScore) {
                bestScore = temp.GetCrashes();
            }
        }
        return bestScore;
    }

    private double GetLowScore(ArrayList<Chromosome> inputList)
    {
        double worstScore = 0;
        Chromosome temp;
        for(int i = 0; i < populationSize; i++)
        {
            temp = population.get(i);
            if(temp.GetCrashes() > worstScore)
            {
                worstScore = temp.GetCrashes();
            }
        }
        return worstScore;
    }

	public ArrayList<Chromosome> KWayTournamentSelection(int k)
	{
        ArrayList<Chromosome> newPopulation = new ArrayList<Chromosome>();
        ArrayList<Chromosome> tempArray = new ArrayList<Chromosome>();
		Chromosome tempChromosome;
		int index = 0;
		int selectedIndex = 0;

		while(population.size() > 1)
        {
            tempArray.clear();
            double max = 0;
            for (int i = 0; i < k; i++)
            {
                if(population.size() > 0) {
                    if (population.size() > 1) {
                        index = rand.nextInt(population.size() - 1);
                    } else {
                        index = 0;
                    }
                    tempChromosome = population.get(index);
                    tempArray.add(tempChromosome);
                }
            }

            for(int j = 0; j < tempArray.size(); j++)
            {
                tempChromosome = tempArray.get(j);
                if(tempChromosome.GetFitness() > max) {
                    max = tempChromosome.GetFitness();
                    selectedIndex = j;
                }
            }
            newPopulation.add(tempArray.get(selectedIndex));
            population.remove(index);
        }

        return newPopulation;
	}

	public ArrayList<Chromosome> Crossover()
    {
        int mateProbability = 0;
        int indexParentA, indexParentB;
        int crossoverPoint;
        Chromosome chromosomeA, chromosomeB;
        int crossA, crossB;
        ArrayList<Chromosome> newPopulation = new ArrayList<Chromosome>();

        for (int i = 0; i < population.size(); i++)
        {
            indexParentA = rand.nextInt(population.size());
            mateProbability = rand.nextInt(100);

            if(mateProbability <= BREEDING_REQUIREMENT * 100)
            {
                do {
                    indexParentB = rand.nextInt(population.size());
                } while (indexParentB == indexParentA);
                chromosomeA = population.get(indexParentA);
                chromosomeB = population.get(indexParentB);
                crossoverPoint = rand.nextInt(7);

                for (int j = 0; j < crossoverPoint; j++)
                {
                    crossA = chromosomeA.queenLayout.get(j);
                    crossB = chromosomeB.queenLayout.get(j);
                    chromosomeA.SetQueenPosition(j, crossB);
                    chromosomeB.SetQueenPosition(j, crossA);
                }
                if(indexParentA < population.size())
                    population.remove(indexParentA);
                if(indexParentB < population.size())
                    population.remove(indexParentB);
                newPopulation.add(chromosomeA);
                newPopulation.add(chromosomeB);
            }
        }
        return newPopulation;
    }

    public void Mutation()
    {
        int mutationIndex;
        int value;
        Chromosome temp;
        for (int i = 0; i < population.size(); i++)
        {
            temp = population.get(i);
            mutationIndex = rand.nextInt(7);
            value = temp.queenLayout.get(mutationIndex);
            if(value != 7)
            {
                temp.queenLayout.set(mutationIndex, value + 1);
                population.set(i, temp);
            }
        }
    }

    public void Repopulate()
    {
        for(int i = population.size(); i < POPULATION_SIZE; i++)
        {
             InitializeBoard();
        }
    }

    public int GetRandom(int min, int max) {
        return (int)Math.round((max - min) * rand.nextDouble() + min);
    }
}