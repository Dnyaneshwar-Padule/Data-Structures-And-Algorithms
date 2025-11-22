#include<stdio.h>
#include<stdlib.h>

struct stack{
    int data;
    struct stack *next;
};

typedef struct stack MyStack;

MyStack* myStackCreate() {
    return NULL;
}

/*
    MyStack *m -> stores address of a node
    *m -> operating on the node
    m -> operating on the (can change the address in m), to another node
*/
void myStackPush(MyStack* obj, int x) {
    MyStack *new_node = (MyStack*)malloc(sizeof(MyStack));
    new_node->data = x;
    new_node->next = obj;
    obj = new_node;
}

int myStackPop(MyStack* obj) {
    if( ! obj) return 0;
    int data = obj->data;
    MyStack *top = obj;
    obj = top->next;
    free(top);
    return data;
}

int myStackTop(MyStack* obj) {
    if( ! obj )
        return 0;
    return obj->data;
}

int myStackEmpty(MyStack* obj) {
    return obj == NULL;
}

void myStackFree(MyStack* obj) {
    MyStack *current = obj;
    while( obj ){
        current = obj;
        obj = obj->next;
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
