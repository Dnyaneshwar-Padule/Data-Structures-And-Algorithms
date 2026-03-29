#include<stdio.h>
#include<stdlib.h>


struct list_node{
    int data;
    long ptr_diff;
};

typedef struct list_node list_node;

list_node *head;


void insert_at_beginning(int data){
    list_node *node = (list_node*)malloc(sizeof(list_node));
    node->data = data;
    node->ptr_diff = 0;

    if(!head){
        head = node;
    }
    else{
        list_node *next = (list_node*)(head->ptr_diff); 
        node->ptr_diff = (long)head;
        head->ptr_diff = (long)node ^ (long)next;
        head = node;
    }
}

void insert_at_end(int data){
    long prev = 0;
    long next = 0;
    list_node *cur;

    list_node *new_node = (list_node*)malloc(sizeof(list_node));
    new_node->data = data;
    
    if(head == NULL){
        head = new_node;
        new_node->ptr_diff = 0;
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

void insert_at(int data, int pos){
    if(pos < 0) return;

    if(pos == 0){
        insert_at_beginning(data);
        return;
    }

    long prev = 0, next = 0;
    list_node* cur = head;

    list_node* node = (list_node*)malloc((sizeof(list_node)));
    node->data = data;
    node->ptr_diff = 0;

    for(int i = 1; i < pos; i++){
        next = prev ^ cur->ptr_diff;
        
        if(! next)
            break;

        prev = (long)cur;
        cur = (list_node*) next;
    }

    next = prev ^ cur->ptr_diff;
    node->ptr_diff = next ^ (long) cur;
    cur->ptr_diff = prev ^ (long) node;

    prev = (long) cur;
    cur = (list_node*)next;

    if(cur){
        next = cur->ptr_diff ^ prev;
        cur->ptr_diff = next ^ (long) node;
    }
}

void delete_first(){
    if(! head)
        return;

    list_node *new_head = (list_node*)head->ptr_diff;
    long next = new_head->ptr_diff ^ (long)head;  
    new_head->ptr_diff = next;
    free(head);
    head = new_head;
}

void delete_last(){
    if (head == NULL)
        return;

    if(head->ptr_diff == 0){
        delete_first();
        return;
    }

    long prev = 0;
    long next = 0;
    list_node *cur = head;
    next = cur->ptr_diff;
 
    while(((list_node*)next)->ptr_diff != (long)cur ) {
        prev = (long)cur;
        cur = (list_node*) next;
        next = cur->ptr_diff ^ prev;
    }

    cur->ptr_diff = prev;
    free((list_node*)next);
}

void delete_at(int pos){
    if (pos < 0 || ! head)
        return;

    if (pos == 0){
        delete_first();
        return;
    }

    long prev = 0, temp = 0;
    list_node* cur = head;
    long next = cur->ptr_diff;

    for(int i = 0; i < pos; i++){
        prev = (long) cur;
        cur = (list_node*)next;

        if(! cur)
            return;

        next = cur->ptr_diff ^ prev;
    }

    temp = ((list_node*)prev)->ptr_diff ^ (long) cur;
    ((list_node*)prev)->ptr_diff = temp ^ next;

    if(next){
        temp = ((list_node*)next)->ptr_diff ^ (long)cur;
        ((list_node*)next)->ptr_diff = temp ^ prev;
    }

    free(cur);
}

list_node* get_at(int pos){
    if(pos < 0 || ! head)
        return NULL;

    int i = 0;
    long prev = 0;
    list_node *cur = head;
    long next = cur->ptr_diff;

    for(int i = 0; i < pos; ++i){
        prev = (long)cur;
        cur = (list_node*)next;
        if(!cur)
            return NULL;

        next = cur->ptr_diff ^ prev;
    }

    return cur;
}

void display(){
    if (head == NULL)
        return;

    long prev = 0, next = 0;
    list_node *cur = head;

    while (cur){
        printf("%d ⟶ ", cur->data);

        next = cur->ptr_diff ^ prev;
        prev = (long)cur;
        cur = (list_node*)next;
    }

    printf("NULL\n");
}

int main(){
    head = NULL;

    insert_at_end(1);
    insert_at_end(2);
    insert_at_end(3);
    insert_at_end(4);
    insert_at_end(5);
    insert_at_end(6);

    insert_at_beginning(11);
    insert_at_beginning(22);
    insert_at_beginning(33);

    insert_at(101,0);
    insert_at(102,2);
    insert_at(103,3);
    insert_at(112,12);

    display();

    delete_first();  // 101 deleted
    display();
    
    delete_first(); // 33 deleted
    display();

    delete_first();
    display();

    delete_first();
    display();

    delete_last();
    display();  

    delete_last();
    display();  
    
    delete_last();
    display();  
    
    delete_at(0);
    display();

    delete_at(3);
    display();

    delete_at(3);
    display();

    delete_at(1);
    display();

    printf("0 ⟶ %d\n", get_at(0)->data);

    printf("1 ⟶ %d\n", get_at(1)->data);
    return 0;
}
