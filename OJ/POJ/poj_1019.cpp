#include <iostream>
using namespace std;

long long int pow(long long int base, long long int exp)
{
	long long int result = 1;
	for(long long int i = 0; i < exp; i++) {
		result *= base;
	}
	return result;
}

int find(long long pos)
{
	long long sum = 0, last_sum = 0;
	int k = 1;
	int result = 0;
	
	while(true) {
		last_sum = sum;
		sum += 9 * pow(10, k-1) * k;
		if(sum > pos) {
			long long diff = pos - last_sum;
			long long rank = diff / k;
			long long mod = diff % k;
			if(k == 1) result = diff + 1;
			else result = ((pow(10, k-1) + rank)/pow(10, k-1-mod)) % 10;
			break; 
		}
		k++;
	}
	
	return result;
}

int main()
{
	int t;
    long long i, group_num, member_size, sum, last_sum, last_group_sum, group_sum, pos, res;
    cin >> t;
    for(int j = 0; j < t; j++) {
    	group_num = 1;
    	last_group_sum = 0;
    	member_size = 1;
    	sum = 0;
    	cin >> i;
    	i = i - 1;
    	while(true){
    		last_sum = sum;
    		group_sum = last_group_sum + member_size;
    		sum += group_sum;
    		if(sum > i) {
    			res = find(i - last_sum);
    			cout<<res<<endl;
    			break;
    		}
    		last_group_sum = group_sum;
    		group_num++;
    		if(group_num/pow(10, member_size) - (group_num-1)/pow(10, member_size) == 1) member_size++;
    	}
    }
    return 0;
}
