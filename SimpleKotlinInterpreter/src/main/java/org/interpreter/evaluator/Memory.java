package org.interpreter.evaluator;

import java.util.HashMap;
import java.util.Map;

// simple memory imitation for the interpreter
public class Memory {
    private final Map<String, Integer> variables = new HashMap<>();

    // stores declared variable with its value
    public void setVariable(String name, int value) {
        variables.put(name, value);
    }

    // returns the value of the variable mapped to the inputted key (if exists)
    // otherwise throws an exception
    public int getVariable(String name){
        if (!variables.containsKey(name)){
            throw new RuntimeException(name + " variable not found");
        }
        return variables.get(name);
    }

}
