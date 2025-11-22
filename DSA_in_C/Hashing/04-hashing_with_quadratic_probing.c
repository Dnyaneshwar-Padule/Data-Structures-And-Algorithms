/*

Quadratic probing is improved version of linear probing, it reduces clustering, 
but does not completely prevent it.
Clustering can still happen, when multiple keys are mapped with same hash key.

Quadratic probing uses formula
    next_hash = (hash + i^2) % table_size

*/

#include<stdio.h>
#include<stdlib.h>

#define LENGTH 11
#define OCCUPIED 1
#define NOT_OCCUPIED 0

typedef struct 
{
    int key;
    int value;
    int is_occupied;
}Entry;

Entry hash_table[LENGTH];

void init(){
    for(int i = 0; i < LENGTH; i++)
        hash_table[i].is_occupied = NOT_OCCUPIED;        
}

void put_value(int key, int value){
    int index = abs(key) % LENGTH;
    int i, new_index;

    for(i = 0; i < LENGTH; ++i){
        new_index = (index + (i * i)) % LENGTH;
        if(hash_table[new_index].is_occupied == OCCUPIED){
            if(hash_table[new_index].key == key){
                printf("Key %d already exists !\nOver-writing value !\n", key);
                hash_table[new_index].value = value;
                return;
            }
        }
        else{
            hash_table[new_index].value = value;
            hash_table[new_index].key = key;
            hash_table[new_index].is_occupied = OCCUPIED;
            return;
        }
    }

    printf("Hash table is full !\n");
}

int get_value(int key)
{
    int index = abs(key) % LENGTH;
    int i, new_index;

    for(i = 0; i < LENGTH; ++i){
        new_index = (index + (i * i)) % LENGTH;
        if(hash_table[new_index].is_occupied == NOT_OCCUPIED) 
            break;
        else if( hash_table[new_index].key == key){
            printf("{key:%d, value:%d}\n", key, hash_table[new_index].value);
            return hash_table[new_index].value;
        }
    }

    printf("Value not found for key %d\n", key);
    return -1;
}


void display_hashtable()
{
    printf("\n+-----+-------+\n");
    printf("| Key | Value |\n");
    printf("+-----+-------+\n");

    for(int i = 0; i < LENGTH; ++i)
    {
        //displaying only occupied buckets/slots 
        if(hash_table[i].is_occupied == 1)
        {
            printf("| %3d | %5d |\n", hash_table[i].key, hash_table[i].value);
        }   
    }
    
    printf("+-----+-------+\n");
}


int main()
{
    init();
    int choice,key,value;

    while (1)
    {
        printf("\n1. Put a key value pair\n");
        printf("2. Get value by it's key\n");
        printf("3. Display hash table\n");
        printf("4. Exit\n");
        printf("Enter choice : ");
        scanf("%d", &choice);

        switch (choice)
        {
        case 1:
            printf("Enter a key : ");
            scanf("%d", &key);
            printf("Enter the value : ");
            scanf("%d", &value);
            put_value(key, value);
            break;
        
        case 2:
            printf("Enter the key : ");
            scanf("%d", &key);
            get_value(key);
            break;
        case 3:
            display_hashtable();
            break;
        case 4:
            exit(0);
            break;
        default:
            printf("Invalid choice !\n");
        }
    }

    return 0;
}
