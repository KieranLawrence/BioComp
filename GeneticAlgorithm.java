package biocomputationf1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The genetic algorithm class is the main processing class for the algorithm,
 * which handles all major operations for solving the problem.
 *
 * @author K6-Lawrence
 */
public class GeneticAlgorithm
{
	private Config config;
	private Function function;

	public GeneticAlgorithm(Config config, Function function)
	{
		this.config = config;
		this.function = function;
	}

	public void solve(CSVLogger csv)
	{
		// Create the population
		List<Individual> individuals = new ArrayList<>(config.populationSize);
		for (int i = 0; i < config.populationSize; i++)
			individuals.add(function.randomIndividual());

		// Iterate
		for (int i = 0; i < config.generationCount; i++)
		{
			// Calculate fitness
			for (int j = 0; j < individuals.size(); j++)
			{
				Individual ind = individuals.get(i);
				ind.setFitness(function.calculateFitness(ind));
			}

			// Log fitness
			float best = individuals.get(0).getFitness();
			float average = 0;
			float worst = best;

			for (int j = 0; j < individuals.size(); j++)
			{
				float fitness = individuals.get(j).getFitness();

				if (function.shouldMaximize())
				{
					best = Math.max(fitness, best);
					worst = Math.min(fitness, worst);
				}
				else
				{
					best = Math.min(fitness, best);
					worst = Math.max(fitness, worst);
				}

				average += fitness;
			}
			average /= individuals.size();
			csv.logGeneration(best, average, worst);

			System.out.println("Finished Generation: " + (i + 1));
			System.out.println("  Best: " + best);
			System.out.println("  Average: " + average);
			System.out.println("  Worst: " + worst);

			// Sort based on fitness
			Collections.sort(individuals);

			if (function.shouldMaximize())
				Collections.reverse(individuals);

			// Crop the lowest percent of the population
			int toCrop = (int) ((config.mutationPercent + config.crossoverPercent) * config.populationSize);
			for (int j = 0; j < toCrop; j++)
				individuals.remove(individuals.size() - 1 - j);

			// Calculate top
			int top = individuals.size();

			// Mutate percent of remaining
			int toMutate = (int) (config.mutationPercent * config.populationSize);
			for (int j = 0; j < toMutate; j++)
			{
				Individual parent = individuals.get((int) (Math.random() * top));
				individuals.add(parent.mutate(config));
			}

			// Crossover percent of remaining
			int toCrossover = (int) (config.crossoverPercent * config.populationSize);
			for (int j = 0; j < toCrossover; j++)
			{
				Individual parentA = individuals.get((int) (Math.random() * top));
				Individual parentB = individuals.get((int) (Math.random() * top));

				individuals.add(parentA.crossover(parentB));
			}
		}
	}
}
