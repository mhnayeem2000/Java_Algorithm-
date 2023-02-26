
#include <bits/stdc++.h>
using namespace std;
int visited[10];

vector<int> vec[7];
int adj[100][100];
int fnode;

void dfs(int nod)
{
    if (visited[nod])
        {   //printf("\t%d\t",visited[nod]);
            return;
        }
    //printf("%d\n",visited[nod]);
    visited[nod] = 1;
    cout << nod << "\t";

    for (int i = 0; i < vec[nod].size(); i++)
    {
        dfs(vec[nod][i]);
    }
}



//BFS

void bfs(int s, int n)
{
    queue<int> Q;
    Q.push(s);
    visited[s] = 1;
    printf("Outside while loop");
    while (!Q.empty())
    {
        int u = Q.front(); //printing first value
        cout << u << "\t";
        Q.pop();
            printf("While loop");
        for (int i = 0; i < vec[u].size(); i++)
        {
            printf("1st for loop");

            if (visited[vec[u][i]] == 0)
            {
                int v = vec[u][i];
                visited[v] = 1;
                Q.push(v);

            }

        }
    }
}



int main(){
    int node,edge, n1,n2;
    printf("Enter Edge and node : ");
    scanf("%d%d",&edge,&node);

    //vector<int> vec[node];

    for(int i=0; i<=edge;i++)
        visited[i]=0;


    printf("Enter Your Edges : \n");
    for(int i = 0 ; i < edge; i++){
        scanf("%d%d",&n1,&n2);
        //for list
        vec[n1].push_back(n2);
        vec[n2].push_back(n1);

        //for matrix;

        adj[n1][n2] = 1;
        adj[n2][n1] = 1;
    }

    cout << "Printing graph using DFS:" << endl;
    dfs(0);


    printf("\n \n ");
    //print list
     printf(" Adjacency List : \n \n ");
    for(int i = 0 ; i < node; i++){
            cout << "vertex  " << i << " : ";
        for(int j = 0; j < vec[i].size(); j++ ){
            cout << " -> " << vec[i][j];
        }
        printf("\n");
    }
    printf("\n \n ");
    printf(" Adjacency Matrix : \n \n ");
    //print matrix

    for(int i = 1 ; i < node ; i ++){
        for(int j = 1 ; j < node ; j++){
            printf("%d \t",adj[i][j]);
        }
        printf("\n");
    }

    cout << "Printing graph using BFS:" << endl;
    bfs(0,6);



}



/**
7 8
0 1
0 4
0 6
1 2
2 3
2 4
4 5
5 6


*/
