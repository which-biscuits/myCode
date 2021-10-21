public class MultiExtends {
    public static void main(String[] args) {
        D d = new D();
        runa(d);
        runb(d);
        runc(d);
        System.out.println(d.toString());
    }

    static void runa(A a) {
        a.a();
    }

    static void runb(B b) {
        b.b();
    }

    static void runc(C c) {
        c.c();
    }
}

class D extends C implements A,B {
    public void a() {
        System.out.println("a() in D");
    }

    public void b() {
        System.out.println("b() in D");
    }

    public void c() {
        System.out.println("c() in D");
    }
}

interface A {
    void a();
}

interface B {
    void b();
}

class C {
    public void c() {
        System.out.println("c() in C");
    }
}


