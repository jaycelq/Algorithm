#include <iostream>
using namespace std;

int main()
{
	unsigned int human, stool, all_num, height;
	
	unsigned int avai_num = 0;

	cin>>human>>stool>>all_num;

	for(unsigned i = 0; i < all_num; i++)
	{
		cin>>height;
		if(height <= human + stool ) avai_num++;
	}

	cout<<avai_num;

	return 0;
}
