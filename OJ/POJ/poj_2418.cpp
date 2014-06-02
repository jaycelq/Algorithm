#include <iostream>
#include <map>
#include <string>
#include <iomanip>

using namespace std;

int main()
{
	map<string, int> spiecies_map;
	string tree;
	int tree_num = 0;
	
	while(getline(cin, tree)) {
		spiecies_map[tree]++;
		tree_num++;
	}
	
	map<string, int>::iterator  iter;
	for(iter = spiecies_map.begin(); iter != spiecies_map.end(); iter++)
	{
       	cout<<iter->first<<" ";
	   	cout << fixed << showpoint << setprecision(4) << (((double) iter->second)/tree_num * 100)<<endl;
	}
	
	return 0;
}
