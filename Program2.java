import java.util.Scanner;
public class Program2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = sc.nextInt();
        int count = 0;
        if (num <= 1) {
            System.out.println(num + " is not a prime number");
        } else {
            for (int i = 2; i <= Math.sqrt(num); i=i+1) {
                if (num % i == 0) {
                    count=count+1;
                    break;
                }
            }
            if (count == 0){
                System.out.println(num + " is a prime number");
            }else{
                System.out.println(num + " is not a prime number");
            }
        }
        sc.close();
    }
}

