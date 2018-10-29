public class Memory
{
	private int F;		// frames
	private int memory[];

	public Memory(int F)
	{
		this.F = F;
		this.memory = new int[F];
	}

	public boolean add(int page)
	{
		for (int i = 0; i < memory.length; i++)
		{
			if (memory[i] == 0)	// found empty index
			{
				memory[i] = page;	// add page here
				return true;		// return success
			}
		}
		return false;				// return failure, memory full
	}

	public int size()
	{
		int count = 0;
		for (int i = 0; i < memory.length; i++)
		{
			if (memory[i] != 0)
				count++;
		}
		return count;
	}

	public boolean hasPage(int page)
	{
		for (int i = 0; i < memory.length; i++)
		{
			if (memory[i] == page)
				return true;
		}
		return false;
	}
}
