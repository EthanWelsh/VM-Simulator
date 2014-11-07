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
		Page[] pages = (Page[]) ramset.toArray();

		// Go through your array...
		//		If you find a referenced page, unreference and proceed.
		//		If you find an unreferenced page, return it.

		for(;;)
		{
			if(pages[index].isReferenced())
			{
				index++;

				if(index >= pages.length) index = 0;

				ram.getPage(index).setUnreferenced();
			}
			else
			{
			    index++;

				if(index >= pages.length) index = 0;

				return ram.getPage(index).number();
			}
		}
	}





}
