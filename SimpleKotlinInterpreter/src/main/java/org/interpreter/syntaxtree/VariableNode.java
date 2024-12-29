package org.interpreter.syntaxtree;

// tree node for variables
public class VariableNode extends Node {
    public final String name;

    public VariableNode(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "VariableNode{" +
                "name='" + name + '\'' +
                '}';
    }
}