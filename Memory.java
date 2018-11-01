/**
 * * 
 *  * c3238179A3.java – Assignment3
 *   * @author: Jeremiah Smith
 *    * @student Number: c3238179
 *     * @version: 30/10/2018
 *      * Description: Memory allocated to a process, which includes a fixed amount of Frames
 *       */
public class Memory
{
	private int F;		// frames
	// private int memory[];
	private Frame memory[];
	private int pointer;

	public Memory(int F)
	{
		this.F = F;
		// this.memory = new int[F];
		this.memory = new Frame[F];
		this.pointer = 0;
		// for (Frame p : memory)
		for (int i = 0; i < memory.length; i++)
		{
			memory[i] = new Frame();
		}
	}

	public void add(int page, String strategy)
	{
		// looks for empty frame
		boolean foundSpace = false;
		for (int i = 0; i < memory.length; i++)
		{
			if (memory[i].getPage() == 0)	// found empty index
			{
				memory[i].setPage(page);	// add page here
				memory[i].setUseBit(1);
				foundSpace = true;		// return success
				return;
			}
		}

		// LRU replacement
		if (strategy.equals("LRU"))
		{
			Frame lru = new Frame();
			int LRU = 0;
			for (int i = 0; i < memory.length; i++)
			{
				if (memory[i].getLastUsed() <= lru.getLastUsed())
				{
					lru = memory[i];
					LRU = i;
				}
			}
			memory[LRU].setPage(page);
		}

		// CLOCK replacement
		else
		{
			while (true)
			{
				for (int i = pointer; i < memory.length; i++)
				{
					pointer = i;
					if (memory[i].getUseBit() == 0)	// set use bit to 0 for next iteration
					{
						memory[i].setUseBit(1);
						memory[i].setPage(page);
						return;
					}
					else
						memory[i].setUseBit(0);
				}
				pointer = 0;
			}
		}
	}

	public int size()
	{
		int count = 0;
		for (int i = 0; i < memory.length; i++)
		{
			if (memory[i].getPage() != 0)
				count++;
		}
		return count;
	}

	public boolean hasPage(int page)
	{
		for (int i = 0; i < memory.length; i++)
		{
			if (memory[i].getPage() == page)
				return true;
		}
		return false;
	}

	public void execute(int page, int time)
	{
		for (Frame f : memory)
		{
			if (f.getPage() == page)
			{
				f.setUseBit(1);		// useBit set when page is refererenced
				f.setLastUsed(time);
			}
		}
	}
}
