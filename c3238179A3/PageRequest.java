/**
 * * 
 *  * c3238179A3.java – COMP2240 Assignment3
 *   * @author: Jeremiah Smith
 *    * @student Number: c3238179
 *     * @version: 30/10/2018
 *      * Description: Holds information about a request for a page made by a process
 *       */
public class PageRequest
{
	private int page;	// the page that the request is for
	private int ready;	// the time that the request page shall be swapped in, and the request complete

	// constructor
	public PageRequest(int page, int ready)
	{
		this.page = page;
		this.ready = ready;
	}

	// returns page that request is for
	public int getPage()
	{
		return page;
	}

	// returns time that page request will be complete
	public int getReady()
	{
		return ready;
	}
}
