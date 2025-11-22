#include<stdio.h>
#include<time.h>
#include<stdlib.h>

void clear_buffer();
void load_array_elements(int*, int);
void display_array(int*, int);
void insert(int*, int* , int, int);

int main(){
    srand(time(0));
    
    int *arr = NULL, len = 0, index = 0, element = 0;

    printf("How many elements : ");
    if (! scanf("%d", &len)){
        printf("Invalid input !\n");
        clear_buffer();
        return 0;
    }

    if (len < 1){
        printf("Atleast one element is required.\n");
        return 0;
    }
    

    arr = (int*) malloc(sizeof(int) * len + 1);
    if(!arr){
        printf("Unable to allocate array !\n");
        return 1;
    }

    load_array_elements(arr, len);
    printf("Array  :");
    display_array(arr, len);
    
    printf("Enter the value of element : ");
    if ( ! scanf("%d", &element)){
        clear_buffer();
        printf("Invalid input !\n");
        return 0;
    }
    
    printf("Enter the index : ");
    if (! scanf("%d", &index)){
        clear_buffer();
        printf("Invalid input !\n");
        return 0;
    }

    insert(arr, &len, element, index);
    printf("Array  :");
    display_array(arr, len);
    
    free(arr);
    return 0;
}

void clear_buffer(){
    char ch;
    while ((ch = getchar()) != '\n' && ch != EOF);
}

void load_array_elements(int* arr, int len){
    for (int i = 0; i < len; i++)
        arr[i] = rand() % 250;
}

void display_array(int* arr, int len){
    printf("[ ");
    for (int i = 0; i < len; i++)
        printf("%3d, ", arr[i]);
    printf("]\n");
}

void insert(int *arr, int *len, int element, int index ){
    if(index < 0 || index > *len){
        printf("Invalid index.\n");
        return;
    }

    int i = *len;
    while ( i > index){
        arr[i] = arr[i - 1];
        i--;
    }
    arr[i] = element;
    *len = *len + 1;
}