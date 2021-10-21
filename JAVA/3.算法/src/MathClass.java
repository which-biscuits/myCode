import java.lang.Math;
public class MathClass {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            int n = (int)(Math.random() * 100);
            System.out.print(n);
            if(i != 9){
                System.out.print("+");
            } else{
                System.out.print("=");
            }
            sum += n;
        }
        System.out.println(sum);
        System.out.println("Math.sin(Math.toRadians(90)) = " + Math.sin(Math.toRadians(90)));
        System.out.println("Math.cos(Math.PI) = " + Math.cos(Math.PI));
        System.out.println("Math.tan(0) = " + Math.tan(0));
        System.out.println("Math.asin(0) = " + Math.asin(0));

        System.out.println("Math.exp(2) = " + Math.exp(2));
        System.out.println("Math.pow(2,3) = " + Math.pow(2,3));
        System.out.println("Math.sqrt(4) = " + Math.sqrt(4));
        System.out.println("Math.cbrt(8) = " + Math.cbrt(8));

        System.out.println("Math.log(Math.E) = " + Math.log(Math.E));
        System.out.println("Math.log10(10) = " + Math.log10(10));

        System.out.println("Math.ceil(3.6) = " + Math.ceil(3.6));
        System.out.println("Math.ceil(-3.6) = " + Math.ceil(-3.6));
        System.out.println("Math.floor(-3.6) = " + Math.floor(-3.6));
        System.out.println("Math.rint(-3.6) = " + Math.rint(-3.6));
        System.out.println("Math.rint(3.5) = " + Math.rint(3.5));
        System.out.println("Math.round(3.6) = " + Math.round(3.6));

        System.out.println("Math.abs(-5.2) = " + Math.abs(-5.2));
        System.out.println("Math.min(10.2,10.5) = " + Math.min(10.2,10.5));
        System.out.println("Math.max(10.2,10.5) = " + Math.max(10.2,10.5));
    }
}
