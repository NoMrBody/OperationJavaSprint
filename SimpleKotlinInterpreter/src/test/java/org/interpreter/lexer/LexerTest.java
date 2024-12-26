package org.interpreter.lexer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LexerTest {

    @Test
    void tokenizeIdentifiers() {
        var lexer = new Lexer("gcd a b sum ");
        assertEquals("[[<IDENTIFIER>, gcd], [<IDENTIFIER>, a], [<IDENTIFIER>, b], [<IDENTIFIER>, sum]]",
                lexer.tokenize().toString());
    }

    @Test
    void tokenizeKeywords() {
        var lexer = new Lexer(" while var val  let");
        assertEquals("[[<KEYWORD>, while], [<KEYWORD>, var], [<KEYWORD>, val], [<IDENTIFIER>, let]]",
                lexer.tokenize().toString());
    }

    @Test
    void tokenizeNumbers() {
        var lexer = new Lexer(" 1 2 3 5 6    7 8 10  20 51");
        assertEquals("[[<NUMBER>, 1], [<NUMBER>, 2], [<NUMBER>, 3], [<NUMBER>, 5], [<NUMBER>, 6], [<NUMBER>, 7], [<NUMBER>, 8], [<NUMBER>, 10], [<NUMBER>, 20], [<NUMBER>, 51]]",
                lexer.tokenize().toString());
    }

    @Test
    void tokenizeAssignmentOp() {
        var lexer = new Lexer("=");
        assertEquals("[[<ASSIGN>, =]]",
                lexer.tokenize().toString());
    }

    @Test
    void tokenizeConditional(){
        var lexer = new Lexer("   if else");
        assertEquals("[[<CONDITIONAL>, if], [<CONDITIONAL>, else]]",
                lexer.tokenize().toString() );
    }

    @Test
    void tokenizeOperators(){
        var lexer = new Lexer("+ - * / % == != >= <=");
        assertEquals("[[<OPERATOR>, +], [<OPERATOR>, -], [<OPERATOR>, *], [<OPERATOR>, /], [<OPERATOR>, %], [<OPERATOR>, ==], [<OPERATOR>, !=], [<OPERATOR>, >=], [<OPERATOR>, <=]]",
                lexer.tokenize().toString());
    }

    @Test
    void tokenizePrintlnKeyword(){
        var lexer = new Lexer("println");
        assertEquals("[[<PRINT>, println]]",
                lexer.tokenize().toString());
    }

    @Test
    void tokenizeParenthesis(){
        var lexer = new Lexer(") ) ( (");
        assertEquals("[[<RIGHT_PAREN>, )], [<RIGHT_PAREN>, )], [<LEFT_PAREN>, (], [<LEFT_PAREN>, (]]",
                lexer.tokenize().toString());
    }

    @Test
    void tokenizeBoolean(){
        var lexer = new Lexer("true false ");
        assertEquals("[[<BOOLEAN>, true], [<BOOLEAN>, false]]",
                lexer.tokenize().toString());
    }

    @Test
    void tokenizeBraces(){
        var lexer = new Lexer("} {");
        assertEquals("[[<RIGHT_BRACE>, }], [<LEFT_BRACE>, {]]",
                lexer.tokenize().toString());
    }

}