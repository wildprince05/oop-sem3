import java.util.Scanner;
class Shape {
    public double area() {
        System.out.println("Default area (Shape class method)");
        return 0;
    }
}

class Circle extends Shape {
    double radius;
    Circle(double r) {
        radius = r;
    }
    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    double length, breadth;
    Rectangle(double l, double b) {
        length = l;
        breadth = b;
    }

    @Override
    public double area() {
        return length * breadth;
    }
}

public class Program5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter radius of Circle: ");
        double r = sc.nextDouble();
        Circle c = new Circle(r);
        System.out.println("Area of Circle = " + c.area());
        
        System.out.print("Enter length of Rectangle: ");
        double l = sc.nextDouble();
        System.out.print("Enter breadth of Rectangle: ");
        double b = sc.nextDouble();
        Rectangle rect = new Rectangle(l, b);
        System.out.println("Area of Rectangle = " + rect.area());
        
        Shape s = new Shape();
        System.out.println("Calling Shape area(): " + s.area());

        sc.close();
    }
}
