/*
2. Brief 1 page (A4) review of how you tested your program and a comparison of the page replacement methods based on the results from your program and any interesting observations.
*/

/**
 * *
 *  * c3238179A3.java – Assignment3
 *   * @author: Jeremiah Smith
 *    * @student Number: c3238179
 *     * @version: 28/10/2018
 *      * Description:
 *       */

// TODO
// print process name in output
// remove junkyard code
// documentation
// header comments
// further testing
// streamline methods / program flow of execution

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
		// if (args.length > 1)
		// {
		// 	F = Integer.parseInt(args[0]);			// sets number of rames
		// 	Q = Integer.parseInt(args[1]);			// sets quantum size
		// // 	process = new String[args.length - 2];		// instantiate array of file names
		// 	files = new File[args.length - 2];
		// 	br = new BufferedReader[args.length - 2];
		// }
		// else 		// warns user of not enough arguments
		// 	System.out.println("Please run program with correct arguments.\nFor example:\n"
		// 			+ "java c3238179A3 30 3 process1.txt process2.txt process3.txt");
		// for (int i = 0; i < args.length - 2; i++)	// set file names from arguments
		// {
		// 	// process[i] = args[i+2];
		// 	files[i] = new File(args[i+2]);
		// 	br[i] = new BufferedReader(new FileReader(files[i]));
		// }
		// parse data from Process text files into Process objects
		// ==================== TEST INPUT ===============
		F = 30;
		Q = 3;
		// process = new String[4];		// instantiate array of file names
		files = new File[3];
		br = new BufferedReader[3];
		// process[0] = "Process1.txt";process[1] = "Process2.txt";process[2] = "Process3.txt";process[3] = "Process4.txt";
		files[0] = new File("Process1.txt");files[1] = new File("Process2.txt");files[2] = new File("Process3.txt");
		br[0] = new BufferedReader(new FileReader(files[0]));br[1] = new BufferedReader(new FileReader(files[1]));
		br[2] = new BufferedReader(new FileReader(files[2]));
		// ===============================================


		int f = F / files.length;	// process memory allocation (fixed)

		// String line;
		// Process process[] = new Process[files.length];
		// for (int i = 0; i < files.length; i++)
		// {
		// 	LinkedList<Integer> data = new LinkedList<Integer>();
		// 	for (line = br[i].readLine(); !line.equals("end"); line = br[i].readLine())
		// 	{
		// 		if (!line.equals("begin"))
		// 			data.add(Integer.parseInt(line));
		// 	}
		// 	process[i] = new Process(data, i, f);
		// }
		Process[] process = addProcesses(files, br, F, Q);

		Scheduler LRU = new Scheduler(F, Q, "LRU");		// initialize Scheduler with prerequisite date
		System.out.println("LRU - Fixed");
		LRU.run(process);

		System.out.println("\n------------------------------------------------------------\n");

		System.out.println("Clock - Fixed");
		Scheduler CLOCK = new Scheduler(F, Q, "CLOCK");
		Process[] p = addProcesses(files, br, F, Q);
		CLOCK.run(p);


	}

	public static Process[] addProcesses(File files[], BufferedReader br[], int F, int Q) throws Exception
	{
		files = new File[3];
		br = new BufferedReader[3];
		// process[0] = "Process1.txt";process[1] = "Process2.txt";process[2] = "Process3.txt";process[3] = "Process4.txt";
		files[0] = new File("Process1.txt");files[1] = new File("Process2.txt");files[2] = new File("Process3.txt");
		br[0] = new BufferedReader(new FileReader(files[0]));br[1] = new BufferedReader(new FileReader(files[1]));
		br[2] = new BufferedReader(new FileReader(files[2]));

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
			process[i] = new Process(data, i, f);
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
