// id[p] is the set that contains p

public class QuickFindUF
{
	private int[] id;
	
	// initialize the items with all different set
	public QuickFindUF(int N)
	{
		id = new int [N];
		for(int i = 0; i < N; i++)
			id[i] = i;
	}
	
	// quick-find whether they are connected O(1)
	public boolean connected(int p, int q)
	{
		return id[p] == id[q];
	}
	
	// union cost much O(N)
	public void union(int p, int q)
	{
		int pid = id[p], qid = id[q];
		
		// Scan through the array
		for(int i = 0; i < id.length; i++)
		{
			if(id[i] == pid) id[i] = qid;
		}
	}
	
}


