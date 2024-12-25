package org.interpreter;

import org.interpreter.lexer.Lexer;

public class Main {
    public static void main(String[] args) {
        Lexer lexer = new Lexer("sum = 5 + 10");
        Lexer lexer2 = new Lexer("if (x >= 5) println(x)");
        System.out.println(lexer.tokenize());
        System.out.println(lexer2.tokenize());
    }
}