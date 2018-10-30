public class Memory
{
	private int F;		// frames
	// private int memory[];
	private Frame memory[];

	public Memory(int F)
	{
		this.F = F;
		// this.memory = new int[F];
		this.memory = new Frame[F];
		// for (Frame p : memory)
			for (int i = 0; i < memory.length; i++)
		{
			memory[i] = new Frame();
		}
	}

	public boolean add(int page)
	{
		for (Frame f : memory)
		{
			System.out.println(f.getPage());
		}
		boolean foundSpace = false;
		for (int i = 0; i < memory.length; i++)
		{
			if (memory[i].getPage() == 0)	// found empty index
			{
				memory[i].setPage(page);	// add page here
				foundSpace = true;		// return success
				break;
			}
		}
		if (foundSpace == false)	// find last recently used
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
		return false;				// return failure, memory full
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
				f.setLastUsed(time);
		}
	}
}
