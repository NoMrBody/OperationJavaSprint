package org.interpreter.lexer;


import java.util.ArrayList;
import java.util.List;

// this class breaks down input into tokens for future
public class Lexer {
    private final String code;
    private int currentIndex = 0;

    public Lexer(String code) {
        this.code = code;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        while (currentIndex < code.length()) {
            char currentChar = code.charAt(currentIndex);

            if (isWhitespace(currentChar)) {
                currentIndex++; // omit whitespace
                continue;
            }

            if (isLetter(currentChar)) {
                String word = readKeyword();
                // dealing with keywords and identifiers
                switch (word) {
                    case "println" -> tokens.add(new Token(TokenType.PRINT, word));
                    case "val", "var", "while" -> tokens.add(new Token(TokenType.KEYWORD, word));
                    case "if", "else" -> tokens.add(new Token(TokenType.CONDITIONAL, word));
                    default -> tokens.add(new Token(TokenType.IDENTIFIER, word));
                }
                continue;
            }

            // handling numbers
            if (isDigit(currentChar)) {
                tokens.add(new Token(TokenType.NUMBER, readNumber()));
                continue;
            }

            // handle binary operators that consist of two symbols
            if (isMultiCharOperator(currentChar)) {
                String operator = readMultiCharOperator();
                tokens.add(new Token(TokenType.OPERATOR, operator));
                continue;
            }

            switch (currentChar) {
                case '=' -> {
                    tokens.add(new Token(TokenType.ASSIGN, "="));
                    currentIndex++;
                    continue;
                }
                case '(' -> {
                    tokens.add(new Token(TokenType.LEFT_PAREN, "("));
                    currentIndex++;
                    continue;
                }
                case ')' -> {
                    tokens.add(new Token(TokenType.RIGHT_PAREN, ")"));
                    currentIndex++;
                    continue;
                }
//                default -> throw new IllegalStateException("Unexpected character: " + currentChar);
            }


            // operator handling
            if (isOperator(currentChar)) {
                tokens.add(new Token(TokenType.OPERATOR, String.valueOf(currentChar)));
                currentIndex++;
                continue;
            }
            throw new LexerException("Unexpected character: " + currentChar);
//            currentIndex++;
        }
        return tokens;
    }

    private String readNumber() {
        StringBuilder number = new StringBuilder();
        while (currentIndex < code.length() && isDigit(code.charAt(currentIndex)))
            number.append(code.charAt(currentIndex++));
        return number.toString();
    }

    // this function runs through a word and builds keyword char by char
    private String readKeyword() {
        StringBuilder kw = new StringBuilder();
        while (currentIndex < code.length() && (isLetter(code.charAt(currentIndex)) || isDigit(code.charAt(currentIndex)))) {
            kw.append(code.charAt(currentIndex++));
        }
        return kw.toString();
    }

    // reading two-symbol comparison operators
    private String readMultiCharOperator() {
        if (currentIndex + 1 < code.length()) {
            String twoCharOp = code.substring(currentIndex, currentIndex + 2);
            if (twoCharOp.equals("!=") || twoCharOp.equals("<=") || twoCharOp.equals(">=") || twoCharOp.equals("==")) {
                currentIndex += 2;
                return twoCharOp;
            }
        }
        return String.valueOf(code.charAt(currentIndex++));
    }

    // checks whether inputted character is whitespace or not
    private boolean isWhitespace(char c) {
        return c == ' ' || c == '\t' || c == '\r' || c == '\n';
    }

    // checks if inputted character is operator
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '>' || c == '<';
    }

    private boolean isMultiCharOperator(char c) {
        return (c == '!' || c == '=' || c == '<' || c == '>');
    }


    // checks if input is letter
    private boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    // checks if input is digit
    private boolean isDigit(char c) {
        return (c >= '0' && c <= '9');
    }

}