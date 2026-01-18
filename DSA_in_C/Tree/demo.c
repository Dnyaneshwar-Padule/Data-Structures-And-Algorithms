#include<stdio.h>
#include<stdlib.h>
#define NEWNODE (struct node*)malloc(sizeof(struct node))

struct node{
    struct node *right;
    int data;
    struct node *left;
};

typedef struct node node;

node *root;

int insert(int data){
    node *new_node = NEWNODE;
    new_node->data = data;
    new_node->right = NULL;
    new_node->left = NULL;
    
    if(!root){
        root = new_node;
        return 1;
    }

    node *cur = root, *prev = NULL;

    while (cur)
    {
        prev = cur;

        if(cur->data > data){
            cur = cur->left;
        }
        else if(cur->data == data){
            free(new_node);
            return 0;
        }
        else{
            cur = cur->right;
        }
    }
    
    if(prev->data > data)
        prev->left = new_node;
    else
        prev->right = new_node;
    
    return 1;
}

void display_inorder(node *root){
    if(root){
        display_inorder(root->left);
        printf("%d, ", root->data);
        display_inorder(root->right);
    }
}

void display_preorder(node *root){
    if(root){
        printf("%d, ", root->data);
        display_preorder(root->left);
        display_preorder(root->right);
    }
}

void display_postorder(node* root){
    if(root){
        display_postorder(root->left);
        display_postorder(root->right);
        printf("%d, ", root->data);
    }
}

int search(int data){
    node *cur = root;

    while(cur){
        if(cur->data == data)
            return 1;
        else if(cur->data > data)
            cur = cur->left;
        else
            cur = cur->right;
    }

    return 0;
}

void free_all(node *root){
    if(root){
        free_all(root->left);
        free_all(root->right);
        free(root);
    }
}

int main()
{
    insert(1);
    insert(3);
    insert(2);
    insert(5);
    insert(4);
    insert(7); 

    display_inorder(root);
    printf("\n");
    display_preorder(root);
    printf("\n");
    display_postorder(root);
    printf("\n");

    printf("\nSearch 1 : %d\n", search(1));
    printf("\nSearch 2 : %d\n", search(2));
    printf("\nSearch 3 : %d\n", search(3));
    printf("\nSearch 4 : %d\n", search(4));
    printf("\nSearch 6 : %d\n", search(6));
    printf("\nSearch 8 : %d\n", search(8));

    free_all(root);
    return 0;
}