/*

    In adjancy Matrix Representation for undirected graph, 
    we use a 2D matrix, of size [v][v], where V is the no. of vertices

    and for every edge{u,v}, we assign 1 in the matrix[u][v] and matrix[v][u]
    it represents, edge e is going from u to v, as well as  v to u (undirected -> both directions)

    and to print all vertices connected to specific vertex,
    iterate in matrix[u][i], if there is 1, there is an edge between {u,i} else not

    ex. 
        A ------- B
        |        /| 
        |      /  |
        |    /    |
        |  /      | 
        |/        |
        D ------- c 

        This, is an undirected graph with 4 vertices, {A,B,C,D}
        The AdjancyMatrix[4][4], will look like

        ++===++===+===+===+===++    
        ||   || A | B | C | D ||
        ++===++===+===+===+===++    
        || A || 0 | 1 | 0 | 1 ||
        ++---++---+---+---+---++    
        || B || 1 | 0 | 1 | 1 ||
        ++---++---+---+---+---++    
        || C || 0 | 1 | 0 | 1 ||
        ++---++---+---+---+---++    
        || D || 1 | 1 | 1 | 0 ||
        ++===++===+===+===+===++    
*/

#include<stdio.h>
#include<stdlib.h>

int **adjacency_matrix;
int V = 0;
int E = 0;

void clear_adjacency_matrix(){
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
    if(! adjacency_matrix){
        printf("Unable to allocate memory for adjacency Matrix !\n");
        exit(0);
    }

    for (int i = 0; i < V; ++i){
        adjacency_matrix[i] = (int*)calloc(V, sizeof(int));
        if(! adjacency_matrix[i]){
            printf("Unable to allocate memoru for adjacency matrix !\n");
            clear_adjacency_matrix(); // clear allocated memory
            exit(0);
        }
    }
}

void add_edge(int u, int v){
    if(u < V && u >= 0 && v < V && v >= 0){
        adjacency_matrix[u][v] = 1;
        adjacency_matrix[v][u] = 1;
    }
    else{
        printf("Invalid no. of vertex !\n");
        return;
    }
}

void print_incidents_of(int v){
    if(v >= V ){
        printf("Invalid vertex %d.\n", v);
        return;
    }

    printf("Incidents of %d\n", v);
    for(int i = 0; i < V; ++i){
        if(adjacency_matrix[v][i])
            printf("\t{%d, %d}\n", v, i);
    }
}

int degree_of(int v){
    if(v >= V){
        printf("Invalid vertex %d.\n", v);
        return -1;
    }

    unsigned int cnt = 0;
    for(int i = 0; i < V; ++i){
        if(adjacency_matrix[v][i])
            cnt++;
    }

    return cnt;
}

void display(){
    printf("\nAdjacency Matrix:\n   ");
    for(int i = 0; i < V; ++i)
        printf("%3d", i);

    printf("\n   ");
    for(int i = 0; i < V; ++i)
        printf("---");

    printf("\n");
    for(int i = 0; i < V; ++i ){
        printf("%2d |", i);
        for(int j = 0; j < V; ++j)
            printf("%3d", adjacency_matrix[i][j]);
        printf("\n");
    }
    printf("\n");
}

void display_degree(){
    int degree;
    for(int i = 0; i < V; ++i){
        degree = degree_of(i);
        printf("Degree of vertex:%d is %d\n", i, degree);
    }
}

void display_incidents(){
    printf("Displaying incidents of each vertex\n");
    for(int i = 0; i < V; ++i)
        print_incidents_of(i);
}

void accept(){

    printf("Enter the number of Vertices : ");
    scanf("%d", &V);
    
    printf("Enter the  number  of Edges  : ");
    scanf("%d", &E);

    if( E > (V * (V - 1 ) / 2)){
        printf("Too many edges !\n");
        exit(0);
    }

    init();
    int u,v;

    printf("Enter Edges\n");
    for(int i = 0; i < E; ++i){
        printf("Edge (u,v) : ");
        scanf("%d,%d", &u,&v);
        
        if (u == v) {
            printf("Self-loops not allowed! {%d,%d}\n", u, v);
            i--;
            continue;
        }

        add_edge(u,v);
    }
}


int main(){
    accept();
    display();
    display_degree();
    display_incidents();
    clear_adjacency_matrix();
    return(0);
}