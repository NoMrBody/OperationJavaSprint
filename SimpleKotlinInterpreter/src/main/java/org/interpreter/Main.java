package org.interpreter;

import org.interpreter.lexer.*;
import org.interpreter.parser.*;
import org.interpreter.evaluator.*;
import org.interpreter.syntaxtree.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String code =
                """
    var num = 1237
    var reversed = 0

    while (num != 0) {
        val digit = num % 10
        reversed = reversed * 10 + digit
        num = num / 10
    }
    println(reversed)
            """;

        Lexer lexer = new Lexer(code);
        List<Token> tokens = lexer.tokenize();
//        System.out.println("Tokens: " + tokens);

        Parser parser = new Parser(tokens);
        try {
            Node syntaxTree = parser.parse();
//            System.out.println("Parsed syntax tree: " + syntaxTree);

            Evaluator evaluator = new Evaluator();
            evaluator.eval(syntaxTree);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}