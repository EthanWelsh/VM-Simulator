import java.io.IOException;
import java.util.*;

public class RAM
{
	private static int NO_EVICT = 0;
	private static int EVICT_CLEAN = 0;
	private static int EVICT_DIRTY = 0;
	private static int TOTAL_MEMORY_ACCESSES = 0;
	private static int PAGE_FAULTS = 0;


	private static final int OPTIMAL = 0;
	private static final int CLOCK = 1;
	private static final int NRU = 2;
	private static final int RANDOM = 3;

	private static int EVICT_METHOD = 3;

	int numberOfFrames = 0;
	public HashMap<Integer, Page> frames;
	int timeout = 0;
	String traceFile = "";

	RAND rand;
	Clock clock;
	NRU nru;
	OPT opt;


	// *********************************************************************************//
	// 										CONSTRUCTORS                                //
	// *********************************************************************************//

	public RAM(int numberOfFrames, int method, int timeout, String file)
	{
		EVICT_METHOD = method;
		this.numberOfFrames = numberOfFrames;
		frames = new HashMap<Integer, Page>(this.numberOfFrames);
		this.timeout = timeout;
		this.traceFile = file;

		if(method == OPTIMAL && opt == null) try
		{
			opt = new OPT(this, traceFile);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
   	}

	// *********************************************************************************//
	// 										PUBLIC METHODS                              //
	// *********************************************************************************//

	public void read(int frameNum)
	{
		if(!containsPage(frameNum))
		{
			PAGE_FAULTS++;
			put(frameNum);
		}
		else
		{
			System.out.println("Hit " + frameNum);
		}

		frames.get(frameNum).setReferenced();

		update(frameNum);
		TOTAL_MEMORY_ACCESSES++;
	}


	public void write(int frameNum)
	{
        if(!containsPage(frameNum))
		{
			PAGE_FAULTS++;
			put(frameNum); // TODO: Should page start a dirty?
			frames.get(frameNum).setDirty();
		}
		else
		{
			System.out.println("Hit " + frameNum);
			frames.get(frameNum).setDirty();
		}

		frames.get(frameNum).setReferenced();

		update(frameNum);
		TOTAL_MEMORY_ACCESSES++;
	}


	public void update(int fn)
	{
		if(opt != null) opt.letOptKnowAboutPageReference(fn);
		if(nru != null) nru.letNruKnowAboutPageReference();
	}


	public void printStats()
	{
		System.out.println("Number of frames:\t" + this.numberOfFrames);
		System.out.println("Total memory accesses:\t" + TOTAL_MEMORY_ACCESSES);
		System.out.println("Total page faults:\t" + PAGE_FAULTS);
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


	public Page getPage(Integer integer)
	{
		return frames.get(integer);
	}


	public void dereferenceEverything()
	{
		for(Object i : pagesInRAM()) frames.get(i).setUnreferenced();
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
			System.out.println("Page Fault --- No Evict " + pageToAdd);
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
		try
		{
			switch(method)
			{
				case OPTIMAL:
					if(opt == null) opt = new OPT(this, traceFile);
					return opt.getPageToEvict();
				case CLOCK:
					if(clock == null) clock = new Clock(this);
					return clock.getPageToEvict();
				case NRU:
					if(nru == null) nru = new NRU(this, this.timeout);
					return nru.getPageToEvict();
				case RANDOM:
					if(rand == null) rand = new RAND(this);
					return rand.getPageToEvict();
			}
		}
		catch(Exception e)
		{
			System.out.println("We encountered an error.");
			e.printStackTrace();
		}

		return -1;
	}


	private void evictPage(int x)
	{
		if(frames.get(x) == null) System.out.println("Whoa..." + x);

		boolean thisIsACleanPage = frames.get(x).isClean();

		if(thisIsACleanPage)
		{
			System.out.println("Page Fault --- Evict CLEAN " + x);
			EVICT_CLEAN++;
		}
		else
		{
			System.out.println("Page Fault --- Evict DIRTY " + x);
			EVICT_DIRTY++;
		}

		frames.remove(x);
	}
}
