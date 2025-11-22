#include<stdio.h>
#include<time.h>
#include<stdlib.h>

void clear_buffer();
int load_array_elements(int*, int);
void display_array(int*, int);
int is_array_sorted(int*, int);
void remove_duplicates(int*, int*);

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

    printf("Initial Array  :");
    display_array(arr, len);

    remove_duplicates(arr, &len);
    printf("After removing duplicates :");
    display_array(arr, len);

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

void remove_duplicates(int *arr, int *len){
    int i = 0;
    int cnt = 0;
    for (int x = 1; x < *len; x++){
        if(arr[x] != arr[i]){
            i++;
            arr[i] = arr[x];
        }
        else{
            cnt++;
        }
    }
    *len = *len - cnt;
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
