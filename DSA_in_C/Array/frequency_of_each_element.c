#include<stdio.h>
#include<time.h>
#include<stdlib.h>

void clear_buffer();
int load_array_elements(int*, int);
void display_array(int*, int);
void print_frequency_of_each_element(int*, int);

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

    printf("Array  :");
    display_array(arr, len);

    printf("\nFrequency of each element\n");
    print_frequency_of_each_element(arr, len);

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

void print_frequency_of_each_element(int *arr, int len){
    int visited[len];

    for (int i = 0; i < len; i++)
        visited[i] = -1;
    
    for (int  i = 0; i < len; i++){
        if (visited[i] == 0)
            continue;
        
        int count = 1;
        for (int j = i + 1 ; j < len; j++){
            if (arr[i] == arr[j]){
                count++;
                visited[j] = 0;
            }
        }
        printf("%d appeared %d times\n", arr[i], count);
    }
}
