#include<stdio.h>
#include<stdlib.h>


struct node{
    struct node *left;
    int data;
    struct node *right;
};

typedef struct {
    struct node *root;
    int k;
    struct node *smallest;
    int size;
} KthLargest;

typedef struct node Node;

Node* smallestValueNode(Node* root){
    if(! root->left) return root;
    return smallestValueNode(root->left);
}

void insertNode(KthLargest *obj, int value ){
    if(obj->smallest && obj->smallest->data >= value && obj->size == obj->k){
        printf("Returning !\n");
        return;
    }
    struct node *current = obj->root, *previous = NULL;
    Node* new_node = NULL;

    if(obj->size < obj->k){
        new_node = (Node*)malloc(sizeof(Node));
        new_node->data = value;
        new_node->right = NULL;
        new_node->left = NULL;
        
        while(current){
            previous = current;
            if(current->data > value)
                current = current->left;
            else
                current = current->right;
        }
      
        printf("New Node data : %d\n", new_node->data);
        
            // root node
        if( ! previous ){
            obj->root = new_node;
            obj->smallest = new_node;
            obj->size++;
            printf("Smallest : %d\n", obj->smallest->data);
            return;
        }

        
        if( value < previous->data)
            previous->left = new_node;
        else
            previous->right = new_node;
    
        obj->size++;    
        obj->smallest = smallestValueNode(obj->root);
        printf("Smallest : %d\n", obj->smallest->data);
        return;
    }
    else{
        printf("Tree Nodes are equal to K\n");
        while(current){
            previous = current;
            if(current->data < value ){
                current  = current->right;

                if(! current && previous->left){
                    current = previous->left;
                    current->data = previous->data;
                }
                else if(current) previous->data = current->data; 
            }
            else{
                break;
            }
        }

        if(! previous){
            current->data = value;
            return;
        }

        previous->data = value;
        printf("Smallest updated : %d\n", obj->smallest->data);
    }   
}



int kthLargestAdd(KthLargest* obj, int val) {
    printf("Insert val %d\n", val);
    insertNode(obj, val);
    return obj->smallest->data;
}

KthLargest* kthLargestCreate(int k, int* nums, int numsSize) {
    KthLargest* K = (KthLargest*)malloc(sizeof(KthLargest));
    K->root = NULL;
    K->size = 0;
    K->smallest = NULL;
    K->k = k;
    for(int i = 0; i < numsSize; ++i )
        insertNode(K, nums[i]);
    return K;
}




void freeNodes(Node *root){
    if( ! root )return;
    freeNodes(root->right);
    freeNodes(root->left);
    free(root);
}

void kthLargestFree(KthLargest* obj) {
    freeNodes(obj->root);
    free(obj);
}

void traverse(Node* root){
    if (! root ) return;
    traverse(root->right);
    printf("%d\n", root->data);
    traverse(root->left);
}

int main(){
    
    // int nums[] = {-2};
    // int nums[] = {7,7,7,7,8,3};
    // int nums[] = {4,5,8,2};
    int nums[] = {0};
    KthLargest *obj = kthLargestCreate(2, &nums, 1);
    
    printf("%d\n", kthLargestAdd(obj,-1));
    printf("%d\n", kthLargestAdd(obj,1));
    printf("%d\n", kthLargestAdd(obj,-2));
    printf("%d\n", kthLargestAdd(obj,-4));
    printf("%d\n", kthLargestAdd(obj,3));

    kthLargestFree(obj);
    return 0;
}

/**
 * Your KthLargest struct will be instantiated and called as such:
 * KthLargest* obj = kthLargestCreate(k, nums, numsSize);
 * int param_1 = kthLargestAdd(obj, val);
 
 * kthLargestFree(obj);
 */
/*
int findKthLargest(KthLargest *obj, Node* root, int i){
    if (! root ) return i;
    i = findKthLargest(obj, root->right, i) + 1;
    
    printf("Root -> %d, it's left val %d\n",root->data, i);
    
    if( i == obj->k && ! obj->found){
        printf("found = %d\n", root->data);
        obj->kthLargest = root->data;
        obj->found = 1;
        return obj->k + 1;
    }
    
    if(root->left)
    i = findKthLargest(obj, root->left, i);
    return i;
}
*/