#include<stdio.h>
#include<stdlib.h>


struct list_node{
    int data;
    long ptr_diff;
};

typedef struct list_node list_node;

list_node *head;

void insert(int data){
    long prev = 0;
    long next = 0;
    list_node *cur;

    list_node *new_node = (list_node*)malloc(sizeof(list_node));
    new_node->data = data;
    
    if(head == NULL){
        head = new_node;
        new_node->ptr_diff = prev ^ next;
        return;
    }
    else if(! head->ptr_diff){
        head->ptr_diff = prev ^ (long) new_node;
        new_node->ptr_diff = next ^ (long) head;
        return;
    }

    cur = head;
    next = cur->ptr_diff ^ prev;

    while(next){
        prev = (long)cur;
        cur = (list_node*)next;
        next = cur->ptr_diff ^ prev;
    }

    cur->ptr_diff = prev ^ (long)new_node;
    new_node->ptr_diff = (long)cur ^ next;

}


void display(){
    if (head == NULL)
        return;

    long prev = 0, next = 0;
    list_node *cur = head;

    while (cur){
        printf("%d -> ", cur->data);

        next = cur->ptr_diff ^ prev;
        prev = (long)cur;
        cur = (list_node*)next;
    }

    printf("NULL\n");
}

int main(){
    head = NULL;

    insert(1);
    insert(2);
    insert(3);
    insert(4);
    insert(5);
    insert(6);

    display();
}