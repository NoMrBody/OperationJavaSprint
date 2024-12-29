package org.interpreter.parser;

import org.interpreter.lexer.*;
import org.interpreter.syntaxtree.*;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final List<Token> tokens;   //tokens list from the lexer
    private int currIndex = 0;  //position of current token

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    private Token currToken(){
        return tokens.get(currIndex);
    }

    //this method increments list index by 1
    private void advance(){
        currIndex += 1;
    }

    // this method checks whether current token type matches inputted one or not
    private boolean match(TokenType type){
        if (currToken().getType() == type){
            advance();
            return true;
        }
        return false;
    }

    // makes sure that current token matches expected type, otherwise throws exception
    private void expect(TokenType type, String message){
        if (!match(type)){
            throw new ParserException(message + "found: " + currToken().getType());
        }
    }

    // this method works at the highest level
    // processes the whole program and creates a tree structure for execution
    public Node parse(){
        List<Node> statements = new ArrayList<>();
        while (currIndex < tokens.size() && currToken().getType() != TokenType.EOF){
            try {
                statements.add(parseStatement());
            } catch (ParserException e) {
                System.out.println("parser error at token " + currToken() + ": " + e.getMessage());
                advance();
            }
        }
        return new BlockNode(statements); // BlockNOde contains all parsed statements as its children
    }

    // parses single statement
    private Node parseStatement(){
        if (currToken().getType() == TokenType.KEYWORD){
            switch (currToken().getValue()){
                case "while": return parseWhile();
                case "if" : return parseIf();
                case "val", "var" : return parseVariableDeclaration();

            }
        }
        if (currToken().getType() == TokenType.PRINT){
            return parsePrint();
        }
        if (currToken().getType() == TokenType.IDENTIFIER){
            return parseAssignment();
        }
        throw new ParserException("unexpected token: " + currToken().getValue());
    }


    // parses variable declaration
    private Node parseVariableDeclaration(){
        expect(TokenType.KEYWORD, "val or var expected");
        String variable = currToken().getValue();
        expect(TokenType.IDENTIFIER, "variable name expected");
        expect(TokenType.ASSIGN, "= expected");
        Node value = parseExpression();
        return new AssignmentNode(variable, value);
    }



    // parses term in an expression
    private Node parseTerm(){
        if (match(TokenType.NUMBER)){
            return new NumberNode(Integer.parseInt(tokens.get(currIndex-1).getValue()));
        }
        if (match(TokenType.IDENTIFIER)){
            return new VariableNode(tokens.get(currIndex-1).getValue());
        }

        throw new ParserException("number of variable expected");
    }

    // parses arithmetic or logic expression
    private Node parseExpression(){
        Node left = parseTerm();
        while (match(TokenType.OPERATOR)){
            String operator = tokens.get(currIndex-1).getValue();
            Node right = parseTerm();
            left = new BinaryOperationNode(left, operator, right);
        }
        return left;
    }

    // parses statements inside code block
    private Node parseBlock(){
        expect(TokenType.LEFT_BRACE, "{ expected");
        List<Node> statements = new ArrayList<>();
        while (currToken().getType()!= TokenType.RIGHT_BRACE && currToken().getType() !=TokenType.EOF){
            statements.add(parseStatement());
        }
        expect(TokenType.RIGHT_BRACE, "}");
        return new BlockNode(statements);
    }


    // parses variable assignments
    private Node parseAssignment() {
        String variable = currToken().getValue();  // catch variable name
        advance();
        expect(TokenType.ASSIGN, " = expected ");
        Node value = parseExpression();
        return new AssignmentNode(variable, value);
    }

    // parses println command
    private Node parsePrint() {
        expect(TokenType.PRINT, " println expected ");
        expect(TokenType.LEFT_PAREN, "( expected after println");
        Node expression = parseExpression();
        expect(TokenType.RIGHT_PAREN, ") expected for println");
        return new PrintNode(expression);
    }

    // parses if-else conditional
    private Node parseIf() {
        expect(TokenType.CONDITIONAL, "if expected");
        expect(TokenType.LEFT_PAREN, "( expected after if");
        Node condition = parseExpression();
        expect(TokenType.RIGHT_PAREN, ") expected for if");
        Node ifBody = parseBlock();
        Node elseBody = null;
        if (currToken().getType() == TokenType.CONDITIONAL && currToken().getValue().equals("else")){ // check if if is with else part
            advance();
            elseBody = parseBlock(); // parsing else part
        }
        return new ConditionalNode(condition, (BlockNode) ifBody, (BlockNode) elseBody);
    }

    // parses while loop
    private Node parseWhile() {
        expect(TokenType.KEYWORD, "while expected");
        expect(TokenType.LEFT_PAREN, "( expected after while");
        Node condition = parseExpression();
        expect(TokenType.RIGHT_PAREN, ") expected for while");
        Node body = parseBlock();
        return new WhileNode(condition, body);
    }


}
