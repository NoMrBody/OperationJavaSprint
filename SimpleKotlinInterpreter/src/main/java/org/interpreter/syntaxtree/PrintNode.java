package org.interpreter.syntaxtree;

public class PrintNode extends Node{
    public final Node expression;

    public PrintNode(Node expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "PrintNode{" +
                "expression=" + expression +
                '}';
    }
}