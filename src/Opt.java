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

			int pageNumber = Integer.parseInt(splitter[0].substring(0,2), 16);

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
		int onTheChoppingBlock = -1;

		for(Object page : pagesInRam)
		{
			if(nextUsed((Integer) page) == -1)
			{
				if(ram.getPage((Integer)page).isClean()) return ((Integer)page); // If this page is never used again, evict it.
				else onTheChoppingBlock = (Integer)page;
			}
			else
			{
			  	if(nextUsed((Integer) page) > nextUsed(onTheChoppingBlock))
				{
					onTheChoppingBlock = (Integer)page;
				}
				else if(nextUsed((Integer) page) == nextUsed(onTheChoppingBlock))
				{
					if(ram.getPage((Integer)page).isClean() && !ram.getPage((Integer)onTheChoppingBlock).isClean())
					{
						onTheChoppingBlock = (Integer)page;
					}
				}
			}
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
		return"";
	}

	private int nextUsed(Integer p)
	{ // Returns the position at which this page is next used. Null if never.
		if(p == -1) return -1;
		ArrayList<Integer> a = priorityMap.get(p);
		if(a == null) return -1;
		else return a.get(0);
	}

	private void popOffFront(Integer i)
	{
	  	if(priorityMap.get(i) == null)
		{
			System.out.println("FATAL ZEBRAS");
		}
		else if(priorityMap.get(i).size() == 1)
		{
			priorityMap.put(i, null);
		}
		else
		{
			priorityMap.get(i).remove(0);
		}
	}






}
