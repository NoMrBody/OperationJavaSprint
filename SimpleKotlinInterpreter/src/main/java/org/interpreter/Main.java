package org.interpreter;

import org.interpreter.lexer.*;
import org.interpreter.parser.*;
import org.interpreter.evaluator.*;
import org.interpreter.syntaxtree.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        /* available file names:
         * checkpalindromenumber.txt
         * factorial.txt
         * fibonacci.txt
         * gcd.txt
         * isprime.txt
         * maxdigit.txt
         * multiplicationtbl.txt
         * reversenum.txt
         * sum.txt
         * sumofdigits.txt
         * */

        String fileName = "reversenum.txt"; // update file name as needed

        String code = readAlgorithmFromFile(fileName);

        //to run the input manually
        //pass manualInput to the Lexer constructor
        String manualInput = """
                val x = 5
                while (x > 0) {
                    println(x)
                    x = x - 1
                }
                """;

        if (code == null) {
            System.out.println("couldn't load algorithm from file");
            return;
        }

        Lexer lexer = new Lexer(code);
        List<Token> tokens = lexer.tokenize();
        Parser parser = new Parser(tokens);
        try {
            Node syntaxTree = parser.parse();
//            System.out.println("parsed syntax tree: " + syntaxTree);
            Evaluator evaluator = new Evaluator();
            evaluator.eval(syntaxTree);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /*
    This method reads the content of a file located in src/main/resources/algorithms
     */
    private static String readAlgorithmFromFile(String fileName) {
        try {
            var classLoader = Main.class.getClassLoader();
            var filePath = Objects.requireNonNull(classLoader.getResource("algorithms/" + fileName)).getPath();
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException | NullPointerException e) {
            System.err.println("error reading file: " + fileName);
            e.printStackTrace();
            return null;
        }
    }
}