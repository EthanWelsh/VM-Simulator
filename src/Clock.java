import java.util.Set;

public class Clock
{
	RAM ram;
	int index;

	public Clock(RAM ram)
	{
		this.ram = ram;
	}

	public int getPageToEvict()
	{
		Set ramset = ram.pagesInRAM();
		Object[] pages = ramset.toArray();

		for(;;)
		{
			Integer currentPageNumber = (Integer) pages[index];
			Page currentPage = ram.getPage(currentPageNumber);

			if(currentPage.isReferenced())
			{
				index++;

				if(index >= pages.length) index = 0;

				ram.getPage(currentPageNumber).setUnreferenced();
			}
			else
			{
			    index++;

				if(index >= pages.length) index = 0;

				return ram.getPage(currentPageNumber).number();
			}
		}
	}
}