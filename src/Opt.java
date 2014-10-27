import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class OPT
{
	HashMap<Integer, ArrayList<Integer>> priorityMap;

	public OPT(String fname) throws IOException
	{

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





}
