#include<stdio.h>
#include<string.h>

#define PLUS 1
#define MINUS 2
#define DIVIDE 3
#define MULTIPLY 4
#define LEFT_BRACKET 5


int is_digit(char ch){
    return (ch >= '0' && ch <= '9');
}

int operate(int left, int operator, int right){
    printf("%d - %d - %d\n", left,operator, right);
    switch (operator)
    {
    case PLUS:
        return left + right;
    case MINUS:
        return left - right;
    case MULTIPLY:
        return left * right;
    case DIVIDE:
        return left / right;
    }
}

int get_operator(char ch){
    switch (ch)
    {
    case '+':
        return PLUS;
    case '-':
        return MINUS;
    case '*':
        return MULTIPLY;
    case '/':
        return DIVIDE;
    }
}

int main()
{
    char *expr = "(2345 - (123 + 456)) * ((500 - 450) / 23)";
    int operands[strlen(expr)]; // operand stack
    int operators[strlen(expr)]; // operator stack
    int a = -1, b = -1;

    for(int i = 0; expr[i]; ++i){
        char ch = expr[i];

        if(ch == ' ')
            continue;

        if(is_digit(ch)){
            int j, num = 0;
            for(j = i; expr[j]; ++j){
                char c = expr[j];
                if(! is_digit(c) )
                    break;
                num = (num * 10) + (c - '0');
            }

            operands[++a] = num;
            i = j-1;
        }
        else if(ch == ')'){     
            int operator = 0;
            while(b >= 0 && (operator = operators[b--]) != LEFT_BRACKET ){
                int right_operand = operands[a--];
                int left_operand = operands[a--];
                operands[++a] = operate(left_operand, operator, right_operand);
            }
        }
        else if( ch == '('){
            operators[++b] = LEFT_BRACKET;
        }
        else{
            int operator = get_operator(ch);
            while (b >= 0 && operators[b] != LEFT_BRACKET){
                if(operators[b] == MULTIPLY || operators[b] == DIVIDE){
                    int right_operand = operands[a--];
                    int left_operand = operands[a--];
                    operands[++a] = operate(left_operand, operators[b--], right_operand);
                    continue;
                }

                if(operator == PLUS || operator == MINUS){
                    if(operators[b] == PLUS || operators[b] == MINUS){
                        int right_operand = operands[a--];
                        int left_operand = operands[a--];
                        operands[++a] = operate(left_operand, operators[b--], right_operand);
                    }
                }
            }
            operators[++b] = operator;
        }

    }

    if(b >= 0){
        int right_operand = operands[a--];
        int left_operand = operands[a--];
        operands[++a] = operate(left_operand, operators[b--], right_operand); 
    }

    printf("Result : %d\n", operands[a]);
    return 0;
}

