package org.interpreter.syntaxtree;

/* object of this class stores information about the variable created and the value assigned to it */
public class AssignmentNode extends Node{
    public final String variable;
    public final Node value;

    public AssignmentNode(String variable, Node value) {
        this.variable = variable;
        this.value = value;
    }

    @Override
    public String toString() {
        return "AssignmentNode{" +
                "variable='" + variable + '\'' +
                ", value=" + value +
                '}';
    }
}