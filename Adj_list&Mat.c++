
#include <bits/stdc++.h>
using namespace std;

int adj[100][100];
int visited[10];
int fnode;

int main(){
    int node,edge, n1,n2;
    printf("Enter Edge and node : ");
    scanf("%d%d",&edge,&node);

    vector<int> vec[node];

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
