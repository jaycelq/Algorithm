#include <iostream>
using namespace std;

long long int reverse_num = 0;

void merge(long long int *array, int p, int q, int r)
{
	long long int *left, *right;
	left = new long long int [q - p + 1];
	right = new long long int [r -q];
	int i,j,k;

	for (i = 0; i < (q - p + 1); i++) left[i] = array[p + i];
	for(i = 0; i < (r -q); i++) right[i] = array[q+1+i];

	k = i = j = 0;
	while(i < (q - p + 1) && j < (r -q)){
		if(left[i] > 2 * right[j]) {reverse_num += (q - p + 1 - i); j++;}
		else i++;
	}

	i = j = 0;
	k = p;
	while(i < (q - p + 1) && j < (r -q)){
		if(left[i] < right[j]) {array[k] = left[i]; k++; i++;}
		else {array[k] = right[j]; k++; j++;}
	}

	while (i < (q - p + 1))
	{
		array[k] = left [i];
		i++;
		k++;
	}

	while(j < (r - q)){
		array[k] = right[j];
		j++;
		k++;
	}
	delete [] left;
	delete [] right;
}

void merge_count(long long int* array, int p, int r)
{
	int q;
	if(p < r){
		q = (p + r) / 2;
		merge_count(array , p, q);
		merge_count(array, q+1, r);
		merge(array, p, q, r);
	}
}

int main()
{
	long long int *array;
	int num;

	cin>>num;

	array = new long long int[num];

	for(int i = 0; i < num; i++) cin>>array[i];

	merge_count(array, 0, num - 1);

	cout << reverse_num;

	return 0;

}