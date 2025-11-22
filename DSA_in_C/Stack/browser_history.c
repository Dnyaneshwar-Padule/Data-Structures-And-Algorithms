#include <stdio.h>
#include <string.h>

#define MAX 50        // Maximum number of pages in each stack
#define URL_LEN 100   // Maximum length of each URL

char backStack[MAX][URL_LEN];
char forwardStack[MAX][URL_LEN];
char current[URL_LEN] = "Home";   // Default starting page
int backTop = -1, forwardTop = -1;

// Function to visit a new page
void visitPage(char *url) {
    if (strlen(current) > 0)
        strcpy(backStack[++backTop], current);   // Push current page to Back Stack
    strcpy(current, url);                        // Set new page as current
    forwardTop = -1;                             // Clear Forward Stack
    printf("\nVisited: %s\n", current);
}

// Function to go back to previous page
void goBack() {
    if (backTop == -1) {
        printf("\nNo pages in history.\n");
        return;
    }
    strcpy(forwardStack[++forwardTop], current); // Push current page to Forward Stack
    strcpy(current, backStack[backTop--]);       // Pop from Back Stack
    printf("\nWent back to: %s\n", current);
}

// Function to go forward to next page
void goForward() {
    if (forwardTop == -1) {
        printf("\nNo forward pages available.\n");
        return;
    }
    strcpy(backStack[++backTop], current);       // Push current page to Back Stack
    strcpy(current, forwardStack[forwardTop--]); // Pop from Forward Stack
    printf("\nWent forward to: %s\n", current);
}

// Function to display browsing history
void displayStatus() {
    printf("\n==============================\n");
    printf("Current Page: %s\n", current);

    printf("\nBack Stack: ");
    if (backTop == -1)
        printf("Empty");
    else
        for (int i = backTop; i >= 0; i--)
            printf("%s ", backStack[i]);

    printf("\nForward Stack: ");
    if (forwardTop == -1)
        printf("Empty");
    else
        for (int i = forwardTop; i >= 0; i--)
            printf("%s ", forwardStack[i]);

   printf("\n------------------------------\n");
   for(int i = 0; i <= backTop; i++)
        printf("%s <-- ", backStack[i]);
    
    printf(" %s ", current);
    for(int i = forwardTop; i >= 0; i--)
        printf(" --> %s ", forwardStack[i]);

    printf("\n==============================\n");
}

// Main function
int main() {
    int choice;
    char url[URL_LEN];

    while (1) {
        printf("\n1. Visit Page");
        printf("\n2. Back");
        printf("\n3. Forward");
        printf("\n4. Display");
        printf("\n5. Exit");
        printf("\nEnter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                printf("Enter URL: ");
                scanf("%s", url);
                visitPage(url);
                break;
            case 2:
                goBack();
                break;
            case 3:
                goForward();
                break;
            case 4:
                displayStatus();
                break;
            case 5:
                printf("\nExiting Browser History Simulation...\n");
                return 0;
            default:
                printf("\nInvalid choice. Please try again.\n");
        }
    }
}
