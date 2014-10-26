import java.util.*;

public class RAM
{

	private static int NO_EVICT = 0;
	private static int EVICT_CLEAN = 0;
	private static int EVICT_DIRTY = 0;
	private static int TOTAL_MEMORY_ACCESSES = 0;

	private static final int OPTIMAL = 0;
	private static final int CLOCK = 1;
	private static final int NRU = 2;
	private static final int RANDOM = 3;

	private static int EVICT_METHOD = 3;





	int numberOfFrames = 0;
	public HashMap<Integer, Page> frames;

	// *********************************************************************************//
	// 										CONSTRUCTORS                                //
	// *********************************************************************************//

	// @ numberOfFrames: The amount of frames you'd like to be able to exist in RAM at any given time (as pages)
	public RAM(int numberOfFrames)
	{
		this.numberOfFrames = numberOfFrames;
		frames = new HashMap<Integer, Page>(this.numberOfFrames);
	}

	public RAM(int numberOfFrames, int method)
	{
		EVICT_METHOD = method;
		this.numberOfFrames = numberOfFrames;
		frames = new HashMap<Integer, Page>(this.numberOfFrames);
	}

	// *********************************************************************************//
	// 										PUBLIC METHODS                              //
	// *********************************************************************************//

	public void read(int frameNum)
	{
	 	if(!containsPage(frameNum)) put(frameNum);
		TOTAL_MEMORY_ACCESSES++;
	}

	public void write(int frameNum)
	{
		if(!containsPage(frameNum)) put(frameNum); // TODO: Should page start a dirty?
		else frames.get(frameNum).setDirty();
		TOTAL_MEMORY_ACCESSES++;
	}

	public void printStats()
	{
		System.out.println("Number of frames:\t" + numberOfFrames);
		System.out.println("Total memory accesses:\t" + TOTAL_MEMORY_ACCESSES);
		System.out.println("Total page faults:\t" + EVICT_CLEAN + EVICT_DIRTY);
		System.out.println("Total writes to disk:\t" + EVICT_DIRTY);
	}

	public String toString()
	{
		String s = "";

		for(Integer i : frames.keySet())
		{
			if(frames.get(i).isClean()) s += (i + "\t C");
			else s += i + "\t D";
			s += "\n";
		}
		return s;
	}

	public int size()
	{
		return numberOfFrames;
	}

	public Set pagesInRAM()
	{
		return frames.keySet();
	}

	// *********************************************************************************//
	// 									EVICTION METHODS                                //
	// *********************************************************************************//
	private int random()
	{ // pick a page at random to evict from memory

		Set keyset = frames.keySet();
		int randomNumber = (new Random()).nextInt(frames.size());

		int pageToEvict = ((Integer) keyset.toArray()[randomNumber]).intValue();
		//System.out.println("I'm evicting page " + pageToEvict);
		return pageToEvict;

	}

	// *********************************************************************************//
	// 									PRIVATE METHODS                                 //
	// *********************************************************************************//


	private void put(int pageToAdd)
	{ // attempt to put a given frame into RAM. If there isn't room, evict a page to make room for it.
		if(isRoom() == false)
		{
			evictPage(findEvictee(EVICT_METHOD));
			frames.put(pageToAdd, new Page(pageToAdd));
		}
		else
		{
			frames.put(pageToAdd, new Page(pageToAdd));
			NO_EVICT++;
		}
	}

	private boolean containsPage(int x)
	{ // see if a given frame is present in RAM
		if(frames.containsKey(x)) return true;
		else return false;
	}

	private boolean isRoom()
	{ // determine if RAM is currently full
		return frames.size() < numberOfFrames;
	}

	// findEvictee: decide which page to evict given the method for eviction OPT|NRU|CLOCK|RANDOM
	private int findEvictee(int method)
	{
		switch(method)
		{
			case OPTIMAL:
				System.out.println("Optimal not supported at this time.");
				System.exit(0);
				break;
			case CLOCK:
				System.out.println("Clock not supported at this time.");
				System.exit(0);
				break;
			case NRU:
				System.out.println("Clock not supported at this time.");
				System.exit(0);
				break;
			case RANDOM:
				return random();
		}
		return -1;
	}

	private void evictPage(int x)
	{
		boolean ret = frames.get(x).isClean();
		frames.remove(x);

		if(ret == true) EVICT_CLEAN++;
		else EVICT_DIRTY++;
	}


}
