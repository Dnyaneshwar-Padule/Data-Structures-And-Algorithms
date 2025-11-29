#include<stdio.h>

#define PLUS -1
#define MINUS -2
#define MULTIPLY -3
#define DIVIDE -4

int calculate(char* str) {
    int s[100000];
    int top = -1, num1, num2, prev = 0, temp = 0;
    char ch;
     
    for(int i = 0; str[i]; ++i){
        ch = str[i];
         
        if(ch == ' ')
            continue;
        else if(ch == '+'){
            s[++top] = PLUS;
        }
        else if( ch == '-'){
            s[++top] = MINUS;
        }
        else if(ch == '*'){
            s[++top] = MULTIPLY;
            prev = 1;
        }
        else if(ch == '/'){
            s[++top] = DIVIDE;
            prev = 1;
        }
        else{
            temp = 0;
            int j;
            for(j = i; str[j]; ++j){
                ch = str[j];
                if(ch == ' ')
                    continue;
                if(ch < '0') break;
                temp = (temp * 10) + (ch - '0');
            }
            s[++top] = temp;
            if(prev){
                prev = 0;
                num1 = s[top--];
                temp = s[top--];
                num2 = s[top--];
                if(temp == DIVIDE)
                    s[++top] = num2/ num1;
                else
                    s[++top] = num2 * num1;

            }
            i = j - 1;
        }
    }

    if(top == 0)
        return s[top];

    for(int i = 0; i < top; ){
        num1 = s[i++];
        temp = s[i++];
        num2 = s[i++];
        
        if(temp == PLUS)
            s[--i] = num1 + num2;
        else
            s[--i] = num1 - num2;
    }
        
    return s[top];
}

int main(){
    char str[] = "1+1-1+1-1+1-2";
    printf("%d\n", calculate(str));
    return 0;
}