package biocomputationf1;

/**
 * This class is a basic implementation of the function which calculates the
 * fitness of an individual based on the equation "x^2", where the goal is to
 * find the highest possible fitness.
 *
 * @author K6-Lawrence
 */
public class ValueSquaredFunction implements Function
{
	@Override
	public boolean shouldMaximize()
	{
		return true;
	}

	@Override
	public Individual randomIndividual()
	{
		Individual ind = new Individual(8, 1, false);
		ind.randomiseBinary();
		return ind;
	}

	@Override
	public float calculateFitness(Individual individual)
	{
		int x = individual.getInteger(0);

		return x * x;
	}
}
