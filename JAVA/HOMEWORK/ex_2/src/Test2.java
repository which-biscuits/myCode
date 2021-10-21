import java.io.IOException;

public class Test2 {
    public static int add(int a, int b) {
        return a + b;
    }
    public static int sub(int a, int b) {
        return a - b;

    }
    public static int mul(int a, int b) {
        return a * b;
    }
    public static double div(int a, int b) {
        return ((double) a) / b;
    }
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        int a = (int) System.in.read()-'0';
        char b = (char) System.in.read();
        int c = (int) System.in.read()-'0';
        switch (b){
            case '+':{ System.out.println(a+"+"+c+"="+add(a,c));break; }
            case '-':{ System.out.println(a+"-"+c+"="+sub(a,c));break; }
            case '*':{ System.out.println(a+"*"+c+"="+mul(a,c));break; }
            case '/':{ System.out.println(a+"/"+c+"="+div(a,c));break; }
            default:{ System.out.println("运算符输入错误!"); break;}
        }
    }
}
