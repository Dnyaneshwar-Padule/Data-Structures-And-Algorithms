/*
    Probing:
        When a collision happens, i.e, two keys are mapped to the same index then 
        the we allot a new index/slot/bucket to the new key, by incrementing the probe linearally
        by a fixed step
        Formula : (hash + i * step_size) % hash_table_size
        where,
            hash is current/base index or collied index/slot/bucket
            i = no. of increments (increment till table size)
            step_size is the interval between two probes
            
            last mod operation is necessary, so that the probe won't go outside the array bound (table size)

    The step_size and table_size should be relatively prime to each other
    i.e. their GCD should be 1.
    due to this, all slots will get checked
    if there is an another common divisior, all the slots won't get checked
    ex. table_size = 10, hash = 2, step_size = 5
    10 and 5 have GCD 5.
    (2 + 0 * 5) % 10 = 2
    (2 + 1 * 5) % 10 = 7
    (2 + 2 * 5) % 10 = 2
    (2 + 3 * 5) % 10 = 7
    it will loop around 2, 7 i.e. only 2nd and 7th slot will be checked, all other slots will never visited

    but if, 
    table-size = 10, hash = 2, step_size = 3
    then,
    (2 + 0 * 3) % 10 = 2
    (2 + 1 * 3) % 10 = 5
    (2 + 2 * 3) % 10 = 8
    (2 + 3 * 3) % 10 = 1
    (2 + 4 * 3) % 10 = 4
    (2 + 5 * 3) % 10 = 7
    (2 + 6 * 3) % 10 = 0
    (2 + 7 * 3) % 10 = 3
    (2 + 8 * 3) % 10 = 6
    (2 + 9 * 3) % 10 = 9
    See, here probe is incrementing by size 3, and all slots are visited.

    if the table size is prime itself, then any step will be relatuvely prime to it.
    and, greater step size won't fix clustering, it will just change the probe pattern.

*/

#include<stdio.h>
#include<stdlib.h>
#define LENGTH 20
#define STEP_SIZE 7
#define OCCUPIED 1
#define NOT_OCCUPIED 0

typedef struct
{
    int key;
    int value;
    int is_occupied;
}Entry;

Entry hash_table[LENGTH];

void init()
{
    for(int i = 0; i < LENGTH; ++i)
        hash_table[i].is_occupied = NOT_OCCUPIED;
}

int abs(int n)
{
    return n > 0 ? n : -(n);
}

void put_value(int key, int value)
{
    int index = abs(key) % LENGTH;
    int i, new_index;

    for(i = 0; i < LENGTH; ++i){
        new_index = (index + i * STEP_SIZE) % LENGTH;
        if(hash_table[new_index].is_occupied == OCCUPIED){
            if(hash_table[new_index].key == key){
                printf("Key:%d already exists !\nOver-writing the value !\n", key);
                hash_table[new_index].value = value;
                return;
            }
        }
        else{
            hash_table[new_index].is_occupied = OCCUPIED;
            hash_table[new_index].key = key;
            hash_table[new_index].value = value;
            return;
        }
    }

    printf("Hash Table is full, can't insert key:%d\n",key);
}

int get_value(int key)
{
    int index = abs(key) % LENGTH;
    int i, new_index;

    for(i = 0; i < LENGTH; ++i){
        new_index = (index + i * STEP_SIZE) % LENGTH;
        if(hash_table[new_index].is_occupied == NOT_OCCUPIED) 
            break;
        else{
            if(hash_table[new_index].key == key){
                printf("Key:%d | Value:%d\n", key, hash_table[new_index].value);
                return hash_table[new_index].value;
            }
        }
    }

    printf("No value found for key:%d\n", key);
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
        printf("\n\n1. Put a key value pair\n");
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
