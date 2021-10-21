public class TestInnerClass {
    public static void main(String[] args) {
        OuterClass t = new OuterClass();
        ex a = t.f2();
        System.out.println("a.i = " + a.getI());
        OuterClass.InnerClass2 tin2 = t.new InnerClass2(20);
        tin2.h();
        OuterClass.M mm = new OuterClass.M();
        OuterClass.f3();
    }
}

class OuterClass {
    private int j = 5, k = 6;

    private class InnerClass1 extends ex{
        public void g() {
            System.out.println("调用私有内部类的方法 g()");
        }
    }

    class InnerClass2 {
        private int m;

        InnerClass2(int n){
            m = n;
        }

        public void h() {
            f1();
            j++;
            k++;
            System.out.println("j = " + j + "\tk = " + k + "\tm = " + m + "\t in InnerClass2");
        }
    }

    static class M {}

    public void f1() {
        System.out.println("j = " + j + "\tk = " + k + "\t in OuterClass");
    }

    public ex f2() {
        return new InnerClass1();
    }

    public static void f3() {
        OuterClass out = new OuterClass();
        OuterClass.InnerClass2 in2 = out.new InnerClass2(30);
        out.f1();
        in2.h();
    }
}

class ex {
    private int i = 10;

    public int getI(){
        return i;
    }
}
