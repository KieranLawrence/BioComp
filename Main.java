package biocomputationf1;

import java.io.File;
import java.io.IOException;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		// Create a new config object.
		Config config = new Config();

		// We can further configure the config here before running the
		// genetic algorithm.
		// config.populationSize = 250;

		// Create a new genetic algorithm instance with the given config.
		GeneticAlgorithm ga = new GeneticAlgorithm(config, new ValueSquaredFunction());

		// Define the output file.
		File file = new File("output.csv");
		CSVLogger csv = new CSVLogger(file);

		// Solve the genetic algorithm.
		ga.solve(csv);

		// Always close the file writer!
		csv.close();
	}
}
