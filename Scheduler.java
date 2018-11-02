/**
 * * 
 *  * c3238179A3.java – Assignment3
 *   * @author: Jeremiah Smith
 *    * @student Number: c3238179
 *     * @version: 30/10/2018
 *      * Description: Schedules processes with Round Robin policy
 *       */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

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

	String temp = null;

	private Scanner in;
	// BufferedReader in;

	// initialize CPU with frame time and time quantum for use with processes
	public Scheduler(int F, int Q, String strategy)
	{
		// in = new BufferedReader(new InputStreamReader(System.in));


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

		in = new Scanner(System.in);
	}
	public void debuggerM()
	{
		System.out.print("\nTIME: " + time);
		System.out.println(" (manual time update)");
		System.out.print("ReadyQueue: ");
		for (Process p : readyQueue)
		{
				System.out.print(p.getName() + " 	");
		}
		System.out.println(readyQueue.size());

		System.out.print("Blocked: ");
		for (Process p : blocked)
		{
				System.out.print(p.getName() + " 	");
		}
		System.out.println(blocked.size());
		// System.out.println(blocked.size());
	}

	public void debugger()
	{
		System.out.print("\nTIME: " + time);
		System.out.print("ReadyQueue: ");
		for (Process p : readyQueue)
		{
				System.out.print(p.getName() + " 	");
		}
		System.out.println(readyQueue.size());

		System.out.print("Blocked: ");
		for (Process p : blocked)
		{
				System.out.print(p.getName() + " 	");
		}
		System.out.println(blocked.size());
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
		// for (int o = 0; o < 10; o++)
		{
			// System.out.println("finished size = " + finished.size() + " and process size = " + processes.size());
			// check for pages finished loading
			checkForReady();
			// insertInOrder();//TODO can be done together
			int prev = time;	// for later reference


			// for (Process ready : readyQueue)
			int size = readyQueue.size();
			for (int i = 0; i < size; i++)
			{
				execute(readyQueue.poll());
			}

			// // loop over ready Processes
			// for (Process ready : processes)
			// {
			// 	// Process ready = readyQueue.poll();
			// 	if (ready.getState().equals("ready"))
			// 		execute(ready);
			// 	// add back to readyQueue if not finished
			// }
			if (time == prev)		// increment when no exection events occured since last loop
			{
				time++;//only increment if nothing was executed
				//DEBUGGER
				// debuggerM();
			}
		}

		System.out.println("PID	 Process Name	Turnaround Time # Faults  Fault Times");
		for (Process p : processes)
		{
			System.out.println((p.getID()+1) + "	 " + p.getName() + "	" + p.getFinishTime() + "		 " + p.faultNo() + "	  " + p.getFaults());
		}
	}

	public void execute(Process ready)
	{
		count = 0;
		while (/*ready.getState().equals("ready") && */ready.getPages().size() > 0 && count < Q)	// 'Q' may change here
		{
			// if (inMemory(ready.check()))	// page is in memory
			if (ready.hasPage(ready.check()))
			{
				ready.pageExecute(time);
				time++;
				// debugger();
				count++;
			}
			//
			else 		// page is not in memory
			{
				ready.addFault(time);

				ready.addRequest(ready.check(), time+6);
				ready.setWaiting(ready.check());
				blocked.add(ready);
				// break;
				return;
			}
			// //--------- MOVED
			// 	time++;
			// 	debugger();
			// 	count++;
			// checkForReady();
			// insertInOrder();
			// // ------------

			checkForReady();
			// insertInOrder();

		}
		if (ready.getPages().size() > 0) // TODO && NOT BLOCKED
		{
			readyQueue.add(ready);	// add back to queue if not finished
		}
		else
		{
			ready.setFinishTime(time);
			finished.add(ready);
		}


	}

	// public void insertInOrder()
	// {
	// 	Process insertNext = new Process();
	// 	while (toInsert.size() > 0)
	// 	{
	// 		int min = Integer.MAX_VALUE;
	// 		for (Process p : toInsert)
	// 		{
	// 			if (p.getID() < min)
	// 			{
	// 				insertNext = p;
	// 				min = p.getID();
	// 			}
	// 		}
	// 		readyQueue.add(insertNext);
	// 		toInsert.remove(insertNext);
	// 	}
	// }

	public void checkForReady()
	{
		// for (Process p : blocked)
		// {
		// 	if (p.checkReady(time, strategy))
		// 		readyQueue.add(p);
		// }
		LinkedList<Process> found = new LinkedList<Process>();
		for (Process p : blocked)
		{
			if (p.addReadyPages(time, strategy))
			{
				found.add(p);
				// blocked.remove(p);
				// readyQueue.add(p);
			}
		}
		blocked.removeAll(found);
		readyQueue.addAll(found);
	}
}
