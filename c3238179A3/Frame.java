/**
 * * 
 *  * Frame.java – COMP1140 Assignment3
 *   * @author: Jeremiah Smith
 *    * @student Number: c3238179
 *     * @version: 02/11/2018
 *      * Description: A frame of memory held by a process, which can hold one page
 *       */
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
