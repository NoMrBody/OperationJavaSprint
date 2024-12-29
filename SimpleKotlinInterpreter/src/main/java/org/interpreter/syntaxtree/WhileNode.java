package org.interpreter.syntaxtree;

// tree node for while loop
public class WhileNode extends Node{
    public final Node condition;
    public final Node body;

    public WhileNode(Node condition, Node body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public String toString() {
        return "WhileNode{" +
                "condition=" + condition +
                ", body=" + body +
                '}';
    }
}