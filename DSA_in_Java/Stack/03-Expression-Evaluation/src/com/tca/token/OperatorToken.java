package com.tca.token;

public final class OperatorToken implements Token {

	public enum Associativity {
        LEFT, RIGHT
    }

    private final char symbol;
    private final int precedence;
    private final Associativity associativity;

    public OperatorToken(char symbol, int precedence,
                         Associativity associativity) {
        this.symbol = symbol;
        this.precedence = precedence;
        this.associativity = associativity;
    }

    public char symbol() {
        return symbol;
    }

    public int precedence() {
        return precedence;
    }

    public Associativity associativity() {
        return associativity;
    }

    public double apply(double a, double b) {
        return switch (symbol) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> a / b;
            case '%' -> a % b;
            default -> throw new IllegalStateException("Unknown operator");
        };
    }

}
