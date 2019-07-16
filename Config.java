package biocomputationf1;

/**
 * The configuration for the genetic algorithm is the set of initial properties for the
 * genetic algorithm to run on. This contains a set of default values, but can
 * be further configured before passing to the genetic algorithm.
 *
 * @author K6-Lawrence
 */
public class Config
{
	/**
	 * The size of the population.
	 */
	public int populationSize = 100;

	/**
	 * The number of generations to use.
	 */
	public int generationCount = 50;

	/**
	 * The percent chance of a mutation occurring.
	 */
	public float mutationPercent = 0.25f;

	/**
	 * The maximum number of bits which can be mutated for binary encoded genes. For
	 * real value genes, this value represents the maximum variation which can occur
	 * upon the value.
	 */
	public float mutationStrength = 25f;
	/**
	 * The percent chance of a crossover occurring.
	 */
	public float crossoverPercent = 0.25f;
}
