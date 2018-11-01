/**
 * * 
 *  * c3238179A3.java – Assignment3
 *   * @author: Jeremiah Smith
 *    * @student Number: c3238179
 *     * @version: 30/10/2018
 *      * Description: Holds information about a request for a page made by a process
 *       */
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
