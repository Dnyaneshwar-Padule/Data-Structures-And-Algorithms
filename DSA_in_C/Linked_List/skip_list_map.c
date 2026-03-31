// For now, I will take int as key and value
#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#include<limits.h>

#define NEW_LISTNODE (struct list_node*)calloc(sizeof(struct list_node), 1)

struct list_node{
    struct list_node *down;
    struct list_node *next;
    
    // using int as key is not currect, but for now, we will use it
    int key;
    int value;

    int level;
};

struct skip_list_map{
    struct list_node *head;
    int size;
    float p;
};

typedef struct list_node list_node;
typedef struct skip_list_map skip_list_map;

skip_list_map *list_map;

void init(){
    static int seeded = 0;
    if(! seeded){
        srand(time(NULL));
        seeded = 1;
    }

    list_map = (skip_list_map*)calloc(sizeof(skip_list_map), 1);
    list_map->head = NEW_LISTNODE;
    list_map->p = 0.5;
}

int rand_level(){
    int level =  0;
    while((float)rand() / RAND_MAX < list_map->p && list_map->head->level >= level){
        ++level;
    }
    return level;
}

void add(int key, int val){
    int level = rand_level();

    if(level > list_map->head->level){
        list_node *new_head = NEW_LISTNODE;
        new_head->level = level;
        new_head->down = list_map->head;
        list_map->head = new_head;
    }


    list_node *cur = list_map->head, *last = NULL;

    while(cur){
        // the next of current is null, or the key next to current is greater than the key to add, we can insert the node here, if the level is same or greater
        if(! cur->next || (cur->next->key > key)){
            if(level >= cur->level){
                list_node *new_node = NEW_LISTNODE;
                new_node->key = key;
                new_node->value = val;
                new_node->level = cur->level;
                
                if(last)
                last->down = new_node;
                
                new_node->next = cur->next;
                cur->next = new_node;
                last = new_node;
            }
            cur = cur->down;
        }
        else if(cur->next->key == key){
            if(last)
                last->down = cur->next;
                
            cur->next->value = val;
            last = cur->next;        
            cur = cur->down;            // Update all values
        }
        else{
            cur = cur->next;
        }

        
    }

    list_map->size++;
}

int get(int key){
    if(!list_map || !list_map->head)
        return INT_MIN;

    list_node *cur = list_map->head;

    while (cur){
        if(! cur->next || cur->next->key < key){
            cur = cur->down;
        }
        else if(cur->next->key == key){
            return cur->next->value;
        }
        else{
            cur = cur->next;
        }
    }
    
    return INT_MIN;
}

int contains(int key){
    if(!list_map  || !list_map->head)
        return 0;
    
    list_node *cur = list_map->head;

    while(cur){
        if(! cur->next || cur->next->key > key){
            cur = cur->down;
        }
        else if(cur->next->key == key){
            return  1;
        }
        else{
            cur = cur->next;
        }
    }

    return 0;
}

int delete(int key){
    if(!list_map || ! list_map->head)
        return 0;

    int deleted = 0;
    list_node *cur = list_map->head;

    while (cur){
        if(! cur->next || cur->next->key > key){
            cur = cur->down;
        }
        else if(cur->next->key == key){
            list_node *next = cur->next;
            cur->next = next->next;
            free(next);
            deleted = 1;
            cur = cur->down; // Free all nodes
        }
        else{
            cur = cur->next;
        }
    }
    
    if(deleted)
        list_map->size--;

    return deleted;
}

void display(){
    if(!list_map || ! list_map->head)
        return;

    list_node *cur = list_map->head, *down = NULL;

    while (cur){
        down = cur->down;
        printf("Level-%2d:", cur->level);
        while (cur){
            printf("[%2d:%2d:%2d(%2d)] ⟶ ", cur->key, cur->value, (cur->down) ? cur->down->key : 0, (cur->down) ? cur->down->level : 0 );
            cur = cur->next;
        }
        
        printf("NULL \n");
        cur = down;
    }
    
}

void clear(){
    if(!list_map || ! list_map->head)
        return;

        list_node *cur = list_map->head, *down = cur->down, *next = NULL;

        while (cur){
            down = cur->down;

            while ( cur ){
                next = cur->next;
                free(cur);
                cur = next;
            }

            cur = down;
        }
        
    
    list_map->size = 0;
    list_map->head = NULL;        
}

int main(){
    init();

    add(1,1);
    add(2,1);
    add(3,1);
    add(4,1);
    add(5,1);
    add(6,1);
    add(7,1);
    add(8,1);
    add(8,1);
    
    display();

    printf("=====================================================================\n");

    add(1,1);
    add(2,1);
    add(3,1);
    add(4,1);
    add(5,1);
    add(9,1);
    
    display();

    clear();
    return 0;
}