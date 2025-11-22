#include<stdio.h>
#include<stdlib.h>

#define NEWNODE (struct node*)malloc(sizeof(struct node))

struct node
{
    int vertex;
    struct node *next;
};
typedef struct node Node;

Node **adjancency_list;
int V,E;

void init(int n){
    if(n < 1){
        printf("Too few Vertices !\n");
        exit(0);
    }

    adjancency_list = (Node**)calloc(V,sizeof(Node*));
    if(!adjancency_list){
        printf("Unable to allocate memory for adjacency list !\n");
        exit(0);
    }
}

// u -> v
int insert_edge(Node** l, int u, int v){
    if(u < 0 || u >= V || v < 0 || v >= V){
        printf("Invalid vertices !\n");
        return 0;
    }

    Node* new_node = NEWNODE;
    if(! new_node){
        printf("Unable to allocate memory for new node!\n");
        return 0;
    }

    new_node->vertex = v;
    new_node->next = NULL;

    if( ! l[u] ){
        l[u] = new_node;   
    }
    else{
        new_node->next = l[u];
        l[u] = new_node;
    }

    return 1;
}

int get_degree_of(Node **l, int v){
    if(v < 0 || v >= V){
        printf("Invalid Vertex !\n");
        return -1;
    }

    int degree = 0;
    Node* t = l[v];

    while (t){
        degree++;
        t = t->next;
    }
    return degree;
}

void display(Node** l){
    Node *t = NULL;
    printf("\nUndirected Adjacency Matrix:\n");

    for(int i = 0; i < V; ++i){
        printf("V%d --> ", i);
        t = l[i];
        while (t){
            printf("[%d] --> ", t->vertex);
            t = t->next;
        }
        printf("NULL\n");
    }
    printf("\n");
}

void free_all(Node** l){
    Node *c = NULL, *p = NULL;
    for(int i = 0; i < V; ++i){
        c = l[i];
        while (c){
            p = c;
            c = c->next;
            free(p);
        }
    }
    free(l);
}

void accept(){
    printf("Enter the number of Vertices : ");
    scanf("%d", &V);
    
    printf("Enter the  number  of  Edges : ");
    scanf("%d", &E);

    if( E > ( (V * (V-1))/2) ){
        printf("Too many edges\n");
        exit(0);
    }

    init(V);
    int u,v;
    printf("Enter Edges :\n");
    for(int i = 0; i < E; ++i){
        printf("Enter (u,v) : ");
        scanf("%d,%d", &u, &v);

        if(u == v){
            printf("Self looping is not allowed in undirected graph !\n");
            i--;
            continue;
        }

        insert_edge(adjancency_list, u, v);
        insert_edge(adjancency_list, v, u);
    }
}

int main(){
    accept();
    display(adjancency_list);
    free_all(adjancency_list);
    return 0;
}