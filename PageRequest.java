public class PageRequest
{
	private int page;
	private int ready;
	public PageRequest(int page, int ready)
	{
		this.page = page;
		this.ready = ready;
	}

	public int getPage()
	{
		return page;
	}

	public int getReady()
	{
		return ready;
	}
}
