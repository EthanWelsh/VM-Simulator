import java.util.Random;
import java.util.Set;

/**
 * Created by welshej on 10/26/14.
 */
public class RAND
{




	private int random()
	{ // pick a page at random to evict from memory

		Set keyset = frames.keySet();
		int randomNumber = (new Random()).nextInt(frames.size());

		int pageToEvict = ((Integer) keyset.toArray()[randomNumber]).intValue();
		//System.out.println("I'm evicting page " + pageToEvict);
		return pageToEvict;

	}







}
