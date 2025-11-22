#include<stdio.h>
#define LENGTH 20

typedef struct 
{
    int ID;
    int salary;
}Employee;

Employee employees[LENGTH];
int n = 0;

void insert()
{
    if(n == LENGTH)
    {
        printf("Array is full, cannot insert !\n");
        return;
    }

    printf("Enter the ID : ");
    scanf("%d", &employees[n].ID);

    printf("Enter the salary : ");
    scanf("%d", &employees[n].salary);

    n++;
}

void display()
{
    for (int i = 0; i < n; i++)
        printf("{[%4d], [%4d]}\n", employees[i].ID, employees[i].salary);
}

void sort()
{
    for(int i = 0; i < n; i++){
        for(int j = i + 1; j < n; j++){
            if(employees[i].salary > employees[j].salary){
                Employee e = employees[i];
                employees[i] = employees[j];
                employees[j] = e;
            }
        }
    }
}

int main()
{
    insert();
    insert();
    insert();
    insert();
    insert();

    display();
    sort();
    printf("\n");
    display();
    return 0;
}