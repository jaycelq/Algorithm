#include <iostream>
using namespace std;

int main()
{
	long int size;
	long int *array = NULL, *sum = NULL, *min_sum = NULL;
	long int max = 0,max_last = 0, max_now = 0,max_out=0;
	long int start = 0, start_tmp = 0 ,end = 0;

	cin >> size;

	array = new long int [size];

	for(long int i = 0; i < size; i++){
		cin>>array[i];
	}
	max_last = array[0];
	max = array[0] + array [1];

	for(long int i = 2; i < size; i++){
		if(max_last < 0 && array[i-2] < 0){
			max_now = array[i-1]+array[i];
		}
		else max_now = max + array[i];
		max_last = max;
		max = max_now;
		if(max_now > max_out) max_out=max_now;
	}
	if(max_out>0) cout<<max_out;
	else cout<<"Game Over";

	delete [] array;
	return 0;
}