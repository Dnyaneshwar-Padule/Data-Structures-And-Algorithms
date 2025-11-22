#include<stdio.h>
#include<stdlib.h>

void clear_buffer();
int load_array_elements(int*, int);
void display_array(int*, int);
void majority_element(int*, int);

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

    if( ! load_array_elements(arr, len)){
        clear_buffer();
        printf("Invalid input\n");
        free(arr);
        return 0;
    }

    printf("Array  :");
    display_array(arr, len);
    majority_element(arr, len);
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

void majority_element(int *arr, int len){
    int element = 0;
    int count = 0;

    for (int i = 0; i < len; i++){
        if (count == 0){
            element = arr[i];
            count = 1;
        }
        else if (arr[i] == element)
            count++;
        else
            count--;
    }

    for (int i = 0; i < len; i++){
        if (arr[i] == element){
            count++;
        }
    }
    
    if (count > len / 2){
        printf("%d is majority element\n", element);
    }
    else{
        printf("No majority element\n");
    }

}
