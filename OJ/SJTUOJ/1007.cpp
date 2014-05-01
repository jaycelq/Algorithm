#include <iostream>
#include <string>

#define min(a,b)    (((a) < (b)) ? (a) : (b))
#define max(a,b)    (((a) > (b)) ? (a) : (b))


using namespace std;

int main()
{
	unsigned int i;
	int carry = 0, sum = 0;
	string add0, add1;
	string result;

	cin >> add0 >> add1;

	result.resize(max(add0.size(), add1.size()) + 1);

	for(i = 0; i < min(add0.size(), add1.size()); i++) {
		if(add0[add0.size() - i - 1] == '.') {
			result[result.size() - i - 1] = '.';
			continue;
		}
		else {
			sum = add0[add0.size() - i - 1] - '0' + add1[add1.size() - i - 1] - '0' + carry;
			if(sum >= 10) {
				sum -= 10;
				carry = 1;
			}
			else carry = 0;
			result[result.size() - i - 1] = sum + '0';
		}
	}

	for(; i < max(add0.size(), add1.size()); i++) {
		if(add0.size() - i -1 >= 0) {
			sum = add0[add0.size() - i - 1] - '0' + carry;
			if(sum >= 10) {
				sum -= 10;
				carry = 1;
			}
			else carry = 0;
			result[result.size() - i - 1] = sum + '0';
		}
		else {
			sum = add1[add1.size() - i - 1] - '0' + carry;
			if(sum >= 10) {
				sum -= 10;
				carry = 1;
			}
			else carry = 0;
			result[result.size() - i - 1] = sum + '0';
		}
	}
	if(carry == 1) result[result.size() - i - 1] = 1 + '0';
	else (result.erase(0,1));

	cout <<result;
	return 0;
}