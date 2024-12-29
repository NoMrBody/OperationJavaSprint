package org.interpreter.syntaxtree;

// node that stores numbers
public class NumberNode extends Node {
    public final int value;

    public NumberNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "NumberNode{" +
                "value=" + value +
                '}';
    }
}