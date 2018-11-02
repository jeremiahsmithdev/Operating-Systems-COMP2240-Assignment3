/*
   2. Brief 1 page (A4) review of how you tested your program and a comparison of the page replacement methods based on the results from your program and any interesting observations.
   */

/**
 * *
 *  * c3238179A3.java – Assignment3
 *   * @author: Jeremiah Smith
 *    * @student Number: c3238179
 *     * @version: 28/10/2018
 *      * Description: Parses input and files and instantiates classes for simulation
 *       */

// TODO
// print process name in output
// remove junkyard code
// documentation
// header comments
// further testing
// streamline methods / program flow of execution
// MAX 50 Pages per process !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// REPORT !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// argument != filename
//
// it's not even operating as a queue lol

import java.util.List;
import java.util.LinkedList;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class c3238179A3
{
	static private int F;	// number of frames
	static private int Q;	// quantum size
	// static String process[];	// process name... negligible
	static File files[];
	static BufferedReader br[];

	public static void main(String args[]) throws Exception
	{
		if (args.length > 1)
		{
			F = Integer.parseInt(args[0]);			// sets number of rames
			Q = Integer.parseInt(args[1]);			// sets quantum size
		}
		else 		// warns user of not enough arguments
		{
			System.out.println("Please run program with correct arguments.\nFor example:\n"
					+ "java c3238179A3 30 3 process1.txt process2.txt process3.txt");
			return;
		}


		Process[] process = addProcesses(files, br, F, Q, args);

		Scheduler LRU = new Scheduler(F, Q, "LRU");		// initialize Scheduler with prerequisite date
		System.out.println("LRU - Fixed");
		LRU.run(process);

		System.out.println("\n------------------------------------------------------------\n");

		System.out.println("Clock - Fixed");
		Scheduler CLOCK = new Scheduler(F, Q, "CLOCK");
		Process[] p = addProcesses(files, br, F, Q, args);
		CLOCK.run(p);


	}

	public static Process[] addProcesses(File files[], BufferedReader br[], int F, int Q, String[] args) throws Exception
	{
		files = new File[args.length - 2];
		br = new BufferedReader[args.length - 2];

		for (int i = 0; i < args.length - 2; i++)	// set file names from arguments
		{
			files[i] = new File(args[i+2]);
			br[i] = new BufferedReader(new FileReader(files[i]));
		}

		int f = F / files.length;	// process memory allocation (fixed)
		String line;
		Process process[] = new Process[files.length];
		for (int i = 0; i < files.length; i++)
		{
			LinkedList<Integer> data = new LinkedList<Integer>();
			for (line = br[i].readLine(); !line.equals("end"); line = br[i].readLine())
			{
				if (!line.equals("begin"))
					data.add(Integer.parseInt(line));
			}
			process[i] = new Process(data, i, f, args[i+2]);
		}
		return process;
	}
}
/*
 * MARK DISTRIBUTION
 *
 * INPUT/OUTPUT (/10)
 * 	Correctly reads input
 * 	Outputs results
 * CODE CLARITY (/10)
 * 	Code structure
 * 	Code comments
 * IMPLEMENTATION (/70)
 * 	1. Round robin scheduling (10)
 * 	2. LRU with fixed allocation/local (30)
 * 	3. Clock with fixed allocation/local (30)
 * 	Break down of 2 and 3
 * 		Page fautl times (15)
 * 		Turn-around time (10)
 * 		Number of page faults (5)
 * 1 PAGE SUMMARY (/10)
 */
