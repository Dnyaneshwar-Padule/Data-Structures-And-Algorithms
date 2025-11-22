#include<stdio.h>
#include<stdlib.h>
#include<limits.h>

void clear_buffer();
int load_array_elements(int*, int);
void display_array(int*, int);
int search(int, int*, int);

int main(){
    int *arr = NULL, len = 0, key = 0, key_index = 0;

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

    if( ! load_array_elements(arr, len)){
        clear_buffer();
        printf("Invalid input\n");
        free(arr);
        return 0;
    }

    printf("Array  :");
    display_array(arr, len);

    printf("Enter the key to search : ");
    if ( ! scanf("%d", &key)){
        clear_buffer();
        printf("Invalid input !\n");
        free(arr);
        return 0;
    }
    
    key_index = search(key, arr, len);

    if (key_index == -1)
        printf("Key is not found in the array.\n");
    else
        printf("Key is found at index %d\n", key_index);

    free(arr);
    return 0;
}

void clear_buffer(){
    char ch;
    while ((ch = getchar()) != '\n' && ch != EOF);
}

int load_array_elements(int* arr, int len){
    for (int i = 0; i < len; i++){
        printf("Enter the element no. %d: ", i+1);
        if(!scanf("%d", &arr[i]))
            return 0;
    }
    return 1;
}

void display_array(int* arr, int len){
    printf("[ ");
    for (int i = 0; i < len; i++)
        printf("%3d, ", arr[i]);
    printf("]\n");
}

int search(int key, int* arr, int len){
    for (int i = 0; i < len; i++)
        if(arr[i] == key)
            return i;
    return -1;
}