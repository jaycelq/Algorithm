#include <iostream>
using namespace std;
//http://www.cnblogs.com/txd0u/p/3353355.html
//the max sub sequence ended with element i is result[i] = sum[1:i] - min(sum[1:i-2])
//result = min(result[1:n])
#define max(x,y) x>y?x:y
#define min(x,y) x<y?x:y

int main()
{
	int size;
	int sum = 0;
	int min_sum = 0;
	int result = 0;
	cin>>size;

	for(int i = 0; i < size; i++)
	{
		int num = 0;
		cin >> num;
		sum = sum + num;
		result = max(result, sum - min_sum);
		min_sum = min(min_sum, sum - num);
	}

	if(result > 0) cout<<result;
	else cout << "Game Over";

	return 0;
}