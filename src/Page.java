/**
 * Created by welshej on 10/25/14.
 */
public class Page
{
	int frameNumber;
	private boolean isClean;
	private boolean isReferenced;

	private static final int CLEAN_UNREFERENCED = 4;
	private static final int DIRTY_UNREFERENCED = 3;
	private static final int CLEAN_REFERENCED = 2;
	private static final int DIRTY_REFERENCED = 1;



	public Page(int x)
	{
		frameNumber = x;
		isClean = true;
		isReferenced = false;
	}

	public int number()
	{
		return frameNumber;
	}

	public boolean isClean()
	{
		return isClean;
	}

	public void setDirty()
	{
		isClean = false;
	}

	public void setClean()
	{
		isClean = true;
	}

	public void setReferenced()
	{
		isReferenced = true;
	}

	public void setUnreferenced()
	{
		isReferenced = false;
	}

	public boolean isReferenced()
	{
		return isReferenced;
	}

	public boolean equals(Object o)
	{
		if (o instanceof Page)
		{
			Page toCompare = (Page) o;
			return this.frameNumber == toCompare.frameNumber;
		}
		return false;
	}

	public int hashCode()
	{
		return (new Integer(frameNumber)).hashCode();
	}

	public int pageType()
	{
	 	if(isClean && isReferenced) return CLEAN_REFERENCED;
		else if(isClean && !isReferenced) return CLEAN_UNREFERENCED;
		else if(!isClean && isReferenced) return DIRTY_REFERENCED;
		else if(!isClean && !isReferenced) return DIRTY_UNREFERENCED;
		return -1;
	}


}
