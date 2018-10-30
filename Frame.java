public class Frame
{
	private int page;
	private int lastUsed;
	private int useBit;

	public Frame()
	{
		page = 0;
		lastUsed = Integer.MAX_VALUE;
	}

	public void setLastUsed(int time)
	{
		lastUsed = time;
	}

	public int getLastUsed()
	{
		return lastUsed;
	}

	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public int getUseBit()
	{
		return useBit;
	}

	public void setUseBit(int bit)
	{
		useBit = bit;
	}
}
