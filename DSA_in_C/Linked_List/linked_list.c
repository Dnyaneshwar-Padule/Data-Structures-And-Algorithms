#include<stdio.h>
#include<stdlib.h>

#define NEWNODE (struct node*)malloc(sizeof(struct node))

struct node 
{
    int data;
    struct node* next;
};

typedef struct node Node;

Node* head;

// Initialize the linked list
void init(){
    head = NULL;
}

//Insert a new node into the linked list
Node* insert(Node* head)
{
    int data=0;

    printf("Enter the data : ");
    scanf("%d", &data);

    Node* new_node = NEWNODE;
    new_node->data = data;
    new_node->next = NULL;

    if(! head){
        return new_node;
    }
    else
    {
        Node* temp = head;
        while( temp->next != NULL) temp = temp->next;
        temp->next = new_node;
        return head;
    }

}

//Display the linked list
void display(Node* head)
{
    Node* t = head;

    while(t){
        printf("%d --> ", t->data);
        t = t->next;
    }

    printf("NULL\n");
}

//Delete a node from the linked list
Node* delete(Node* head, unsigned int idx)
{
    if(idx < 1)
    {
        printf("Invalid index !\n");
        return head;
    }

    if(! head)
    {
        printf("Linked list is empty !\n");
        return head;
    }

    Node* t = head;
    int i = 2;

    // deleting first node, head will update
    if(idx == 1)
    {
        head = head->next;
        free(t);
        return head;
    }

    while( t->next && i < idx){
        t = t->next;
        i++;
    }

    if(!t->next){
        printf("Invalid Index !\n");
        return head;
    }
    else if( i == idx){
        Node *node_to_delete = t->next;
        Node* next_node = node_to_delete->next;
        t->next = next_node;
        free(node_to_delete);
        return head; 
    }
}

//Returns the index of node with target data
int search(Node* head, int target)
{
    if(! head){
        printf("Linked list is empty !\n");
        return -1;
    }

    Node* traverser = head;
    int index = 1;
    
    while(traverser && traverser->data != target){
        traverser = traverser->next;
        index++;
    }

    if(!traverser){
        printf("Data not found !\n");
        return -1;
    }

    return index;
}

//Updates data of a node
Node* update(Node* head, unsigned int index, int new_data)
{
    if (index < 1)
    {
        printf("Invalid index !\n");
        return head;
    }   
    
    if(! head) return head;

    Node* traverser = head;
    int i = 1;

    while( traverser && i < index){
        traverser = traverser->next;
        i++;
    }

    if(!traverser){
        printf("Invalid Index !\n");
        return head;
    }

    traverser->data = new_data;
    return head;
}

// deletes the first node
Node* del_first(Node *head)
{
    if(!head) return head;
    Node *t = head;
    head = head->next;
    free(t);
    return head;
}

//deletes the last node in the linked list
Node* del_last(Node* head)
{
    if(!head) return head;
    if(!head->next) return del_first(head);

    Node *t = head, *p = NULL;

    while(t->next){
        p = t;
        t = t->next;
    }

    p->next = NULL;
    free(t);
    return head;
}

//Insert a node at first pos
Node* add_first(Node* head, int data)
{
    Node* new_node = NEWNODE;
    new_node->data = data;
    new_node->next = head;
    head = new_node;
    return head;
}

//Insert a node at last pos
Node* add_last(Node* head, int data)
{
    Node *traverser = head;
    Node *new_node = NEWNODE; 
    
    new_node->data = data;
    new_node->next = NULL;

    if(!head) 
        return new_node;

    while(traverser->next) 
        traverser = traverser->next;

    traverser->next = new_node;
    return head;
}

//add a node at specific index
Node* insert_at(Node* head, int data, unsigned int index)
{
    if(index < 1)
    {
        printf("Invalid Index !\n");
        return head;
    }

    if(index == 1) 
        return add_first(head, data);

    Node* traverser = head;
    Node* new_node = NEWNODE;
    int pos = 2;

    new_node->data = data;
    new_node->next = NULL;

    while (traverser && traverser->next && pos < index)
    {
        pos++;
        traverser = traverser->next;
    }

    if(!traverser || pos != index)
    {
        printf("Invalid position !");
        free(new_node);
        return head;
    }

    new_node->next = traverser->next;
    traverser->next = new_node;
    return head; 
}

//attaches a linked list to a Linked list at specific pos 
Node* attach(Node* head, Node *sub_head, unsigned int index)
{
    //Negative or 0 index is not allowed
    if(index < 1)
    {
        printf("Invalid index\n");
        return head;
    }

    if(!head) return sub_head; // if head is empty return sub_head
    if(!sub_head) return head; // if sub_head is empty return head

    Node* head_traverser = head;  // to traverse head nodes
    Node* sub_head_traverser = sub_head; // to traverse sub head nodes
    
    //if index is 1, attach head to sub_head's last node, and return sub_head
    if(index == 1)
    {
        while (sub_head_traverser->next)
        sub_head_traverser = sub_head_traverser->next;
        
        sub_head_traverser->next = head;
        return sub_head; 
    }
    else
    {
        int pos = 2; // bcax, we have to traverse just before the targer index

        //traverse through head_nodes
        while ( head_traverser && head_traverser->next && pos < index )
        {
            pos++;
            head_traverser = head_traverser->next;
        }

        // if traverser becomes null, then it's invalid index
        if(! head_traverser)
        {
            printf("Invalid index !\n");
            return head;
        }
        else if(!head_traverser->next) // if travserser's next is null, it means we are on the last node, then just attach the sub_head and return head
        {
            head_traverser->next = sub_head;
            return head;
        }
        else
        {
            Node* remaining_head_nodes = head_traverser->next; 

            while (sub_head_traverser->next)
                sub_head_traverser = sub_head_traverser->next;
                
            sub_head_traverser->next = remaining_head_nodes;
            head_traverser->next = sub_head;
            return head;
        }
    }
}

//Linked list length
unsigned int llength(Node* head)
{
    int len = 0;
    Node *t = head;

    while(t)
    {
        len++;
        t=t->next;
    }
    return len;
}

// free all nodes
Node* free_all(Node* head){
    Node* prev = head;
    
    while(!head){
        prev = head;
        head = head->next;
        free(prev);
    }

    return NULL;
}

// Delete all nodes from a index
Node* delete_all_from(Node* head, unsigned int index)
{
    if( index < 1 )
    {
        printf("Invalid index !\n");
        return head;
    }
    
    if(index == 1)
        return free_all(head);

    Node* t = head, *p = NULL;
    int pos = 1;

    while(t && t->next && pos < index)
    {
        pos ++;
        p = t;
        t = t->next;
    }

    if(!t || pos != index)
    {
        printf("Invalid from index !\n");
        return head;
    }

    p->next = NULL;
    free_all(t);
    return head;
}

// Delete all nodes from a index to a index
Node* delete_all_from_to(Node* head, unsigned int from, unsigned int to)
{
    if( !head ) return head;

    if(from == to)
    {
        return delete(head, from);   
    }

    if( from > to)
    {
        printf("From index should be smaller than to index !\n");
        return head;
    }

    Node* s = head, *f = NULL, *t = NULL, *p = NULL;
    int pos = 1;

    while( s &&  pos <= to)
    {
        if(pos <= from){
            p = f;
            f = s;
        }

        if(pos <= to)
            t = s;

        pos++;
        s = s->next;
    }

    if(!s && pos <= to)
    {
        printf("Invalid to index !\n");
        return head;
    }

    if(from == 1)
    {
        head = t->next; // Update head to next of to
        t->next = NULL;  // make to's next NULL
        free_all(f);  //  Free all nodes of from 
        return head;
    }
    else
    {
        p->next = t->next;
        t->next = NULL;
        free_all(f);
        return head;
    }

}

//Reverse linked list
Node* reverse(Node* head)
{
    if(!head || !head->next) return head;

    Node *next = head, *prev = NULL, *t = NULL;

    while(next)
    {
        t = next->next;
        next->next = prev;
        prev = next;
        next = t;
    }

    return prev;
}

int main()
{
    init();

    head = insert(head);
    head = insert(head);
    head = insert(head);
    head = insert(head);
    head = insert(head);
    
    
    printf("Linked list : ");
    display(head);
    
    head = reverse(head);
    
    printf("Linked list : ");
    display(head);
    
    free_all(head);
    return 0;
}