#include <stdio.h>
#include <stdlib.h>

#define MAX 100

typedef struct {
    int u, v, weight;
} Edge;

Edge edges[MAX];
int parent[MAX];

int find(int i) {
    if (parent[i] != i)
        parent[i] = find(parent[i]);
    return parent[i];
}

void unionSet(int u, int v) {
    int rootU = find(u);
    int rootV = find(v);
    parent[rootU] = rootV;
}

int compare(const void *a, const void *b) {
    return ((Edge *)a)->weight - ((Edge *)b)->weight;
}

int main() {
    int V, E;
    
    printf("Enter number of vertices and edges: ");
    scanf("%d %d", &V, &E);

    printf("Enter edges (u v weight):\n");
    for (int i = 0; i < E; i++) {
        scanf("%d %d %d", &edges[i].u, &edges[i].v, &edges[i].weight);
    }

    for (int i = 0; i < V; i++)
        parent[i] = i;

    qsort(edges, E, sizeof(Edge), compare);

    int mstWeight = 0;

    printf("\nEdges in MST:\n");

    for (int i = 0; i < E; i++) {
        int u = edges[i].u;
        int v = edges[i].v;

        if (find(u) != find(v)) {
            printf("%d -- %d == %d\n", u, v, edges[i].weight);
            mstWeight += edges[i].weight;
            unionSet(u, v);
        }
    }

    printf("\nTotal weight of MST = %d\n", mstWeight);
    return 0;
}