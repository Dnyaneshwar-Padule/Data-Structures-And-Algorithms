#include<stdio.h>
#include<stdlib.h>

int top = -1;
int n = 0;
int *stack;

void init(int size){
    stack = (int*)calloc(size, sizeof(int));
}