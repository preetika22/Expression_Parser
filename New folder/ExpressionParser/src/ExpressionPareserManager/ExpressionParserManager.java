package ExpressionPareserManager;
import EPINterfaces.IInputServices;
import EPINterfaces.IOutputServices;
import EPINterfaces.IParseManager;
import ExpressionParserIO.ConsoleInputService;
import ExpressionParserIO.ConsoleOutputServices;
import ExpressionParses.MathExpressionParserService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionParserManager implements IParseManager{
    private final ConsoleInputService inputService;
    private final ConsoleOutputServices outputService;
    private final MathExpressionParserService expressionParserService;

    public ExpressionParserManager() {
        inputService = new ConsoleInputService();
        outputService = new ConsoleOutputServices();
        expressionParserService = new MathExpressionParserService();
    }

    public ExpressionParserManager(IInputServices inputService, IOutputServices outputService,
                                     MathExpressionParserService expressionParserService) {
        this.inputService = (ConsoleInputService) inputService;
        this.outputService = (ConsoleOutputServices) outputService;
        this.expressionParserService = expressionParserService;
    }
    @Override
    public void run() {
        while (true){
            String expression = inputService.readExpression();
            if (!validateExpression(expression)) {
                outputService.writeMessage("Enter expression in correct format.");
                continue;
            }
            expressionParserService.parse(expression);
            outputService.writeMessage(String.valueOf(expressionParserService.getResult()));
        }
    }

    private final Pattern pattern = Pattern.compile("^(\\d+(\\.\\d+)?[\\-+*/%])*(\\d+(\\.\\d+)?)$");

    private boolean validateExpression(String expression) {
        Matcher matcher = pattern.matcher(expression);
        return matcher.matches();
    }
}
