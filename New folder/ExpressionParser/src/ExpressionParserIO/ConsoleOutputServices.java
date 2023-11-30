package ExpressionParserIO;

import EPINterfaces.IOutputServices;

public class ConsoleOutputServices implements IOutputServices {
    @Override
    public void writeMessage(String message) {
        System.out.println(message);
    }
}
