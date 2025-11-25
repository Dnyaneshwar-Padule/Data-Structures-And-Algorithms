
#include<stdio.h>
#include<stdlib.h>

struct node{
    struct node* previous;
    int data;
    struct node* next;
};

struct queue{
    struct node *front;
    struct node *rear;
};

typedef struct node node;
typedef struct queue MyQueue;


MyQueue* myQueueCreate() {
    MyQueue* queue = (MyQueue*)calloc(1, sizeof(MyQueue));
    return queue;
}

void myQueuePush(MyQueue* obj, int x) {
    node* new_node = (node*)malloc(sizeof(node));
    new_node->data = x;
    new_node->next = NULL;
    new_node->previous = obj->rear;
    obj->rear->next = new_node;
    obj->rear = new_node;
}

int myQueuePop(MyQueue* obj) {
    node* front = obj->front;
    int data = front->data;
    obj->front = front->next;
    if(obj->front) obj->front->previous = NULL;
    free(front);
    return data;
}

int myQueuePeek(MyQueue* obj) {
    return obj->front->data;
}

int myQueueEmpty(MyQueue* obj) {
    return obj->front == NULL;
}

void myQueueFree(MyQueue* obj) {
    node* current = obj->front;
    while(obj->front){
        current = obj->front;
        obj->front = obj->front->next;
        free(current);
    }
    obj->front = NULL;
    obj->rear = NULL;
}

/**
 * Your MyQueue struct will be instantiated and called as such:
 * MyQueue* obj = myQueueCreate();
 * myQueuePush(obj, x);
 
 * int param_2 = myQueuePop(obj);
 
 * int param_3 = myQueuePeek(obj);
 
 * bool param_4 = myQueueEmpty(obj);
 
 * myQueueFree(obj);
*/