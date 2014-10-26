import java.io.*;

public class Main
{
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


		RAM ram = new RAM(5);


		File file = new File(traceFile);
		BufferedReader reader = new BufferedReader(new FileReader(file));

		String line;
		while ((line = reader.readLine()) != null)
		{
			String [] splitter = line.split(" ");
			int pageNumber = Integer.parseInt(splitter[0].substring(0,2), 16);

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

		System.out.println("\n" + ram);
		ram.printStats();



		/*CircularQueue q = new CircularQueue();
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
		q.enqueue(5);

		System.out.println(q + "\n");



		System.out.println(q); */




	}







	/*
	 * hit, page fault – no eviction
	 * page fault – evict clean
	 * page fault – evict dirty
	 * -------------------------
	 * Number of frames:       8
	 * Total memory accesses:  1000000
     * Total page faults:      181856
     * Total writes to disk:   29401
	 *
	 */

}
