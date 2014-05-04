//id[p] store the parent of item p
//if only one item the id[p]=p
//if the root of two item is the same then they are connected

public class QuickUnionUF {
	private int [] id;
	
	//average get the root O(logN)
	private int root(int i)
	{
		while(i != id[i]) i = id[i];
		return i;
	}
	
	// initialize all the item's parent is itself
	public QuickUnionUF(int N)
	{
		for(int i = 0; i < N; i++)
			id[i] = i;
	}
	
	// attach the left tree to the right tree
	public void union(int p, int q)
	{
		int root_p = root(p);
		int root_q = root(q);
		
		id[root_p] = root_q;
	}
	
	public boolean connected(int p, int q)
	{
		return root(q) == root(p);
	}
}
