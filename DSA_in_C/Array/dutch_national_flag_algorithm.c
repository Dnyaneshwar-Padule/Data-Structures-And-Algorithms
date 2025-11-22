#include<stdio.h>
#include<stdlib.h>
#include<time.h>

void clear_buffer();
void load_array_elements(int*, int);
void display_array(int*, int);
void subarray_with_maximum_sum(int*, int);
void dutch_national_flag_algorithm(int*, int);

int main(){
    srand(time(NULL));
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
    printf("Array :");
    display_array(arr, len);
    dutch_national_flag_algorithm(arr, len);
    printf("Sorted Array : ");
    display_array(arr, len);
    free(arr);
    return 0;
}

void clear_buffer(){
    char ch;
    while ((ch = getchar()) != '\n' && ch != EOF);
}

void load_array_elements(int* arr, int len){
    for (int i = 0; i < len; i++) arr[i] = rand() % 3;
}

void display_array(int* arr, int len){
    printf("[ ");
    for (int i = 0; i < len; i++)
        printf("%3d, ", arr[i]);
    printf("]\n");
}

void dutch_national_flag_algorithm(int* arr, int len){
    int temp,low = 0, i = 0, high = len-1;

    while(i <= high){
        if (arr[i] == 0){
            temp = arr[low];
            arr[low] = arr[i];
            arr[i] = temp;
            low++;
            i++;
        }
        else if(arr[i] == 1){
            i++;
        }
        else if(arr[i] == 2){
            temp = arr[i];
            arr[i] = arr[high];
            arr[high] = temp;
            high--;
        }
    }
}