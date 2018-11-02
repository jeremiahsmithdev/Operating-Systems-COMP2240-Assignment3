/**
 * * 
 *  * c3238179A3.java – Assignment3
 *   * @author: Jeremiah Smith
 *    * @student Number: c3238179
 *     * @version: 30/10/2018
 *      * Description:
 *       */
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

// TODO max 50 pages

public class Process implements Comparable<Process>
{
	private LinkedList<Integer> pages;
	private ArrayList<Integer> faultTimes;
	private int id;
	private String state;
	private String name;
	private int waiting;		// page waiting on if blocked
	private int finishTime;
	private Memory memory;
	private int frames;
	private List<PageRequest> requests;
	private int addTime;

	public Process()
	{

	}

	public Process(LinkedList<Integer> pages, int id, int frames, String name)
	{
		this.pages = pages;
		this.id = id;
		this.state = "ready";
		this.faultTimes = new ArrayList<Integer>();
		this.frames = frames;
		this.memory = new Memory(frames);
		this.requests = new LinkedList<PageRequest>();
		this.name = name;
		addTime = 0;
	}

	public void pageExecute(int time)
	{
		int page = pages.poll();
		memory.execute(page, time);
	}

	public int getID()
	{
		return id;
	}

	public List<Integer> getPages()
	{
		return pages;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public int check()
	{
		return pages.get(0);
	}

	public int getWaiting()
	{
		return waiting;
	}

	public void setWaiting(int page)
	{
		waiting = page;
	}

	public void addFault(int time)
	{
		faultTimes.add(time);
	}

	public String getFaults()
	{
		String list = "{";
		for (int i = 0; i < faultTimes.size(); i++)
		{
			if (i == 0)		// first
				list += faultTimes.get(i);

			else 
				list += ", "+faultTimes.get(i);
		}
		list += "}";
		return list;
	}

	public int getFinishTime()
	{
		return finishTime;
	}

	public void setFinishTime(int time)
	{
		this.finishTime = time;
		setState("finished");
	}

	public int faultNo()
	{
		return faultTimes.size();
	}

	public boolean hasPage(int page)
	{
		return memory.hasPage(page);
	}

	public String getName()
	{
		return name;
	}

	public Memory getMemory()
	{
		return memory;
	}

	public void addRequest(int page, int time)
	{
		// check if page request already exists
		for (PageRequest request : requests)
			if (request.getPage() == page)
				return;		// don't add existing request
		requests.add(new PageRequest(page, time));
		setState("blocked");
	}

	// // experimental , return if a page is ready
	// public boolean checkReady(int time, String strategy)
	// {
	// 	LinkedList<PageRequest> used = new LinkedList<PageRequest>();
	// 	for (PageRequest request : requests)
	// 	{
	// 		if (request.getReady() <= time)
	// 		{
	// 			setState("ready");
	// 			memory.add(request.getPage(), strategy);
	// 			used.add(request);
	// 			return true;
	// 		}
	// 	}
	// 	requests.removeAll(used);
	// 	return false;
	// }
	public void setAddTime(int addTime)
	{
		this.addTime = addTime;
	}

	public int getAddTime()
	{
		return addTime;
	}

	// if page ready, then set process to ready and put page in memory
	public boolean addReadyPages(int time, String strategy)
	{
		boolean processReady = false;
		LinkedList<PageRequest> used = new LinkedList<PageRequest>();
		for (PageRequest request : requests)
		{
			if (request.getReady() <= time)
			{
				processReady = true;
				setState("ready");
				memory.add(request.getPage(), strategy);
				used.add(request);
			}
		}
		requests.removeAll(used);
		return processReady;
	}

	public int compareTo(Process p)
	{
		if (addTime < p.getAddTime())
			return -1;
		else if (addTime > p.getAddTime())
			return 1;
		else if (addTime == p.getAddTime())
		{
			if (id < p.getID())
				return -1;
			else if (id > p.getID())
				return 1;
		}

		return 0;
	}
}
