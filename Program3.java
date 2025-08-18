import java.util.Scanner;
public class Program3 {
    public static int fibonacci(int n){
        if(n<=1){
            return n;
        }
        return fibonacci(n-1)+fibonacci(n-2);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        System.out.println("Fibonacci Sequence upto "+n);
        for(int i=0; i<n; i=i+1){
            System.out.print(fibonacci(i)+" ");
        }
    }
}
