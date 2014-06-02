#include <iostream>
using namespace std;

int f(int M, int N)
{
    if(M == 1 || N == 1) return 1;
    else if(N > M) return f(M, N-1);
    else return f(M-N, N) + f(M, N-1);
}

int main()
{
    int M, N, num;
    cin >> num;
    for(int i = 0; i < num; i++) {
        cin >> M >> N;
        cout << f(M, N) << endl;
    }
    return 0;
}
