package org.interpreter.lexer;

// possible token types
public enum TokenType {
    IDENTIFIER, // variable names
    KEYWORD, //  while, var, val
    NUMBER, // {0,1,2,3,4,5,6,7,8,9}
    CONDITIONAL, // if, else
    ASSIGN, // =
    OPERATOR, // + - * / % == != >= <=
    PRINT, // println command
    LEFT_PAREN, // (
    RIGHT_PAREN, // )
}
