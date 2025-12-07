package com.tca.token;

public final class NumberToken implements Token{

	private final double value;

    public NumberToken(double value) {
        this.value = value;
    }

    public double value() {
        return value;
    }

}
