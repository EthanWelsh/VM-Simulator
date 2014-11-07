import java.util.concurrent.*;

public class NRU
{
	private static final int CLEAN_UNREFERENCED = 0;
	private static final int DIRTY_UNREFERENCED = 1;
	private static final int CLEAN_REFERENCED = 2;
	private static final int DIRTY_REFERENCED = 3;

	int timeout;
	RAM ram;


	public NRU(RAM ram, int timeout)
	{
		this.timeout = timeout;
		this.ram = ram;

		setDereference();
	}


	private void setDereference()
	{
		Runnable helloRunnable = new Runnable()
		{
			public void run()
			{
				ram.dereferenceEverything();
				//System.out.println("I've dereferenced everything.");
			}
		};

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(helloRunnable, 0, timeout, TimeUnit.MILLISECONDS); // TODO fix this
	}


	public int getPageToEvict()
	{

		int bestPage = -1;
		int bestPageRank = -1;

		for(Object page : ram.pagesInRAM())
		{

			Page p = ram.getPage((Integer)page);

			if(p.pageType() == CLEAN_UNREFERENCED)
			{
				return p.number();
			}
			if(p.pageType() == DIRTY_UNREFERENCED)
			{
				bestPageRank = DIRTY_UNREFERENCED;
				bestPage = p.number();

			}
			else if(p.pageType() == CLEAN_REFERENCED)
			{
				if(bestPageRank == CLEAN_UNREFERENCED || bestPageRank == DIRTY_UNREFERENCED)
				{
					//System.out.println("Passing this over in favor of fairer seas...");

				}
				else
				{
					bestPageRank = CLEAN_REFERENCED;
					bestPage = p.number();
				}
			}
			else if(p.pageType() == DIRTY_REFERENCED)
			{
				if(bestPageRank == CLEAN_UNREFERENCED || bestPageRank == DIRTY_UNREFERENCED || bestPageRank != CLEAN_REFERENCED)
				{
					//System.out.println("Passing this over in favor of fairer seas...");
				}
				else
				{
					bestPageRank = DIRTY_REFERENCED;
					bestPage = p.number();
				}
           	}
		}
		return bestPage;
	}
}