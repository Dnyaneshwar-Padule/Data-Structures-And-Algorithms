/*

    Here, we use a formula
    index(i) = ( h1(k) + i * h2(k) ) % M
    where, 
        M = size of hash-table
        h1(k) is a hash function, with hashing like (key % M)
        h2(k) is another hash function with hashing like (R - (key % R))
            where, R is a step size, smaller than M
            and R should be a prime number
            h2(k) should nnot be zero, it will cause equation like ( h1(k) + i * 0 ), which is problematic
        
    This reduces primary clustering, but takes longer time compared to linear and quadratic probing
    because it computes two hashes
*/

#include<stdio.h>
#include<stdlib.h>
#define M 11
#define R 7
#define NOT_OCCUPIED 0
#define OCCUPIED 1
#define TOMBSTONE 2

typedef struct 
{
    int key;
    int value;
    int is_occupied;
}Entry;

Entry hash_table[M];

void init(){
    for(int i = 0; i < M; ++i)
        hash_table[i].is_occupied = NOT_OCCUPIED;
}

int h1(int key){
    return abs(key) % M;
}

int h2(int key){
    /*
        if we write, just key % R, it can cause the hash to be 0. and to avoid that
        we write it as, R - (k % R), even if the k % R is 0, R - 0, will be R, an non-zero number
    */
    return ( R - ( abs(key) % R ) );
}

void put_value(int key, int value){
    int i, index;

    for ( i = 0; i < M; i++){
        index = ( h1(key) + i * h2(key) ) % M;
        if(hash_table[index].is_occupied == OCCUPIED){
            if(hash_table[index].key == key){
                printf("Key %d already exists!\nOver-writing it's value!\n", key);
                hash_table[index].value = value;
                return;
            }
        }
        else{
            hash_table[index].key = key;
            hash_table[index].value = value;
            hash_table[index].is_occupied = OCCUPIED;
            return;
        }
    }
    printf("Hash table is full, can not insert new elements !\n");
}

int get_value(int key){
    int i, index;
    for(int i = 0; i < M; ++i){
        index = ( h1(key) + i * h2(key) ) % M;
        if(hash_table[index].is_occupied == NOT_OCCUPIED) break;
        else if(hash_table[index].key == key){
            printf("{key:%d, value:%d}\n", key, hash_table[index].value);
            return hash_table[index].value;
        }
    }
    printf("Key %d does not exists !\n", key);
    return -1;
}

void delete_value(int key){
    int i, index;

    for(i = 0; i < M; ++i){
        index = (h1(key) + i * h2(key)) % M;
        if(hash_table[index].is_occupied == NOT_OCCUPIED) break;
        else if(hash_table[index].key == key){
            printf("Deleted pair : {key:%d, value:%d}\n", key, hash_table[index].value);
            hash_table[index].is_occupied = TOMBSTONE;
            return;
        }
    }
    printf("Key:%d not found !\n", key);
}

void display_hashtable()
{
    printf("\n+-----+-------+\n");
    printf("| Key | Value |\n");
    printf("+-----+-------+\n");

    for(int i = 0; i < M; ++i)
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
        printf("3. Delete an entry.\n");
        printf("4. Display hash table\n");
        printf("5. Exit\n");
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
        case 3 :
            printf("Enter the key : ");
            scanf("%d", &key);
            delete_value(key);
            break;
        case 4:
            display_hashtable();
            break;
        case 5:
            exit(0);
            break;
        default:
            printf("Invalid choice !\n");
        }
    }

    return 0;
}

