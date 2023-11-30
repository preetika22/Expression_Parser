package ExpressionParserIO;
import java.util.Scanner;
import EPINterfaces.IInputServices;

public class ConsoleInputService implements IInputServices {
    @Override
    public String readExpression() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\\nEnter your expression(e.g. 10+11*9/7):");
        return sc.nextLine() ;
    }


}
