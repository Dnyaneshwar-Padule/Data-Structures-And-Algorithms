package com.tca.token;

public final class VariableToken implements Token {

	private final String name;

    public VariableToken(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

}
