package ExpressionParses;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.function.DoubleBinaryOperator;

public class RPNTreeEvaluator {
    private final Map<Character, Operator<DoubleBinaryOperator>> operators;

    public RPNTreeEvaluator(Map<Character, Operator<DoubleBinaryOperator>> operators) {
        this.operators = operators;
    }

    public double evaluate(Queue<Token> tokenQueue) throws Exception {
        Queue<Token> temp = new LinkedList<>(tokenQueue);
        Stack<LiteralToken> operandStack = new Stack<>();

        while (!temp.isEmpty()) {
            Token token = temp.poll();
            switch (token.getElementType()) {
                // create an operand from the token and push onto the operand stack
                case Literal:
                    operandStack.push((LiteralToken) token);
                    break;

                // Pop the left and right sides of the operator from operand stack
                // Perform the operation and push the results onto the operand stack
                case Operator:
                    OperatorToken opToken = (OperatorToken) token;
                    Operator<DoubleBinaryOperator> op = operators.get((char) opToken.getValue());

                    LiteralToken lhs, rhs;
                    lhs = rhs = null;
                    if (operandStack.size() >= 2) {
                        rhs = operandStack.pop();
                        lhs = operandStack.pop();
                    }

                    if (lhs == null) {
                        throw new Exception("Error");
                    }

                    double result = op.getEval().applyAsDouble((double) lhs.getValue(), (double) rhs.getValue());
                    operandStack.push(new LiteralToken(result));
                    break;
            }
        }

        if (operandStack.size() != 1) {
            throw new Exception("result");
        }

        return (double) operandStack.pop().getValue();
    }
}
