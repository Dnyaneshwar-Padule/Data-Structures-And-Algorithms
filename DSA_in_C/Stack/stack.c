#include<stdio.h>
#include<stdlib.h>

struct stack{
    struct node *top;
};

struct node{
    int data;
    struct node* next;
};

typedef struct node node;
typedef struct stack MyStack;

MyStack* myStackCreate() {
    MyStack *stack = (MyStack*)calloc(1, sizeof(MyStack));
    return stack;
}

/*
    MyStack *m -> stores address of a node
    *m -> operating on the node
    m -> operating on the (can change the address in m), to another node
*/
void myStackPush(MyStack* obj, int x) {
    node *new_node = (node*)malloc(sizeof(node));
    new_node->data = x;
    new_node->next = obj->top;
    obj->top = new_node;
}

int myStackPop(MyStack* obj) {
    if( ! obj) return 0;
    int data = obj->top->data;
    node *top = obj->top;
    obj->top = top->next;
    free(top);
    return data;
}

int myStackTop(MyStack* obj) {
    if( ! obj )
        return 0;
    return obj->top->data;
}

int myStackEmpty(MyStack* obj) {
    return obj == NULL;
}

void myStackFree(MyStack* obj) {
    node *current = obj->top;
    while( obj->top ){
        current = obj->top;
        obj->top = obj->top->next;
        free(current);
    }
}

int main(){

    MyStack *m  = myStackCreate();

    myStackPush(m, 1);
    myStackPush(m, 2);
    myStackPush(m, 3);

    printf("%d\n", myStackTop(m));
    
    printf("%d\n", myStackPop(m));
    printf("%d\n", myStackPop(m));
    printf("%d\n", myStackPop(m));



}
/*
* Your MyStack struct will be instantiated and called as such:
* MyStack* obj = myStackCreate();
* myStackPush(obj, x);

* int param_2 = myStackPop(obj);

* int param_3 = myStackTop(obj);

* bool param_4 = myStackEmpty(obj);

* myStackFree(obj);
*/
