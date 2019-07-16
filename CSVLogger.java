package biocomputationf1;

import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVLogger implements Closeable
{
	private FileWriter writer;

	public CSVLogger(File file) throws IOException
	{
		writer = new FileWriter(file);

		logHeader();
	}

	private void logHeader()
	{
		try
		{
			writer.append("Best,");
			writer.append("Average,");
			writer.append("Worst\n");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void logGeneration(float bestFitness, float averageFitness, float worstFitness)
	{
		try
		{
			writer.append(bestFitness + ",");
			writer.append(averageFitness + ",");
			writer.append(worstFitness + "\n");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws IOException
	{
		writer.flush();
		writer.close();
	}
}
