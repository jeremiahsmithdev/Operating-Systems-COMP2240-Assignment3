import java.util.List;
import java.util.LinkedList;

// TODO max 50 pages

public class Process
{
	private LinkedList<Integer> pages;
	private LinkedList<Integer> faultTimes;
	private int id;
	private String state;
	private int waiting;		// page waiting on if blocked
	private int finishTime;

	public Process(LinkedList<Integer> pages, int id)
	{
		this.pages = pages;
		this.id = id;
		this.state = "ready";
		this.faultTimes = new LinkedList<Integer>();
	}

	public void executeInstruction()
	{
		System.out.println("	executing page " + pages.poll());
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
	}

	public int faultNo()
	{
		return faultTimes.size();
	}


}
