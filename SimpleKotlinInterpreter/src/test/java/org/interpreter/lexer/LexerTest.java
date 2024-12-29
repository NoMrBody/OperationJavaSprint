package org.interpreter.lexer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LexerTest {

    @Test
    void tokenizeIdentifiers() {
        var lexer = new Lexer("gcd a b sum ");
        assertEquals("[[<IDENTIFIER>, gcd], [<IDENTIFIER>, a], [<IDENTIFIER>, b], [<IDENTIFIER>, sum], [<EOF>, ]]",
                lexer.tokenize().toString());
    }

    @Test
    void tokenizeKeywords() {
        var lexer = new Lexer(" while var val  let");
        assertEquals("[[<KEYWORD>, while], [<KEYWORD>, var], [<KEYWORD>, val], [<IDENTIFIER>, let], [<EOF>, ]]",
                lexer.tokenize().toString());
    }

    @Test
    void tokenizeNumbers() {
        var lexer = new Lexer(" 1 2 3 5 6    7 8 10  20 51");
        assertEquals("[[<NUMBER>, 1], [<NUMBER>, 2], [<NUMBER>, 3], [<NUMBER>, 5], [<NUMBER>, 6], [<NUMBER>, 7], [<NUMBER>, 8], [<NUMBER>, 10], [<NUMBER>, 20], [<NUMBER>, 51], [<EOF>, ]]",
                lexer.tokenize().toString());
    }

    @Test
    void tokenizeAssignmentOp() {
        var lexer = new Lexer("=");
        assertEquals("[[<ASSIGN>, =], [<EOF>, ]]",
                lexer.tokenize().toString());
    }

    @Test
    void tokenizeConditional(){
        var lexer = new Lexer("if else");
        assertEquals("[[<CONDITIONAL>, if], [<CONDITIONAL>, else], [<EOF>, ]]",
                lexer.tokenize().toString() );
    }

    @Test
    void tokenizeOperators(){
        var lexer = new Lexer("+ - * / % == != >= <=");
        assertEquals("[[<OPERATOR>, +], [<OPERATOR>, -], [<OPERATOR>, *], [<OPERATOR>, /], [<OPERATOR>, %], [<OPERATOR>, ==], [<OPERATOR>, !=], [<OPERATOR>, >=], [<OPERATOR>, <=], [<EOF>, ]]",
                lexer.tokenize().toString());
    }

    @Test
    void tokenizePrintlnKeyword(){
        var lexer = new Lexer("println");
        assertEquals("[[<PRINT>, println], [<EOF>, ]]",
                lexer.tokenize().toString());
    }

    @Test
    void tokenizeParenthesis(){
        var lexer = new Lexer(") ) ( (");
        assertEquals("[[<RIGHT_PAREN>, )], [<RIGHT_PAREN>, )], [<LEFT_PAREN>, (], [<LEFT_PAREN>, (], [<EOF>, ]]",
                lexer.tokenize().toString());
    }

    @Test
    void tokenizeBoolean(){
        var lexer = new Lexer("true false");
        assertEquals("[[<BOOLEAN>, true], [<BOOLEAN>, false], [<EOF>, ]]",
                lexer.tokenize().toString());
    }

    @Test
    void tokenizeBraces(){
        var lexer = new Lexer("}{");
        assertEquals("[[<RIGHT_BRACE>, }], [<LEFT_BRACE>, {], [<EOF>, ]]",
                lexer.tokenize().toString());
    }

}