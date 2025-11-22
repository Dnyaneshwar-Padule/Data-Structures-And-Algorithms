/*

    This is as same as simple_hashing.c but the improvement is that I will use an extra field is_occupied
    which will also allow negative values as keys

    I will use a Structure
    struct Entry{
        int key;
        int value;
        int is_occupied;
    };

    is the value of is_occupied is 1, i.e the bucket/slot is already occupied
    else not

*/

#include<stdio.h>
#include<stdlib.h>
#define LENGTH 50

typedef struct{
    int key;
    int value;
    int is_occupied;
}Entry;

Entry hash_table[LENGTH];

/*
void init()
{
    for(int i = 0; i < LENGTH; ++i)
    {
        keys[i] = -1;
        values[i] = -1;
    }
}
*/

int abs(int n)
{
    return n > 0 ? n : -(n);
}

int get_value(int key)
{
    int index = abs(key) % LENGTH;

    if(hash_table[index].key != key)
    {
        printf("Value not found for key : %d\n", key);
    }
    else
    {
        printf("Key : %d, Value %d\n", key, hash_table[index].value);
        return hash_table[index].value;
    }
}

void put_value(int key, int value)
{   
    int index = abs(key) % LENGTH;

    if(hash_table[index].key == key)
    {
        printf("Duplicate key found !\n");
    }
    else if(hash_table[index].is_occupied == 1 )
    {
        printf("Over-writing key and value !\n");
    }

    hash_table[index].is_occupied = 1;
    hash_table[index].key = key;
    hash_table[index].value = value;
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
    //init();
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