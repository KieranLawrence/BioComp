package biocomputationf1;

/**
 * This represents an individual within the population.
 *
 * @author K6-Lawrence
 */
public class Individual implements Comparable<Individual>
{
	private boolean[] binary;
	private float realValue;
	private int intSize;
	private int intCount;
	private boolean signedInt;
	private float fitness;

	/**
	 * Creates a new individual using a float encoding.
	 */
	public Individual()
	{
	}

	/**
	 * Creates a new individual using a binary encoding.
	 *
	 * @param integerSize
	 *     - The number of bits per integer.
	 * @param integerCount
	 *     - The number of integers.
	 * @param signedInt
	 *     - Whether or not the integers are signed.
	 */
	public Individual(int integerSize, int integerCount, boolean signedInt)
	{
		intSize = integerSize;
		intCount = integerCount;
		binary = new boolean[integerSize * integerCount];
		this.signedInt = signedInt;
	}

	/**
	 * Gets the value of this individual as a float value.
	 *
	 * @return The real value of this individual.
	 */
	public float getRealValue()
	{
		return realValue;
	}

	/**
	 * Gets the value of this individual as an integer. Because an individual can
	 * contain one or two integer values, (x or y), the index of the integer must be
	 * supplied. 0 for x, and 1 for y.
	 *
	 * @param index
	 *     - The index of the integer to get.
	 * @return The int value of the integer.
	 */
	public int getInteger(int index)
	{
		int i = 0;

		// Calculate the needed size and offset of the int within the binary string.
		int size = intSize - (signedInt ? 1 : 0);
		int offset = intSize * index;

		// This loop uses simple bit operations to turn the boolean array into an int.
		for (int a = 0; a < size; a++)
			i |= (binary[a + offset] ? 1 : 0) << a;

		// If the int is signed, flip the value based on the state of the last binary
		// bit.
		if (signedInt)
			if (binary[intSize - 1 + offset])
				i = -i;

		return i;
	}

	/**
	 * Checks if this individual represents a binary encoding.
	 *
	 * @return True if this individual is a binary encoding, false otherwise.
	 */
	public boolean isBinaryEncoding()
	{
		return binary != null;
	}

	/**
	 * Creates a mutated copy of this individual, given the settings presented in
	 * the config.
	 *
	 * @param config
	 *     - The config to determine mutation settings.
	 * @return A mutated copy of this individual.
	 */
	public Individual mutate(Config config)
	{
		Individual ind;

		if (isBinaryEncoding())
		{
			// Create a new binary individual
			ind = new Individual(intSize, intCount, signedInt);

			// Copy our genes to other individual
			for (int i = 0; i < binary.length; i++)
				ind.binary[i] = binary[i];

			// Mutate
			int mutatedBits = (int) (Math.random() * config.mutationStrength) + 1;
			for (int i = 0; i < mutatedBits; i++)
			{
				int bitIndex = (int) (Math.random() * binary.length);
				ind.binary[bitIndex] = !ind.binary[bitIndex];
			}
		}
		else
		{
			// Create a new float individual
			ind = new Individual();

			// Copy our genes
			ind.realValue = realValue;

			// Mutate
			ind.realValue += (Math.random() * 2f - 1f) * config.mutationStrength;
		}

		return ind;
	}

	/**
	 * Creates a crossover child with this individual and another individual based
	 * on the given configuration settings. This can only be used with binary
	 * encoded genes.
	 *
	 * @param parentB
	 *     - The other individual to crossover with.
	 * @return A new individual which contains crossed over genes between this and
	 *     another individual.
	 */
	public Individual crossover(Individual parentB)
	{
		Individual ind = new Individual(intSize, intCount, signedInt);

		// Copy over genes randomly
		for (int i = 0; i < binary.length; i++)
		{
			boolean parentA = Math.random() >= 0.5;
			ind.binary[i] = parentA ? binary[i] : parentB.binary[i];
		}

		return ind;
	}

	/**
	 * Gets the fitness for this individual.
	 *
	 * @return The fitness
	 */
	public float getFitness()
	{
		return fitness;
	}

	/**
	 * Sets the fitness for this individual.
	 *
	 * @param fitness
	 *     - The new fitness value.
	 */
	public void setFitness(float fitness)
	{
		this.fitness = fitness;
	}

	/**
	 * Randomises the binary encoding for this individual.
	 */
	public void randomiseBinary()
	{
		for (int i = 0; i < binary.length; i++)
			binary[i] = Math.random() >= 0.5;
	}

	/**
	 * Randomises the initial real value for this individual, within the given max
	 * range.
	 *
	 * @param max
	 *     - The maximum value.
	 */
	public void randomiseFloat(float max)
	{
		realValue = (float) ((Math.random() * 2f - 1f) * max);
	}

	@Override
	public int compareTo(Individual o)
	{
		return Float.compare(fitness, o.fitness);
	}
}
