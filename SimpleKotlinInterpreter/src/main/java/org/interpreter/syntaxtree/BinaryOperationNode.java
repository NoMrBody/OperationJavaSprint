package org.interpreter.syntaxtree;

// object of this class stores both sides of binary operator and the operator itself
public class BinaryOperationNode extends Node{
    public final Node left, right;
    public final String operator;

    public BinaryOperationNode(Node left, String operator, Node right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public String toString() {
        return "BinaryOperationNode{" +
                "left=" + left +
                ", right=" + right +
                ", operator='" + operator + '\'' +
                '}';
    }
}