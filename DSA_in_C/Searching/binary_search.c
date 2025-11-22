#include<stdio.h>
#include<time.h>
#include<stdlib.h>

void clear_buffer();
int load_array_elements(int*, int);
void display_array(int*, int);
int is_array_sorted(int*, int);
int search(int , int*, int);

int main(){
    srand(time(0));
    
    int *arr = NULL, len = 0, target = 0, target_index = -1;

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

    if(! is_array_sorted(arr, len)){
        printf("Array is not sorted !\n");
        free(arr);
        return 0;
    }

    printf("Array :");
    display_array(arr, len);

    printf("\nEnter the element to search : ");
    if (! scanf("%d", &target)){
        clear_buffer();
        printf("Invalid input !\n");
        free(arr);
        return 0;
    }
    
    target_index = search(target, arr, len);
    
    if (target_index == -1)
        printf("Element not found.\n");
    else
        printf("Element found at index %d.\n", target_index);

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

int search(int target, int* arr, int len){
    int lower_bound = 0, upper_bound = len - 1, mid = 0;

    while (lower_bound <= upper_bound){
        mid = (lower_bound + upper_bound) / 2;

        if (arr[mid] == target)
            return mid;
        else if(arr[mid] < target)
            lower_bound = mid + 1;
        else
            upper_bound = mid;    
    }

    return -1;
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
