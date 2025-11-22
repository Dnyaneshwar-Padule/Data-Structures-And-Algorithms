#include<stdio.h>
#include<stdlib.h>

void clear_buffer();
void display_array(int*, int);
int is_array_sorted(int*, int);

int main(){
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

    printf("Enter array elements\n");
    for (int i = 0; i < len; i++){
        printf("Element no.%d : ", i+1);
        if ( ! scanf("%d", &arr[i]) ){
            printf("Invalid input !\n");
            clear_buffer();
            free(arr);
            return 0;
        }
    }
    
    printf("Your array :");
    display_array(arr, len);
    printf("%s\n", is_array_sorted(arr, len) ? "Array is ascendingly sorted\n" : "Array is not ascendingly sorted\n" );
    
    free(arr);
    return 0;
}

void clear_buffer(){
    char ch;
    while ((ch = getchar()) != '\n' && ch != EOF);
}

void display_array(int* arr, int len){
    printf("[ ");
    for (int i = 0; i < len; i++)
        printf("%3d, ", arr[i]);
    printf("]\n");
}

int is_array_sorted(int* arr, int len){
    char flag = 1;
    for (int i = 0; i < (len - 1); i++){
        if (arr[i] > arr[i+1]){
            flag = 0;
            break; 
        }
    }
    return (int) flag;
}