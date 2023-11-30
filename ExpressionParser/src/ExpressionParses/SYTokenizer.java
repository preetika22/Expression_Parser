package ExpressionParses;

import java.util.*;
import java.util.function.DoubleBinaryOperator;


public class SYTokenizer {
    private final Map<Character, Operator<DoubleBinaryOperator>> operators;

    public  SYTokenizer( Map<Character, Operator<DoubleBinaryOperator>> operators) {
        this.operators = operators;
    }

    public Queue<Token> tokenize(String expression){
        Queue<Token> tokenQueue = new LinkedList<Token>();
        Stack<OperatorToken> operatorStack = new Stack<OperatorToken>();

        int parseCharPosition = 0;
        int numTokenIndex = 0;
        String[] numbers = expression.split(Arrays.toString(operators.keySet().toArray()));;

        for (String num: numbers){
            numTokenIndex = expression.indexOf(num, parseCharPosition);
            if (numTokenIndex > parseCharPosition) {
                String op = expression.substring(parseCharPosition, numTokenIndex).trim();
                if (isOperator(op.charAt(0))) {
                    while (!operatorStack.isEmpty()) {
                        OperatorToken opS = operatorStack.peek();
                        if (operators.get(op.charAt(0)).getPrecedence() <= operators.get((char) opS.getValue()).getPrecedence()) {
                            OperatorToken removedToken = operatorStack.pop();
                            tokenQueue.add((Token) removedToken);
                        } else {
                            break;
                        }
                    }

                    operatorStack.push(new OperatorToken(op.charAt(0)));
                }
        }
            parseCharPosition = numTokenIndex + num.length();

            tokenQueue.add(new LiteralToken(Double.parseDouble(num)));
        }
            while (!operatorStack.isEmpty()) {
                OperatorToken opS = operatorStack.pop();
                tokenQueue.add((Token) opS);
            }

        return tokenQueue;
    }

    private boolean isOperator(char token) {
        return operators.containsKey(token);
    }
    private boolean isNumber(String token, double[] number) {
        try {
            number[0] = Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}


