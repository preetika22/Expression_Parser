package ExpressionParses;

import EPINterfaces.IExpressionParserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.function.DoubleBinaryOperator;

public class MathExpressionParserService implements IExpressionParserService {
    SYTokenizer tokenizer;
    RPNTreeEvaluator rpnTreeEvaluator;
    String expression;
    Queue<Token> tokenQueue;

    public MathExpressionParserService(){
        InitializeOperators();
        tokenizer = new SYTokenizer(operators);
        rpnTreeEvaluator = new RPNTreeEvaluator(operators);
    }
    private Map<Character, Operator<DoubleBinaryOperator>> operators;
    private void InitializeOperators() {
        ArithmeticOperations ops = new ArithmeticOperations();

        operators = new HashMap<>();
        operators.put('*', new Multiply(ops::mul));
        operators.put('/', new Divide(ops::div));
        operators.put('%', new Modulo(ops::mod));
        operators.put('+', new Plus(ops::add));
        operators.put('-', new Minus(ops::sub));
    }
    private double result;
    @Override
    public void parse(String expression) {
        this.expression = expression;
        tokenQueue = tokenizer.tokenize(expression);
        try {
            result = rpnTreeEvaluator.evaluate(tokenQueue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public double getResult() {
        return result;
    }
}
