#include <iostream>
using namespace std;

template <class T>
class Array
{
private:
	T *A;
	size_t num;
	void mergesort(T *array, int lower_bound, int upper_bound);
	void quicksort(T *array, int lower_bound, int upper_bound);
	void merge(T *arry, int p, int q, int r);
	void partition(T *array, int p, int &q, int r);
	void swap(T &a, T &b);
public:
	Array(T *array, size_t size){
		A = new T[size];
		num = size;
		for(size_t i = 0; i < size; i++){
			A[i] = array[i];
		}
	}
	void insertion_sort();
	void merge_sort();
	void quick_sort(){
		quicksort(A, 0, num - 1);
	}
	void display();
	~Array(){
		delete [] A;
	}
};

template<class T>
void Array<T>::swap(T &a, T &b)
{
	T c = a;
	a = b;
	b = c;
}

template<class T>
void Array<T>::partition(T *array, int p, int &q, int r)
{
	T pivot = array[p];
	int i = p, j;

	for(j = p + 1; j <= r; j++){
		if(array[j] < pivot){
			i++;
			swap(array[j], array[i]);
		}
	}
	swap(array[i], array[p]);
	q = i;
}

template<class T>
void Array<T>::quicksort(T *array, int lower_bound, int upper_bound)
{
	int middle;
	if (lower_bound < upper_bound)
	{
		partition(array, lower_bound, middle, upper_bound);
		quicksort(array, lower_bound, middle - 1);
		quicksort(array, middle + 1, upper_bound);
	}
}

template <class T>
void Array<T>::insertion_sort()
{
	T key;
	size_t i,j;

	for (i = 0; i < num; i++)
	{
		key = A[i];
		for (j = i-1; j >= 0; j--)
		{
			if(A[j] > key) A[j+1] = A[j];
			else break;
		}
		A[j+1] = key;
	}
}

template <class T>
void Array<T>::merge_sort()
{
	mergesort(A, 0, num - 1);
}

template <class T>
void Array<T>::mergesort(T *A, int p, int r){
	int q;
	if (p < r)
	{
		q = (p + r)/2;
		mergesort(A, p, q);
		mergesort(A, q+1, r);
		merge(A, p, q, r);
	}
}

template <class T>
void Array<T>::merge(T *arry, int p, int q, int r)
{
	T *left = new T[q - p + 1];
	T *right = new T[r - q];
	int i,j,k;
	int n1 = q - p + 1, n2 = r - q;

	for(i = p; i < p + n1; i++) left[i-p] = arry[i];
	for (j = q + 1; j < q + n2 + 1; j++) right[j - q -1] = arry[j];
    
	i=0,j=0,k=p;
	
	while(i < n1 && j < n2){
		if(left[i] < right[j]) {
			arry[k] = left[i];
			i++;
		}
		else{
			arry[k] = right[j];
			j++;
		}
		k++;
	}

	while(i < n1){
		arry[k] = left[i];
		k++;
		i++;
	}
	while(j < n2){
		arry[k] = right[j];
		k++;
		j++;
	}

	delete [] left;
	delete [] right;
}

template <class T>
void Array<T>::display()
{
	for (size_t i = 0; i < num; i++) cout << A[i];
}

int main()
{
  int A[]={2,9,6,5,4,2,3,1,14,54,6};

  Array<int> a(A,11);
  //a.insertion_sort();
  //a.merge_sort();
  a.quick_sort();
  a.display();

  return 0;
}