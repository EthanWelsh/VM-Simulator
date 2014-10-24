import java.io.*;

public class Main
{
    public static void main(String[] args) throws IOException
	{

		// Size of the Frame 		4Kb
		// Number of Frames 		X
		// 32 bit address space

		double sizeOfAddressSpace = Math.pow(2.0,32.0);
		int numberOfFrames = 500;
		double numberOfFrame =


		File file = new File("/Users/welshej/github/VM-Simulator/traceFiles/bzip.trace");
		BufferedReader reader = new BufferedReader(new FileReader(file));

		String line;
		while ((line = reader.readLine()) != null)
		{
			System.out.println(line);
		}
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
