/*

    This hashing method, does not cause clustering, 
    because, if we have multiple keys which are causing same hash index, 
    then we will store them in a linked list which will be at that index
    
    i.e. we will use an array of linked list (head), every array index will point to a
    separate linked list, and we will store keys with same hash in respective linked list

    It will look like this

    +----+    +-----+-------+--+   +-----+-------+--+   +-----+-------+--+
  0 |head|--> | Key | Value |->|   | Key | Value |->|   | Key | Value |->| NULL
    +----+    +-----+-------+--+   +-----+-------+--+   +-----+-------+--+
    +----+    +-----+-------+--+
  1 |head|--> | Key | Value |->|  NULL
    +----+    +-----+-------+--+
    +----+    +-----+-------+--+   +-----+-------+--+
  2 |head|--> | Key | Value |->|   | Key | Value |->| NULL
    +----+    +-----+-------+--+   +-----+-------+--+
    +----+
  3 |head|--> NULL
    +----+    
  Hash Table of size 4, and every index is a linked list head

*/
#include<stdio.h>
#include<stdlib.h>
#define NEWNODE (struct node*)malloc(sizeof(struct node))
#define LENGTH 17

struct node
{
    int key;
    int value;
    struct node* next;
};
typedef struct node Node;

Node* hash_table[LENGTH];

//empty hash_table
void init(){
    for(int i = 0; i < LENGTH; ++i)
        hash_table[i] = NULL;
}

void put_value(int key, int value){
    int index = abs(key) % LENGTH;
    Node* traverser = hash_table[index];
    Node* prev = NULL;

    //No linked list
    if(traverser == NULL){
        Node* new_entry = NEWNODE;
    
        //Unable to allocate memory for new node
        if(!new_entry){
            printf("Unable to allocate memory for new node !\n");
            return;
        }

        new_entry->key = key;
        new_entry->value = value;
        new_entry->next = NULL;
        hash_table[index] = new_entry;
    }
    else{
        while(traverser){
            if(traverser->key == key){
                printf("Key:%d already exists!\nOver-writing value!\n",key);
                traverser->value = value;
                return;
            }
            prev = traverser;
            traverser = traverser->next;
        }

        Node* new_entry = NEWNODE;
        if(!new_entry){
            printf("Unable to allocate memory for new node !\n");
            return;
        }

        new_entry->key = key;
        new_entry->value = value;
        new_entry->next = NULL;
        prev->next = new_entry;
    }
}

// I thought, I can set return type to int, 
//if found return the value, else return -1
//But -1 itself can be a value
int* get_value(int key){
    int index = abs(key) % LENGTH;
    Node* traverser = hash_table[index];

    while (traverser){
        if(traverser->key == key)
            return &traverser->value;
        traverser = traverser->next;
    }
    printf("Key:%d does not exists !\n", key);
    return NULL;
}

void delete_value(int key){
    int index = abs(key) % LENGTH;
    Node* traverser = hash_table[index];
    Node *temp = NULL, *previous = NULL;

    if(traverser){
        //delete first node, and update head
        if(traverser->key == key){
            temp = traverser->next;
            traverser->next = NULL;
            printf("Removing entry : {key:%d, value:%d}\n", traverser->key, traverser->value);
            free(traverser);
            hash_table[index] = temp;
            return;
        }
        else{
            //traverse and delete the node
            while(traverser && traverser->key != key){
                previous = traverser;
                traverser = traverser->next;
            }

            if(traverser){
                temp = traverser->next;
                traverser->next = NULL; 
                printf("Removing entry : {key:%d, value:%d}\n", traverser->key, traverser->value);
                free(traverser);
                previous->next = temp; // Joining remaining linked list 
                return;
            }
        }
    }
    printf("Key:%d does not exists !\n", key);
}

void display_hashtable()
{
    Node *traverser = NULL;
    printf("\n+-----+-------+\n");
    printf("| Key | Value |\n");
    printf("+-----+-------+\n");

    for(int i = 0; i < LENGTH; ++i)
    {
        traverser = hash_table[i];
        while (traverser){
            printf("| %3d | %5d |\n", traverser->key, traverser->value);
            traverser = traverser->next;
        }
    }
    printf("+-----+-------+\n");
}

void dispose_hash_table()
{
    Node *c = NULL, *p = NULL;
    for(int i = 0; i < LENGTH; ++i)
    {
        c = hash_table[i];
        while (c){
            p = c;
            c = c->next;
            free(p);
        }
        hash_table[i] = NULL;
    }
    printf("Hash table memory released !\n");
}


int main()
{
    init();
    int choice,key,value;
    int *val;

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
            val = get_value(key);
            if(val)
                printf("{key:%d, value:%d}\n", key, *val);
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
            dispose_hash_table();
            exit(0);
            break;
        default:
            printf("Invalid choice !\n");
        }
    }
    return 0;
}

