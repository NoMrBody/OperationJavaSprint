package org.interpreter.syntaxtree;

import java.util.List;

// object of this class stores the statements inside code block
public class BlockNode extends Node {
    public final List<Node> statements;

    public BlockNode(List<Node> statements) {
        this.statements=statements;
    }

    @Override
    public String toString() {
        return "BlockNode{" +
                "statements=" + statements +
                '}';
    }
}