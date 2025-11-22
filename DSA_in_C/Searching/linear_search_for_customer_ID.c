#include<stdio.h>
#define LENGTH 20

typedef struct 
{
    int ID;
    char name[16];
}Customer;

Customer customers[LENGTH];
int n = 0;

void insert()
{
    if( n == 20)
    {
        printf("Array is full, cannot insert !\n");
        return;
    }

    printf("Enter the Customer name : ");
    scanf("%s", customers[n].name);

    printf("Enter the customer ID : ");
    scanf("%d", &customers[n].ID);

    n++;
}

int search(int target_ID)
{
    for(int i = 0; i < n; ++i)
    {
        if(customers[i].ID == target_ID)
            return i;
    }

    return -1;
}


void display()
{
    for(int i = 0; i < n; i++)
        printf("[%5d], [%16s]\n", customers[i].ID, customers[i].name);
}

int main()
{
    insert();
    insert();
    insert();
    insert();

    display();

    printf("Search for Customer ID 3 : %d\n", search(3));
    printf("Search for Customer ID 4 : %d\n", search(4));
    printf("Search for Customer ID 5 : %d\n", search(5));
    printf("Search for Customer ID 6 : %d\n", search(6));
    printf("Search for Customer ID 10 : %d\n", search(10));

    return 0;
}
