import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class OPT
{
	HashMap<Integer, ArrayList<Integer>> priorityMap;
	RAM ram;


	public OPT(RAM ram, String fname) throws IOException
	{
		this.ram = ram;
		priorityMap = new HashMap<Integer, ArrayList<Integer>>();
		File file = new File(fname);
		BufferedReader reader = new BufferedReader(new FileReader(file));

		String line;
		int indexInFile = 0;

		while ((line = reader.readLine()) != null)
		{
			String [] splitter = line.split(" ");

			int pageNumber = Integer.parseInt(splitter[0].substring(0,5), 16);

			if(priorityMap.containsKey(pageNumber))
			{
				priorityMap.get(pageNumber).add(indexInFile);
			}
			else
			{
				ArrayList<Integer> q = new ArrayList<Integer>();
				q.add(indexInFile);
				priorityMap.put(pageNumber, q);
			}
			indexInFile++;
		}

	}


	public int getPageToEvict()
	{
		Set pagesInRam = ram.pagesInRAM();
		Integer onTheChoppingBlock = new Integer(-1);

		for(Object page : pagesInRam)
		{ // Go through every page in ram

			Integer ipage = (Integer)page;

			if(nextUsed(ipage) == -1) // TODO nextUsed never returns -1...
			{ // If the page will never be used again...
				if(ram.getPage(ipage).isClean())
				{ // If it's clean.

					System.out.println("EVICT GOOD");
					return ipage; // If this page is never used again, evict it.
				}
				else
				{ // Otherwise, if it's dirty, keep look to see if you can find a clean page that won't be used to beat it.
					System.out.println("MAYBE EVICT?");
					onTheChoppingBlock = ipage;
				}
			}
			else
			{ // Otherwise, if the page WILL be used again in the future...
			  	if(nextUsed(ipage) > nextUsed(onTheChoppingBlock))
				{ // See if the page that we're looking at
					onTheChoppingBlock = ipage;
				}
			}
		}


		if(onTheChoppingBlock == -1)
		{
			System.out.println("Fatal error with OPT.");
			System.exit(-1);
		}

		// Removing the page from the priority map:
		popOffFront(onTheChoppingBlock);

		return onTheChoppingBlock;
	}


	public String toString()
	{
		for(Integer i : priorityMap.keySet())
		{
			System.out.println(i + ": " + priorityMap.get(i).size());
		}
		return "";
	}


	private int nextUsed(Integer p)
	{ // Returns the position at which this page is next used. Null if never.
		if(p == -1)
		{
			return Integer.MIN_VALUE;
		}

		ArrayList<Integer> a = priorityMap.get(p);

		if(a == null)
		{
			System.out.println("WAT??");
			return Integer.MAX_VALUE;
		}
		else if(a.size() == 0)
		{
            System.out.println("WAT???");
			return Integer.MAX_VALUE;
		}

		else return a.get(0);
	}


	private void popOffFront(Integer i)
	{
	  	if(priorityMap.get(i) == null)
		{
			System.out.println("FATAL ZEBRAS " + i);
		}
		else if(priorityMap.get(i).size() == 1)
		{
			//System.out.println("Setting a page to be never used again.");
			priorityMap.put(i, null);
		}
		else
		{
			priorityMap.get(i).remove(0);
    	}
	}
}
