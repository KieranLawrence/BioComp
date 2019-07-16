package biocomputationf1;

/**
 * This interface is used to determine the fitness function for how the fitness
 * for an individual should be calculated. By placing this in an interface form,
 * we can greatly simply the code needed by reusing a large portion of the rest
 * of the genetic algorithm.
 *
 * @author K6-Lawrence
 */
public interface Function
{
	/**
	 * This is used to tell the genetic algorithm what the goal of the fitness
	 * function should be. If true, the genetic algorithm will try to maximise the
	 * fitness of the population. If false, the algorithm will try to minimise the
	 * fitness.
	 *
	 * @return True if this function wants a higher fitness, false otherwise.
	 */
	boolean shouldMaximize();

	/**
	 * This function creates a new individual with a random starting value.
	 * 
	 * @return The new individual.
	 */
	Individual randomIndividual();

	/**
	 * This method is what actually calculates the current fitness for an
	 * individual.
	 *
	 * @param individual
	 *     - The individual.
	 * @return The fitness for the given individual.
	 */
	float calculateFitness(Individual individual);
}
