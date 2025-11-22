#include<stdio.h>
#include<time.h>
#include<stdlib.h>

void clear_buffer();
void load_array_elements(int*, int);
void display_array(int*, int);
int find_second_largest_element(int*, int);

int main(){
    srand(time(0));
    
    int *arr = NULL, len = 0;

    printf("How many elements : ");
    if (! scanf("%d", &len)){
        printf("Invalid input !\n");
        clear_buffer();
        return 0;
    }

    if (len < 2){
        printf("Atleast two elements are required.\n");
        return 0;
    }

    arr = (int*) malloc(sizeof(int) * len);
    if(!arr){
        printf("Unable to allocate array !\n");
        return 1;
    }

    load_array_elements(arr, len);
    printf("Array is created :");
    display_array(arr, len);
    printf("Second Largest element : %d\n", find_second_largest_element(arr, len));
    
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

int find_second_largest_element(int* arr, int len){
    int largest, second_largest,  i = 0;

    for(i = 0; i < len - 1; i++){
        if(arr[i] > arr[i + 1]){
            largest = arr[i];
            second_largest = arr[i +  1];
            break;
        }
        else if ( arr[i] == arr[i + 1]){
            largest = arr[i];
            second_largest = arr[i];
        }
        else{
            largest = arr[i + 1];
            second_largest = arr[i];
        }
    }
    

    for (; i < len; i++)
        if (arr[i] > largest){
            second_largest = largest;
            largest = arr[i];
        }
        else if(arr[i] > second_largest && arr[i] != largest)
            second_largest = arr[i];

    return second_largest;
}