import java.util.List;
import java.util.LinkedList;

// TODO max 50 pages

public class Process// implements Comparable<Process>
{
	private LinkedList<Integer> pages;
	private LinkedList<Integer> faultTimes;
	private int id;
	private String state;
	private int waiting;		// page waiting on if blocked
	private int finishTime;
	private Memory memory;
	private int frames;
	private List<PageRequest> requests;

	public Process()
	{

	}

	public Process(LinkedList<Integer> pages, int id, int frames)
	{
		this.pages = pages;
		this.id = id;
		this.state = "ready";
		this.faultTimes = new LinkedList<Integer>();
		this.frames = frames;
		this.memory = new Memory(frames);
		this.requests = new LinkedList<PageRequest>();
	}

	public void executeInstruction()
	{
		System.out.println(" executes page " + pages.poll());
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

	public LinkedList<Integer> getFaults()
	{
		return faultTimes;
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
		return faultTimes.length;
	}

	public boolean hasPage(int page)
	{
		return memory.hasPage(page);
	}

	public void addPage(int page)
	{
		memory.add(page);
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
		// System.out.println("  request for " + page);
		// System.out.println("Page request added for page " + page + " due at time " + time);
		requests.add(new PageRequest(page, time));
		setState("blocked");
		System.out.println("			"+(id+1) + " BLOCKED!");
	}

	// if page ready, then set process to ready and put page in memory
	public void checkForReady(int time)
	{
		LinkedList<PageRequest> used = new LinkedList<PageRequest>();
		for (PageRequest request : requests)
		{
			if (request.getReady() <= time)
			{
				setState("ready");
				System.out.println("			"+(id+1) + " Ready!");
				memory.add(request.getPage());
				used.add(request);
			}
		}
		requests.removeAll(used);
	}

	// public int compareTo(Process p)
	// {
	// 	if (id < 
	// 	return 1;
	// }
}
