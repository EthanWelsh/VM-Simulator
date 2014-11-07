import java.io.*;

public class Main
{

	private static final int OPTIMAL = 0;
	private static final int CLOCK = 1;
	private static final int NRU = 2;
	private static final int RANDOM = 3;

    public static void main(String[] args) throws IOException
	{ // ./vmsim –n <numberOfFrames> -a <opt|clock|nru|rand> [-r <refresh>] <tracefile>

		int numframes = 0;
		String algo = "";
		int refreshRate = 0;
		String traceFile = "";

		traceFile = args[args.length-1];

		for(int i = 0; i < args.length; i++)
		{
			if(args[i].equals("-n")) numframes = Integer.parseInt(args[i+1]);
			else if(args[i].equals("-a")) algo = args[i + 1];
			else if(args[i].equals("-r")) refreshRate = Integer.parseInt(args[i+1]);
		}

		System.out.println("Number of Frames: " + numframes);
		System.out.println("Algorithm: " + algo);
		System.out.println("Refresh Rate: " + refreshRate);
		System.out.println("Trace File: " + traceFile);

		// Size of the Frame 		4Kb
		// Number of Frames 		X
		// 32 bit address space

		int algo_int = 0;

		if(algo.equalsIgnoreCase("OPT"))
		{
			algo_int = OPTIMAL;
		}
		else if(algo.equalsIgnoreCase("RAND"))
		{
			algo_int = RANDOM;
		}
		else if(algo.equalsIgnoreCase("NRU"))
		{
			algo_int = NRU;
		}
		else if(algo.equalsIgnoreCase("CLOCK"))
		{
			algo_int = CLOCK;
		}


		RAM ram = new RAM(numframes, algo_int, refreshRate, traceFile);


		File file = new File(traceFile);
		BufferedReader reader = new BufferedReader(new FileReader(file));

		String line;
		while ((line = reader.readLine()) != null)
		{
			String [] splitter = line.split(" ");
			int pageNumber = Integer.parseInt(splitter[0].substring(0,5), 16);

			if(splitter[1].equals("R"))
			{
				ram.read(pageNumber);
			}
			else if(splitter[1].equals("W"))
			{
				ram.write(pageNumber);
			}
			else System.out.println("Houston, we have a problem.");
		}

		ram.printStats();

		return;

	}
}
