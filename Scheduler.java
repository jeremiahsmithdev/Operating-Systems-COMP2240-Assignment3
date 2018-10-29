import java.util.Queue;
import java.util.LinkedList;
import java.util.List;

public class Scheduler
{
	private int F;
	private int Q;
	private LinkedList<Process> readyQueue;
	private LinkedList<Process> blocked;
	private LinkedList<Process> finished;
	private LinkedList<Process> processes;
	private List<PageRequest> requests;
	// private Process active;
	private int time;
	private int count;
	// private List<Integer> memory;
	// private int memory[];
	private Memory memory;

	// initialize CPU with frame time and time quantum for use with processes
	public Scheduler(int F, int Q)
	{
		this.F = F;
		this.Q = Q;
		this.memory = new Memory(F);
		// this.memory = new int[F];
		// this.memory = new LinkedList<Integer>();	// F size
		this.requests = new LinkedList<PageRequest>();
		this.time = 0;
		this.processes = new LinkedList<Process>();
		this.readyQueue = new LinkedList<Process>();
		this.blocked = new LinkedList<Process>();
		this.finished = new LinkedList<Process>();
	}

	public void run(Process process[])
	{
		// TODO enter in order of ID
		// add processes to ready quque
		for (int i = 0; i < process.length; i++)
		{
			System.out.println("added (3)");
			readyQueue.add(process[i]);
			processes.add(process[i]);
		}

		// run while proccesses are still in the ready queue
		// for (int a = 0; a < 20; a++)
		while (finished.size() < processes.size())
		{
			// check for pages finished loading
			checkForReady();

			// loop over ready Processes
			int end = readyQueue.size();
			for (int i = 0; i < end; i++)
			{
				Process active = readyQueue.poll();
				// System.out.println("	Process " + (active.getID()+1) + " removed from queue");
				execute(active);
			System.out.println("		TIME: " + time + " 		Q size: " + readyQueue.size() + " Memory used: " + memory.size());
				// add back to readyQueue if not finished
			}
			time++;
		}

		System.out.println("PID	 Turnaround Time # Faults  Fault Times");
		for (Process p : processes)
		{
			System.out.println((p.getID()+1) + "	 " + p.getFinishTime() + "		 " + p.faultNo() + "	  " + p.getFaults());
		}
	}

	public void execute(Process active)
	{
		System.out.println("\n===== PROCESS: "+(active.getID()+1)+" =====");
		count = 0;
		while (/*active.getState().equals("ready") && */active.getPages().size() > 0 && count < Q)	// 'Q' may change here
		{
			// if (inMemory(active.check()))	// page is in memory
			if (memory.hasPage(active.check()))
			{
				System.out.println();
				System.out.println("	CONTINUING FOR process: --> "+(active.getID()+1));
				active.executeInstruction();
				time++;
				count++;
			}
			//
			else 		// page is not in memory
			{
				System.out.println("	PAGE FAULT 	(" + active.check()+")");
				active.addFault(time);

				addRequest(active.check(), time+6);
				// ioRequest(active.check());
				active.setState("blocked");
				active.setWaiting(active.check());
				System.out.println("	"+(active.getID()+1) + "	set to be waiting for " + active.check());
				// readyQueue.remove(active);
				blocked.add(active);
				// break;
				return;
			}

			// if (active.getPages().size() > 0) // TODO && NOT BLOCKED
			// {
			// 	// System.out.println(readyQueue.size());
			// 	// System.out.println("					added " + (active.getID()+1) + " back to queue");
			// 	// readyQueue.add(active);
			// 	// System.out.println(readyQueue.size());
			// }
			// else
			// {
			// 	readyQueue.remove(active);		// finished process removed from ready queue
			// 	System.out.println("Process " + (active.getID()+1) +" FINISHED");
			// 	finished.add(active);
			// }
		}
		System.out.println("P = " + (active.getID()+1));
			if (active.getPages().size() > 0) // TODO && NOT BLOCKED
			{
				System.out.println("					added " + (active.getID()+1) + " back to queue");
				readyQueue.add(active);
			}
			else
			{
				System.out.println("								Process " + (active.getID()+1) +" FINISHED");
				active.setFinishTime(time);
				finished.add(active);
			}

	}

	public void addRequest(int page, int time)
	{
		// check if page request already exists
		for (PageRequest request : requests)
			if (request.getPage() == page)
				return;		// don't add existing request
		System.out.println("  request for " + page);
		requests.add(new PageRequest(page, time));
	}

	public void checkForReady()
	{
		System.out.println("\nready: "+readyQueue.size());
		System.out.println("blocked: "+blocked.size());
		System.out.println("finished: "+finished.size()+"\n");
		for (int j = 0; j < requests.size(); j++)
		{
			if (requests.get(j).getReady() <= time)
			{
				System.out.println("loaded " + requests.get(j).getPage());
				notify(requests.get(j).getPage());
				memory.add(requests.remove(j).getPage());
			}
		}
	}

	public void notify(int page)
	{
		LinkedList<Process> added = new LinkedList<Process>();
		for (Process process: blocked)		// add processes back to readyQueue
		// for (int i = 0; i < blocked.size(); i++)
		{
			// if (blocked.get(i).getWaiting() == page)
			if (process.getWaiting() == page)
			{
				// blocked.remove(process);		// remove process from blocked queue
				readyQueue.add(process);
				added.add(process);			// add process to list that needs to be removed
			System.out.println("added (2)");
				System.out.println("Process " + (process.getID()+1) + " notified");
			}
		}
		blocked.removeAll(added);
	}

	public void ioRequest(int page)
	{
		System.out.println("loading " + page + " into memory...");
	}

	// returns true if page in memory else false
	// public boolean inMemory(int page)
	// {
	// 	for (int i = 0; i < memory.size(); i++)
	// 	{
	// 		if (memory.get(i) == page)
	// 			return true;
	// 	}
	// 	return false;
	// }
}
