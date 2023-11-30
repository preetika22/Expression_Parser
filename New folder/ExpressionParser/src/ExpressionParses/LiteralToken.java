package ExpressionParses;

public class LiteralToken extends Token {
    public LiteralToken(double value) {
        this.value = value;
        elementType = ElementType.Literal;
    }
}
