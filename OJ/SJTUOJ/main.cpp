/*
#include<stdio.h>
int v[12],list[12],n,t,sum,path[1000][14],count;
void dfs(int x)
{
	if(x>=n)
	{
		if(sum==t)
		{
			int i,j=1;
			for(i=0;i<n;i++)
				if(v[i])
					path[count][j++]=list[i];
			path[count][0]=j-1;
			count++;
		}
		return;
	}
	if(sum+list[x]<=t)
	{
		v[x]=1;
		sum+=list[x];
		dfs(x+1);
		sum-=list[x];
		v[x]=0;
	}
	dfs(x+1);
}
int main()
{
	int i,j,k,l;
	while(scanf("%d%d",&t,&n),n)
	{
		count=sum=0;
		for(i=0;i<n;i++)
			scanf("%d",list+i);
		printf("Sums of %d:\n",t);
		dfs(0);
		if(!count)
			printf("NONE\n");
		else
		{
			l=path[0][0];
			for(i=1;i<=l;i++)
				printf("%s%d",i-1?"+":"",path[0][i]);
			putchar('\n');
			for(i=1;i<count;i++)
			{
				l=path[i][0];
				for(k=0;k<i;k++)
				{
					for(j=0;j<=l;j++)
						if(path[i][j]!=path[k][j])
							break;
					if(j>l)
						break;
				}
				if(k<i)
					continue;
				for(j=1;j<=l;j++)
					printf("%s%d",j-1?"+":"",path[i][j]);
				putchar('\n');
			}
		}
	}
	return 0;
}
*/

#include <stdio.h> 

int board[20001][20001]; 
static int domino_num= 1; 

void chessboard(int r1, int c1, int r2, int c2, int size) 
{ 
	if(1 == size) return; 
	int half_size; 

	int d = domino_num++; 
	half_size = size / 2;    

	if(r2 < r1 + half_size && c2 < c1 + half_size)  
	{ 
		chessboard(r1, c1, r2, c2, half_size);  
	} 
	else    
	{ 
		board[r1 + half_size - 1][c1 + half_size - 1] = d; 
		chessboard(r1, c1, r1 + half_size - 1, c1 + half_size - 1, half_size); 
	} 

	if(r2 < r1 + half_size && c2 >= c1 + half_size)  
	{ 
		chessboard(r1, c1 + half_size, r2, c2, half_size); 
	} 
	else   
	{ 
		board[r1 + half_size - 1][c1 + half_size] = d; 
		chessboard(r1, c1 + half_size, r1 + half_size - 1, c1 + half_size, half_size); 
	} 

	if(r2 >= r1 + half_size && c2 < c1 + half_size)  
	{ 
		chessboard(r1 + half_size, c1, r2, c2, half_size); 
	} 
	else   
	{ 
		board[r1 + half_size][c1 + half_size - 1] = d; 
		chessboard(r1 + half_size, c1, r1 + half_size, c1 + half_size - 1, half_size); 
	} 

	if(r2 >= r1 + half_size && c2 >= c1 + half_size)  
	{ 
		chessboard(r1 + half_size, c1 + half_size, r2, c2, half_size); 
	} 
	else    
	{ 
		board[r1 + half_size][c1 + half_size] = d; 
		chessboard(r1 + half_size, c1 + half_size, r1 + half_size, c1 + half_size, half_size); 
	}    
} 

int main() 
{ 
	int i,n,a,b,x,j,c; 
	scanf("%d",&n); 
	for(c=0;c<n;c++) 
	{ 
		scanf("%d",&x); 
		scanf("%d%d",&a,&b); 
		board[a][b] = 0; 
		chessboard(0, 0, a, b, x); 
		printf("CASE:%d\n",c+1); 
		for(i = 0; i < x; i++) 
		{   printf("%d",board[i][0]); 
		for(j = 1; j < x; j++) 
		{ 
			printf("\t%d", board[i][j]); 
		} 

		printf("\n"); 
		domino_num= 1; 
		} 

	} 
	return 0; 
} 