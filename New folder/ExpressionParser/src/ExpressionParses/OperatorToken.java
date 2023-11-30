package ExpressionParses;

public class OperatorToken extends Token {
    public OperatorToken(char Op) {
        this.value = Op;
        elementType = ElementType.Operator;
    }
}