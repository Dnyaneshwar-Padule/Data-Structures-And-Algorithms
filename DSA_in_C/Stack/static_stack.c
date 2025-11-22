#include<stdio.h>
#define SIZE 50

int stack[SIZE];
int top;

void init(){
    top = -1;
}

int is_full(){
    return top >= SIZE;
}

int is_empty(){
    return top < 0;
}

void push(int data){
    if (is_full()){
        printf("Stack is full !\n");
        return;
    }
    stack[++top] = data;
}

int pop(){
    if(is_empty()){
        printf("Stack is empty !\n");
        return -1;
    }
    return stack[top--];
}

int main(){
    init();
    push(1);
    push(2);
    push(3);
    push(4);
    push(5);
    push(6);

    printf("%d\n", pop());
    printf("%d\n", pop());
    printf("%d\n", pop());
    printf("%d\n", pop());
    printf("%d\n", pop());
    printf("%d\n", pop());
    printf("%d\n", pop());
    return 0;
}