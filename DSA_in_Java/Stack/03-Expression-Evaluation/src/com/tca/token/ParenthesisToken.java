package com.tca.token;

public final class ParenthesisToken implements Token {
	public enum Type { LEFT, RIGHT }

	private final Type type;

	public ParenthesisToken(Type type) {
		this.type = type;
	}

	public boolean isLeft() {
		return type == Type.LEFT;
	}

	public boolean isRight() {
		return type == Type.RIGHT;
	}

}
