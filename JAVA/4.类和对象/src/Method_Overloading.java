public class Method_Overloading {
    public static void main(String[] args) {
        char    x1 = 'a';
        byte    x2 = 4;
        short   x3 = 5;
        int     x4 = 6;
        long    x5 = 7L;
        float   x6 = 9.2f;
        double  x7 = 10.5d;

        method(x1);
        method(x2);
        method(x3);
        method(x4);
        method(x5);
        method(x6);
        method(x7);
        method(x4,x4);
        method(x4,x6);
        method(x6,x4);
    }
    static void method(short x){
        System.out.println("in method(short)");
    }
    static void method(int x){
        System.out.println("in method(int)");
    }
    static void method(long x){
        System.out.println("in method(long)");
    }
    static void method(float x){
        System.out.println("in method(float)");
    }
    static void method(double x){
        System.out.println("in method(double)");
    }
    static void method(int x, int y){
        System.out.println("in method(int, int)");
    }
    static void method(int x, double y){
        System.out.println("in method(int, double)");
    }
    static void method(double x, int y){
        System.out.println("in method(double, int)");
    }
}
