#include<stdio.h>
#define NO_REPEATED_CHAR -1

char get_first_repeated_char(char *str){
    
    /*
    // Worst approach
    // Time Complexity : O(n^2)
    // In place
    for(int i = 0; str[i]; i++){
        for (int j = i + 1; str[j] ; j++)
        {
            if(str[i] == str[j]){
                return str[i];
            }
        }
    }
    */

    // Best Approach
    int count[128];

    for(int i = 0; i < 128; i++)
        count[i] = 0;

    for(int i = 0; str[i]; i++){
        if (count[str[i]] == 1)
        {
            return str[i];
        }
        count[str[i]]++;
    }

    return NO_REPEATED_CHAR;
}

int main()
{
    char buffer[64]; // will store the input string 

    printf("Enter the String : ");
    fgets(buffer, sizeof(buffer), stdin);

    char repeated_char = get_first_repeated_char(buffer);

    if (repeated_char == NO_REPEATED_CHAR){
        printf("No repeated character !\n");
    }
    else{
        printf("First Repeated Char : %c\n", repeated_char);
    }

    return 0;
}
