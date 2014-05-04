import java.util.Random;

public class Percolate {
	private int open_num;
	private int [][] array;
	private WeightedQuickUnionCompressionUF uf;
	
	private int id(int row, int colum)
	{
		return row * array[row].length + colum + 1;
	}
	
	private boolean open(int row, int colum)
	{
		if(array[row][colum] == 1) return false;
		
		array[row][colum] = 1;
		
		/*The condition needed to connect the virtual node*/
		if(row == 0) uf.union(0, id(row, colum));
		if(row == array.length - 1) uf.union(id(row, colum), array.length*array.length+1);

		if(row > 0)
		{
			if(array[row-1][colum] == 1) uf.union(id(row, colum), id(row-1, colum));
		}
		if(row < array.length -1)
		{
			if(array[row+1][colum] == 1) uf.union(id(row, colum), id(row+1, colum));
		}
		if(colum > 0)
		{
			if(array[row][colum-1] == 1) uf.union(id(row, colum), id(row, colum-1));
		}
		if(colum < array[row].length-1)
		{
			if(array[row][colum+1] == 1) uf.union(id(row, colum), id(row, colum+1));
		}

		open_num++;
		return true;
	}
	public Percolate(int N)
	{
		array = new int [N][N];
		uf = new WeightedQuickUnionCompressionUF(N*N + 2);
		
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				array[i][j] = 0;
		
		open_num = 0;
	}
	
	public void simulate()
	{
		int random;
		int row, colum;
		int size = array.length;
		Random rnd = new Random();
			
		while(true)		{
			random = rnd.nextInt(size*size);
			row = random / array.length;
			colum = random % array.length;
			if(open(row, colum) == true)
				System.out.println("percolate add row " + row + " colum " + colum);
			if(uf.connected(0, size*size+1) == true){
				System.out.println("percolate finished " + ((double) open_num)/(size*size));
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		Percolate test = new Percolate(500);
		test.simulate();
	}
}
