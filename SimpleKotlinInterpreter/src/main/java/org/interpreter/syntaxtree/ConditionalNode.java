package org.interpreter.syntaxtree;

// handles if-else
public class ConditionalNode extends Node {
    public final Node condition;
    public final BlockNode ifBody;
    public final BlockNode elseBody;

    public ConditionalNode(Node condition, BlockNode ifBody, BlockNode elseBody) {
        this.condition = condition;
        this.ifBody = ifBody;
        this.elseBody = elseBody;
    }

    @Override
    public String toString() {
        return "ConditionalNode{" +
                "condition=" + condition +
                ", ifBody=" + ifBody +
                ", elseBody=" + elseBody +
                '}';
    }
}