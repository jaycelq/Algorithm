#include <iostream>
#include <string.h>
using namespace std;

int main()
{
	int sudoku_num = 0, i = 0, j = 0, k = 0, grid_pos = 0;
	int sudoku_var[9][9];
	int line[9]={0}, row[9]={0}, grid[9]={0};
	bool isSudoku = 1;

	cin >> sudoku_num;

	for(i = 0; i < sudoku_num; i++)
	{
		for (j = 0; j < 9; j++)
		{
			for(k = 0; k < 9; k++)
			{
				cin>>sudoku_var[j][k];

				//judge the input value
				if(sudoku_var[j][k]<1 || sudoku_var[j][k] > 9)
				{
					isSudoku = false;
				}

				//judge every line
				if (isSudoku && (line[j] & (1 << sudoku_var[j][k])))
				{
					isSudoku = false;
				}

				line[j] |= 1 << sudoku_var[j][k];

				//judge every row
				if (isSudoku && (row[k] & (1 << sudoku_var[j][k])))
				{
					isSudoku = false;
				}

				row[k] |= 1 << sudoku_var[j][k];
				grid_pos = (j/3)*3 + k/3;
				//judge every grid
				if (isSudoku && (grid[grid_pos] & (1 << sudoku_var[j][k])))
				{
					isSudoku = false;
				}

				grid[grid_pos] |= 1 << sudoku_var[j][k];
			}
		}
		memset(line,0,sizeof(line));
		memset(row,0,sizeof(row));
		memset(grid,0,sizeof(grid));
		cout<<(isSudoku ? "Right\n" :"Wrong\n");
		isSudoku = true;
	}

	return 0;
}