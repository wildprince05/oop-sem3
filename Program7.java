import java.util.Scanner;
public class Program7{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num, den, result;
        System.out.print("Enter numerator: ");
        num = sc.nextInt();
        System.out.print("Enter denominator: ");
        den = sc.nextInt();
        try {
            result = num / den;
            System.out.println("Result = " + result);
        } 
        catch (ArithmeticException e) {
            System.out.println("Exception handled: Division by zero not allowed.");
        }
        sc.close();
    }
}