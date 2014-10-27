import java.util.Random;
import java.util.Set;

public class RAND
{
	RAM ram;

	public RAND(RAM ram)
	{
	    this.ram = ram;
	}

	public int getPageToEvict()
	{ // pick a page at getPageToEvict to evict from memory

		Set keyset = ram.pagesInRAM();
		int randomNumber = (new Random()).nextInt(ram.size());

		int pageToEvict = ((Integer) keyset.toArray()[randomNumber]).intValue();
		//System.out.println("I'm evicting page " + pageToEvict);
		return pageToEvict;

	}
}
