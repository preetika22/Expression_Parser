package ExpressionParses;

import java.util.Map;
import java.util.function.DoubleBinaryOperator;

public interface IOperator {
    char getValue();
    int getPrecedence();
}

class Plus extends Operator<DoubleBinaryOperator> {
    public Plus(DoubleBinaryOperator func) {
        super('+', 1, func);
    }
}

 class Minus extends Operator<DoubleBinaryOperator> {
    public Minus(DoubleBinaryOperator func) {
        super('-', 1, func);
    }
}

class Multiply extends Operator<DoubleBinaryOperator> {
    public Multiply(DoubleBinaryOperator func) {
        super('*', 2, func);
    }
}

class Divide extends Operator<DoubleBinaryOperator> {
    public Divide(DoubleBinaryOperator func) {
        super('/', 2, func);
    }
}

class Modulo extends Operator<DoubleBinaryOperator> {
    public Modulo(DoubleBinaryOperator func) {
        super('%', 2, func);
    }
}


