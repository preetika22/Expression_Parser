package ExpressionParses;

import java.util.Map;
import java.util.function.DoubleBinaryOperator;

public abstract class Operator<T> implements IOperator {
    private char value;
    private int precedence;
    protected T eval;

    public Operator(char value, int precedence, T func) {
        this.value = value;
        this.precedence = precedence;
        this.eval = func;
    }

    public Operator(Map<Character, Operator<DoubleBinaryOperator>> operators) {
    }

    public char getValue() {
        return value;
    }

    public int getPrecedence() {
        return precedence;
    }

    public T getEval() {
        return eval;
    }
}
