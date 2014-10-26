/**
 * Created by welshej on 10/25/14.
 */
public class Page
{
	int frameNumber;
	private boolean isClean;
	private boolean isReferenced;

	public Page(int x)
	{
		frameNumber = x;
		isClean = true;
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


}
