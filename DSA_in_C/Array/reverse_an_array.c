#include<stdio.h>
#include<time.h>
#include<stdlib.h>

void clear_buffer();
void load_array_elements(int*, int);
void display_array(int*, int);
void reverse_array(int*, int);

int main(){
    srand(time(0));
    
    int *arr = NULL, len = 0;

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
    

    arr = (int*) malloc(sizeof(int) * len);
    if(!arr){
        printf("Unable to allocate array !\n");
        return 1;
    }

    load_array_elements(arr, len);
    printf("Initial Array  :");
    display_array(arr, len);
    reverse_array(arr, len);
    printf("Reversed Array :");
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

void reverse_array(int* arr, int len){
    int right = 0, temp;
    len -= 1;
    while (right < len){
        temp = arr[right];
        arr[right] = arr[len];
        arr[len] = temp;
        len--;
        right++; 
    }
}