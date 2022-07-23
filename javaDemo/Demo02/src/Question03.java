/**
 * @author 11412
 */
public class Question03 {
    public static void main(String[] args) {
        B b = new B();
        b.showA();
        b.showB();
    }
}

class B implements A {

    @Override
    public void showA() {
        System.out.println("AAAA");
    }
}

interface A {
    void showA();
    default void showB(){
        System.out.println("BBBB");
    }
}