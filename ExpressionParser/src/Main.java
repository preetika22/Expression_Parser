import EPINterfaces.IParseManager;
import ExpressionPareserManager.ExpressionParserManager;

public class Main {
    public static void main(String[] args) {
        IParseManager manager = new ExpressionParserManager();
        manager.run();
    }
}
