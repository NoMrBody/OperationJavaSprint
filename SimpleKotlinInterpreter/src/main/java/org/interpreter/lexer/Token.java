package org.interpreter.lexer;

// simple anatomy of token
public class Token {
    public final TokenType type;
    public final String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("[<%s>, %s]", type, value);
    }

    public TokenType getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }
}
