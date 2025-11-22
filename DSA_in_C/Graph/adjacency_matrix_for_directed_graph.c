/*

    In adjancy Matrix Representation for directed graph, 
    we use a 2D matrix, of size [v][v], where V is the no. of vertices

    Direct-Adjacency matrix need not to be symmetric like undirected-adjacency matrix
    and for every edge{u,v}, we assign 1 in the matrix[u][v] 
    it represents, edge e is going from u to v (i.e. u --> v)

    and to print all vertices connected to specific vertex,
    iterate in matrix[u][i], if there is 1, there is an edge between {u,i} else not

    to print indegree of a vertex (v), calculate the count 
    adjacency_matrix[i][v]  <-- count of column(v)

    to print out-degree of a vvertex(v), calculat the count
    adjacency_matrix[v][i]  <-- count of row(v)

    -
  /   \
 |+---+|  
  \   /
    -
    ex.       
        A ------> B
        /\        | 
        |         |
        |         |
        |        \/
        D <------ C 

        This, is an directed graph with 4 vertices, {A,B,C,D}
        The AdjancyMatrix[4][4], will look like

        ++===++===+===+===+===++    
        ||   || A | B | C | D ||
        ++===++===+===+===+===++    
        || A || 0 | 1 | 0 | 0 ||
        ++---++---+---+---+---++    
        || B || 0 | 0 | 1 | 0 ||
        ++---++---+---+---+---++    
        || C || 0 | 0 | 0 | 0 ||
        ++---++---+---+---+---++    
        || D || 1 | 0 | 0 | 0 ||
        ++===++===+===+===+===++    

*/

#include<stdio.h>
#include<stdlib.h>

int **adjacency_matrix;
int E,V;

void clear_adjacency_matrix()
{
    for(int i = 0; i < V; ++i){
        if(adjacency_matrix[i])
            free(adjacency_matrix[i]);
    }
    free(adjacency_matrix);
}

void init(){
    if(V < 2){
        printf("Too few vertices !\n");
        exit(0);
    }

    adjacency_matrix = (int**)malloc(sizeof(int*) * V);
    if(!adjacency_matrix){
        printf("Unable to allocate memory for adjacency matrix !\n");
        exit(0);
    }
    
    for(int i = 0; i < V; i++){
        adjacency_matrix[i] = (int*)calloc(V, sizeof(int));
        if(!adjacency_matrix[i]){
            printf("Unable to allocate memory for adjacency matrix !\n");
            clear_adjacency_matrix();
            exit(0);
        }
    }
}

int add_edge(int u, int v){
    if((u >= V || u < 0 ) || ( v >= V || v < 0)){
        printf("Invalid vertex!\n");
        return 0;
    }
    adjacency_matrix[u][v] = 1;
    return 1;
}

int indegree_of(int v){
    if( v < 0 || v >= V ){
        printf("Invalid vertex %d \n",v);
        return -1;
    }

    int in_degree = 0;
    for(int i = 0; i < V; i++){
        if(adjacency_matrix[i][v])
            in_degree++;
    }
    return in_degree;
}

int outdegree_of(int v){
    if( v < 0 || v >= V ){
        printf("Invalid vertex %d \n",v);
        return -1;
    }

    int out_degree = 0;
    for(int i = 0; i < V; i++){
        if(adjacency_matrix[v][i])
            out_degree++;
    }
    return out_degree;
}

void display() {
    printf("\nDirected Adjacency Matrix:\n    ");
    for (int i = 0; i < V; i++) printf("%3d", i);
    printf("\n   ");
    for (int i = 0; i < V; i++) printf("---");
    printf("\n");

    for (int i = 0; i < V; i++) {
        printf("%2d |", i);
        for (int j = 0; j < V; j++)
            printf("%3d", adjacency_matrix[i][j]);
        printf("\n");
    }
    printf("\n");
}


void print_inward_incidents_of(int v){
    if(v < 0 || v >= V){
        printf("Invalid vertex %d\n", v);
        return;
    }

    printf("\tInward incidents of %d:\n", v);
    for(int i = 0; i < V; ++i){
        if(adjacency_matrix[i][v])
            printf("\t\t{%d, %d}\n", i, v);
    }
}

void print_outward_incidents_of(int v){
    if(v < 0 || v >= V){
        printf("Invalid vertex %d\n", v);
        return;
    }

    printf("\tOutward incidents of %d:\n", v);
    for(int i = 0; i < V; ++i){
        if(adjacency_matrix[v][i])
            printf("\t\t{%d, %d}\n", v, i);
    }
}

void print_incidents(){
    printf("Incidents of every vertex\n");

    for(int i = 0; i < V; ++i){
        printf("Incidents of vertex %d:\n", i);
        print_inward_incidents_of(i);
        print_outward_incidents_of(i);
    }
    printf("\n");
}

/*
    Degree of 1:
        Indegree     : 1
        Outdegree    : 2
        Total Degree : 3 
*/
void print_degree(){
    int total_degree, indegree, outdegree;
    printf("Degree of each vertex\n");

    for(int i = 0; i < V; ++i){
        indegree = indegree_of(i);
        outdegree = outdegree_of(i);
        total_degree = indegree + outdegree;
        printf("Degree of vertex %d:\n",i);
        printf("\tIndegree     : %d\n", indegree);
        printf("\tOutdegree    : %d\n", outdegree);
        printf("\tTotal Degree : %d\n", total_degree);
    }
    printf("\n");
}

void accept(){
    printf("Enter the number of vertices : ");
    scanf("%d", &V);
    
    printf("Enter the  number  of  edges : ");
    scanf("%d", &E);

    if(E > ( V * (V - 1))){
        printf("Too many edges !\n");
        exit(0);
    }

    init();
    int u,v;

    printf("Enter Edges\n");
    for(int i = 0; i < E; i++){
        printf("Edge (u,v) : ");
        scanf("%d,%d", &u,&v);
        if( ! add_edge(u,v) )
            i--;
    }
}

int main(){
    accept();
    display();
    print_incidents();
    print_degree();
    clear_adjacency_matrix();
    return(0);
}