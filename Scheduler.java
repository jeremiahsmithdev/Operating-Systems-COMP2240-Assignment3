/**
 * * 
 *  * c3238179A3.java – Assignment3
 *   * @author: Jeremiah Smith
 *    * @student Number: c3238179
 *     * @version: 30/10/2018
 *      * Description: Schedules processes with Round Robin policy
 *       */
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;

public class Scheduler
{
	private int F;
	private int Q;
	private LinkedList<Process> readyQueue;
	private LinkedList<Process> toInsert;
	private LinkedList<Process> blocked;
	private LinkedList<Process> finished;
	private LinkedList<Process> processes;
	private List<PageRequest> requests;
	private String strategy;
	private int time;
	private int count;

	// initialize CPU with frame time and time quantum for use with processes
	public Scheduler(int F, int Q, String strategy)
	{
		this.F = F;
		this.Q = Q;
		this.strategy = strategy;
		this.requests = new LinkedList<PageRequest>();
		this.time = 0;
		this.processes = new LinkedList<Process>();
		this.readyQueue = new LinkedList<Process>();
		this.toInsert = new LinkedList<Process>();
		this.blocked = new LinkedList<Process>();
		this.finished = new LinkedList<Process>();
	}

	public void run(Process process[])
	{
		// TODO enter in order of ID
		// add processes to ready quque
		for (int i = 0; i < process.length; i++)
		{
			readyQueue.add(process[i]);
			processes.add(process[i]);
		}

		// run while proccesses are still in the ready queue
		while (finished.size() < processes.size())
		{
			// check for pages finished loading
			checkForReady();
			insertInOrder();
			int prev = time;	// for later reference

			// loop over ready Processes
			for (Process active : processes)
			{
				// Process active = readyQueue.poll();
				if (active.getState().equals("ready"))
					execute(active);
				// add back to readyQueue if not finished
			}
			if (time == prev)		// increment when nothing executed
				time++;//only increment if nothing was executed
		}

		System.out.println("PID	 Process Name	Turnaround Time # Faults  Fault Times");
		for (Process p : processes)
		{
			System.out.println((p.getID()+1) + "	 " + p.getName() + "	" + p.getFinishTime() + "		 " + p.faultNo() + "	  " + p.getFaults());
		}
	}

	public void execute(Process active)
	{
		count = 0;
		while (/*active.getState().equals("ready") && */active.getPages().size() > 0 && count < Q)	// 'Q' may change here
		{
			// if (inMemory(active.check()))	// page is in memory
			if (active.hasPage(active.check()))
			{
				active.pageExecute(time);
				time++;
				count++;
			}
			//
			else 		// page is not in memory
			{
				active.addFault(time);

				active.addRequest(active.check(), time+6);
				active.setWaiting(active.check());
				blocked.add(active);
				// break;
				return;
			}

			checkForReady();
			insertInOrder();

		}
		if (active.getPages().size() > 0) // TODO && NOT BLOCKED
		{
			readyQueue.add(active);
		}
		else
		{
			active.setFinishTime(time);
			finished.add(active);
		}

	}

	public void insertInOrder()
	{
		Process insertNext = new Process();
		while (toInsert.size() > 0)
		{
			int min = Integer.MAX_VALUE;
			for (Process p : toInsert)
			{
				if (p.getID() < min)
				{
					insertNext = p;
					min = p.getID();
				}
			}
			readyQueue.add(insertNext);
			toInsert.remove(insertNext);
		}
	}

	public void checkForReady()
	{
		for (Process p : processes)
		{
			p.addReadyPages(time, strategy);
		}
	}
}
