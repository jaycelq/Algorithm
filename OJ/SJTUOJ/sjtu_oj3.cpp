#include <iostream>
using namespace std;

int main()
{
	unsigned int size;
	unsigned int occupied = 0;
	unsigned int scale;
	unsigned int **array, **flag;
	unsigned int num = 1;
	
	cin>>size;

	scale = size*size;

	array = new unsigned int * [size];
	flag = new unsigned int * [size];

	for(unsigned int i = 0; i < size; i++){
		array[i] = new unsigned int [size];
		flag[i] = new unsigned int [size];
	}

	for(unsigned int i = 0; i < size; i++){
		for(unsigned int j = 0; j < size; j++){
			cin>>array[i][j];
			if(array[i][j] == 1) {
				flag[i][j] = 1;
				occupied++;
			}
			else if(array[i][j] == 2){
				flag[i][j] = 0;
				occupied++;
			} 
			else flag[i][j]=0;
		}
	}

	while(occupied < scale){
		for(unsigned int i = 0; i < size; i++){
			for(unsigned int j = 0; j < size; j++){
				if(flag[i][j]!=num) continue;
				if(i>0 && array[i-1][j] == 0) {
					array[i-1][j]=1;
					flag[i-1][j]=num+1;
					occupied++;
				}
				if(i<size-1 && array[i+1][j] == 0) {
					array[i+1][j]=1;
					flag[i+1][j]=num+1;
					occupied++;
				}
				if(j<size-1 && array[i][j+1] == 0) {
					array[i][j+1]=1;
					flag[i][j+1]=num+1;
					occupied++;
				}
				if(j>0 && array[i][j-1] == 0) {
					array[i][j-1]=1;
					flag[i][j-1]=num+1;
					occupied++;
				}
				flag[i][j]=0;
			}
		}
		num++;
	}

	cout<<num-1;

	for(unsigned int i = 0; i < size; i++){
		delete [] array[i];
		delete [] flag[i];
	}

	delete [] array;
	delete [] flag;
	return 0;

}