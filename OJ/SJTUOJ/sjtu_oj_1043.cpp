#include <iostream>
using namespace std;

class Node
{public:
	Node * left;
	Node * right;
	Node()
	{
		this->left = NULL;
		this->right = NULL;
	}
};
Node nodes[1000000];
Node* q[1000000] = {NULL};
int head=0, tail=0;

void enqueue(Node* item)
{
	q[tail++] = item;
}

Node* dequeue()
{
	Node* item = q[head];
	q[head] = NULL;
	head++;
	return item;
}

int main()
{
	int num;
	cin >> num;

	for(int i = 1; i < num; i++)
	{
		int parent;
		cin >> parent;
		if(nodes[parent].left == NULL) nodes[parent].left = &nodes[i];
		else if(nodes[parent].right == NULL) nodes[parent].right = &nodes[i];
		else {
			cout<<"false"<<endl;
			return 0;
		}
	}
	enqueue(&nodes[0]);
	Node *top;
	while((top = dequeue())!= NULL)
	{
		enqueue(top->left);
		enqueue(top->right);
	}
	while(head != tail)
	{
		top = dequeue();
		if(top != NULL)	{
			cout<<"false"<<endl;
			return 0;
		}
	}

	cout<<"true"<<endl;
	return 0;
}