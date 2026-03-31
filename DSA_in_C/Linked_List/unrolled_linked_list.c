#include<stdio.h>
#include<stdlib.h>
#include<limits.h>

#define NEWNODE (struct list_node*)malloc(sizeof(struct list_node))

struct list_node{
    struct list_node *previous;
    struct list_node *next;
    int *elements;
    int elementCount;
};

typedef struct list_node list_node;

list_node *first_node;
list_node *last_node;
int capacity;
int size;



int* alloc_arr(int len){
    if(len < 1 || len > capacity)
        return NULL;
    
    return (int*)calloc(sizeof(int), len);
}

void init(int cap){
    if(cap < 4)
        cap = 4;

    if(cap > 16)
        cap = 16;

    capacity = cap;
    first_node = NEWNODE;

    last_node->next = NULL;
    last_node->previous = NULL;
    first_node->elementCount = 0;
    first_node->elements = alloc_arr(capacity);

    last_node = first_node;
    size = 0;
}

void clear(){
    list_node *cur = NULL;

    while(first_node){
        cur = first_node;
        first_node = first_node->next;
        free(cur->elements);
        free(cur);
    }
    first_node = last_node = NULL;
    size = 0;
}

void add_to_node(list_node* node, int index, int element){
    if(node == NULL)
        return;

    if(node->elementCount == capacity){
        list_node *new_node = NEWNODE;
        new_node->next = NULL;
        new_node->previous = NULL;
        new_node->elementCount = 0;
        new_node->elements = alloc_arr(capacity);

        int elements_to_move = capacity / 2;
        int starting_index = node->elementCount - elements_to_move;

        for(int i = 0; i < elements_to_move; ++i){
            new_node->elements[i] = node->elements[starting_index + i];
            node->elements[starting_index + i] = 0;
        }

        node->elementCount = elements_to_move;
        new_node->elementCount = elements_to_move;
        
        new_node->next = node->next;
        new_node->previous = node;

        if(node->next)
            node->next->previous = new_node;
        
        node->next = new_node;

        if(last_node == node)
            last_node = new_node;


        if(index > node->elementCount){
            node = new_node;
            index -= node->elementCount;
        }
    }

    for(int i = node->elementCount; i > index; i--){
        node->elements[i] = node->elements[i - 1];
    }

    node->elements[index] = element;
    node->elementCount++;
    size++;
}

void merge_with_next_node(list_node *node){
    list_node *next = node->next;

    for(int i = 0; i < next->elementCount; i++){
        node->elements[node->elementCount + i] = next->elements[i];
    }

    node->elementCount += next->elementCount;
    
    if(next->next)
        next->next->previous = node;
    
    node->next = next->next;

    if(last_node == next);
        last_node = node;
}

void delete_from_node(list_node* node, int index){
    if(!node)
        return;

    node->elementCount--;

    for (int i = index; i < node->elementCount; i++){
        node->elements[i] = node->elements[i + 1];
    }

    if(node->next && (node->elementCount + node->next->elementCount) <= capacity)
        merge_with_next_node(node);

    if(node->previous && (node->elementCount + node->previous->elementCount) <= capacity)
        merge_with_next_node(node->previous);

}


void add(int element){
    if(!last_node){
        init(capacity);
    }

    add_to_node(last_node, last_node->elementCount, element);
}

int get(int index){
    if(index < 0 || index >= size)
        return INT_MIN;
    
    list_node *cur = first_node;
    int p = 0;

    if(size - index > index){
        while(p <= (index - cur->elementCount)){
            p += cur->elementCount;
            cur = cur->next;
        }
    }
    else{
        cur = last_node;
        p = size;
        while((p -= cur->elementCount) > index){
            cur = cur->previous;
        }
    }

    return cur->elements[index - p];

}

int add_to(int element, int index){
    if(index < 0 || index >= size)
        return 0;
    
    list_node *cur = NULL;
    int p = 0;

    if(size - index > index){
        cur = first_node;
        
        while(p <= (index - cur->elementCount)){
            p += cur->elementCount;
            cur = cur->next;
        }
    }
    else{
        cur = last_node;
        p = size;

        while((p -= cur->elementCount) > index){
            cur = cur->previous;
        }
    }

    add_to_node(cur, p - index, element);
    size++;
}

int delete(int index){
    if(index < 0 || index >= size)
        return INT_MIN;

        list_node *cur = NULL;
    int p = 0;

    if(size - index > index){
        cur = first_node;
        
        while(p <= (index - cur->elementCount)){
            p += cur->elementCount;
            cur = cur->next;
        }
    }
    else{
        cur = last_node;
        p = size;

        while((p -= cur->elementCount) > index){
            cur = cur->previous;
        }
    }

    int ele = cur->elements[index - p];
    delete_from_node(cur, index - p);
    size--;
    return ele;
}

int main(){


}