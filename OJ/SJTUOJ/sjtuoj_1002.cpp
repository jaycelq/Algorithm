//SJTU OJ 1002
//solution is correct, but not elegant and general
//initial analysis http://www.cnblogs.com/li-qiang/p/3338708.html
//a more elegant and general solution prefix sum
#include <iostream>
using namespace std;

unsigned int prefix_sum[1000][1000] = {0};

int main()
{
	unsigned int L = 0, W = 0;
	cin >> L >> W;

	for(int i = 0; i < L; i++)
	{
		for(int j = 0; j < W; j++)
		{
			unsigned int value;
			cin >> value;
			if(i > 0 && j > 0) prefix_sum[i][j] = prefix_sum[i][j-1] + prefix_sum[i-1][j] - prefix_sum[i-1][j-1] + value;
			else if(i == 0 && j > 0) prefix_sum[0][j] = prefix_sum[0][j-1] + value;
			else if(i > 0 && j == 0) prefix_sum[i][0] = prefix_sum[i-1][0] + value;
			else prefix_sum[0][0] = value;
		}
	}

	unsigned int a = 0, b = 0, max = 0;
	cin >> a >> b;
	for(int i = a-1; i < L; i++)
	{
		for(int j = b-1; j < W; j++)
		{
			unsigned int sum = 0;
			if(i >= a && j >= b) sum = prefix_sum[i][j] - prefix_sum[i-a][j] - prefix_sum[i][j-b] + prefix_sum[i-a][j-b];
			else if(i == a-1 && j >= b) sum = prefix_sum[a-1][j] - prefix_sum[a-1][j-b];
			else if(i >= a && j == b-1) sum = prefix_sum[i][b-1] - prefix_sum[i-a][b-1];
			else sum = prefix_sum[a-1][b-1];
			if(sum > max) max = sum;
		}
	}
	cout << max << endl;
}
