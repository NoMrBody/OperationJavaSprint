package org.interpreter.evaluator;

import org.interpreter.syntaxtree.*;

// this class executes the syntax tree
public class Evaluator {
    private final Memory memory = new Memory();

    // this method evaluates the syntax tree nodes accordingly
    public void eval(Node node) {
        try {
            if (node instanceof AssignmentNode assignmentNode) {
                int value = evaluateExpression(assignmentNode.value);
                memory.setVariable(assignmentNode.variable, value);
            } else if (node instanceof PrintNode printNode) {
                int value = evaluateExpression(printNode.expression);
                System.out.println("Kotlin: " + value);
            } else if (node instanceof ConditionalNode conditionalNode){
                int conditionalValue = evaluateExpression(conditionalNode.condition);
                if (conditionalValue != 0){ // condition value != 0 => value is true
                    eval(conditionalNode.ifBody);
                } else if (conditionalNode.elseBody != null) {
                    eval(conditionalNode.elseBody);
                }
            } else if (node instanceof WhileNode whileNode) {
                while (evaluateExpression(whileNode.condition) != 0) {
                    eval(whileNode.body);
                }
            } else if (node instanceof BlockNode blockNode) {
                for (Node statement : blockNode.statements) {
                    eval(statement);
                }
            } else {
                throw new UnsupportedOperationException("unsupported node: " + node.getClass().getSimpleName());
            }
        } catch (Exception e) {
            System.out.println("evaluation error: " + e.getMessage());
        }
    }


    private int evaluateExpression(Node node){
        if (node instanceof NumberNode numberNode){
            return numberNode.value;
        }
        if (node instanceof VariableNode variableNode){
            return memory.getVariable(variableNode.name);
        }
        if (node instanceof BinaryOperationNode binOpNode){
            int left = evaluateExpression(binOpNode.left);
            int right = evaluateExpression(binOpNode.right);
            return switch (binOpNode.operator){
                case "+" -> left + right;
                case "-" -> left - right;
                case "*" -> left * right;
                case "/" -> (right != 0) ? left / right : 0; // intentionally returning 0
                case "%" -> left % right;
                // true == 1
                // false == 0
                case ">" -> left > right ? 1:0;
                case "<" -> left < right? 1 : 0;
                case "<="-> left <= right ? 1:0;
                case ">=" -> left >= right? 1:0;
                case "==" -> left == right? 1:0;
                case "!=" -> left != right? 1:0;

                default -> throw new RuntimeException("unidentified operator" + binOpNode.operator);
            };
        }
        throw new RuntimeException("unsupported expression node: " + node.getClass().getSimpleName());
    }
}
