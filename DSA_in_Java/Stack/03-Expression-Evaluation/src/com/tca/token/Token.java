package com.tca.token;

public sealed interface Token  
	permits NumberToken, OperatorToken, VariableToken, ParenthesisToken {

}
