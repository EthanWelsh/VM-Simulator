public class NRU
{
	private static final int CLEAN_UNREFERENCED = 4;
	private static final int DIRTY_UNREFERENCED = 3;
	private static final int CLEAN_REFERENCED = 2;
	private static final int DIRTY_REFERENCED = 1;

	int timeout;
	RAM ram;

	int referencesSinceLastTimeout = 0;


	public NRU(RAM ram, int timeout)
	{
		this.timeout = timeout;
		this.ram = ram;
	}

	public void letNruKnowAboutPageReference()
	{
		if(referencesSinceLastTimeout > timeout)
		{
			ram.dereferenceEverything();
			referencesSinceLastTimeout = 0;
		}
		referencesSinceLastTimeout++;
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
			if(p.pageType() == DIRTY_UNREFERENCED && bestPageRank < DIRTY_UNREFERENCED)
			{
				bestPageRank = DIRTY_UNREFERENCED;
				bestPage = p.number();
			}
			else if(p.pageType() == CLEAN_REFERENCED && bestPageRank < CLEAN_REFERENCED)
			{
				bestPageRank = CLEAN_REFERENCED;
				bestPage = p.number();
			}
			else if(p.pageType() == DIRTY_REFERENCED && bestPageRank < DIRTY_REFERENCED)
			{
				bestPageRank = DIRTY_REFERENCED;
				bestPage = p.number();
	      	}
		}

		return bestPage;
	}
}