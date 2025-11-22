/*
    In this hashing program,
    I will use two arrays, one for storing KEYS and other for storing their values
    and will use a simple hash function
    key % array_length 
    for obtaing the array index.
    if we have two same keys, then there will be a collision, and the new value will be over written on the old value

*/

#include<stdio.h>
#include<stdlib.h>
#define LENGTH 50

int values[LENGTH];
int keys[LENGTH];

void init()
{
    for(int i = 0; i < LENGTH; ++i)
    {
        keys[i] = -1;
        values[i] = -1;
    }
}

int get_value(int key)
{
    int index = key % LENGTH;

    if(keys[index] != key)
    {
        printf("Value not found for key : %d\n", key);
    }
    else
    {
        printf("Key : %d, Value %d\n", key, values[index]);
        return values[index];
    }
}

void put_value(int key, int value)
{   
    int index = key % LENGTH;

    if(keys[index] == key)
    {
        printf("Duplicate key found !\n");
    }
    else if(keys[index] != -1)
    {
        printf("Over-writing key and value !");
    }

    keys[index] = key;
    values[index] = value;
}

void display_hashtable()
{
    printf("\n+-----+-------+\n");
    printf("| Key | Value |\n");
    printf("+-----+-------+\n");

    for(int i = 0; i < LENGTH; ++i)
    {
        //displaying only valid keys 
        if(keys[i] != -1)
        {
            printf("| %3d | %5d |\n", keys[i], values[i]);
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