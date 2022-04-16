package com.craftinginterpreters.lox;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Token {
    final TokenType type;
    final String lexeme;
    final Object literal;
    final int line;

    public String toString() {
        return type + " " + lexeme + " " + literal;
    }
}
