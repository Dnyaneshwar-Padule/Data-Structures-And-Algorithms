#include<stdio.h>
#include<stdlib.h>
#include<string.h>

typedef struct{
    Node* next;
    char *url;
    Node* prev;
}Node;

typedef struct {
    Node* current;
} BrowserHistory;

void freeNextNodes(Node* node){
    Node* current;
    while(node){
        current = node;
        node = node->next;
        free(current);
    }
}

BrowserHistory* browserHistoryCreate(char* homepage) {
    BrowserHistory* browserHistory = (BrowserHistory*)malloc(sizeof(browserHistory));
    browserHistory->current = (Node*)malloc(sizeof(Node));
    browserHistory->current->url = homepage;
    browserHistory->current->next = NULL;
    browserHistory->current->prev = NULL;
    return browserHistory;
}

void browserHistoryVisit(BrowserHistory* obj, char* url) {
    Node* node  = (Node*)malloc(sizeof(Node));
    node->url = url;
    node->next = NULL;
    node->prev = obj->current;
    if(obj->current){
        freeNextNodes(obj->current->next);
        obj->current->next = node;
    }
    obj->current = node;
}

char* browserHistoryBack(BrowserHistory* obj, int steps) {
    if(!  obj->current || ! obj->current->prev) return NULL;
    while(steps > 0){
        obj->current = obj->current->prev;
        steps--;
    }
    return obj->current->url;
}

char* browserHistoryForward(BrowserHistory* obj, int steps) {
    if( ! obj->current || ! obj->current->next) return NULL;
    while(steps > 0){
        obj->current = obj->current->next;
        steps--;
    }
}

void browserHistoryFree(BrowserHistory* obj) {
    if(! obj || ! obj->current) return;
    freeNextNodes(obj->current->next);
    Node* current;
    while(obj->current){
        current = obj->current;
        obj->current = current->prev;
        free(current);
    }
    free(obj);
}
