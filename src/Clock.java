import java.util.Arrays;
import java.util.Collections;
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

		Object[] p = ramset.toArray();
		Integer [] pages = new Integer[p.length];

		for(int i = 0; i < p.length; i++) pages[i] = (Integer) p[i];

		Arrays.sort(pages);

		for (;;)
		{
			Integer currentPageNumber = pages[index];
			Page currentPage = ram.getPage(pages[index]);

			if (currentPage.isReferenced())
			{
				index++;
                if (index >= pages.length) index = 0;
                ram.getPage(currentPageNumber).setUnreferenced();
			}
			else
			{
				index++;
                if (index >= pages.length) index = 0;
                return ram.getPage(currentPageNumber).number();
			}
		}
	}
}