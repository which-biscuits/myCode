/**
 * @author 11412
 * . 定义接口AA，普通类BB实现接口AA
 * 2. AA接口中，定义抽象方法showA
 * 3. AA接口中，定义私有方法show10（String str），循环打印10次str
 * 4. AA接口中，定义默认方法showB10，showC10，分别调用show10方法，传入参数
 * 5. 测试类中，创建BB对象，调用showA方法，showB10方法，showC10方法
 */
public class Question04 {
    public static void main(String[] args) {
        BB bb = new BB();
        bb.showA();
        bb.showB10();
        bb.showC10();
    }
}

class BB implements AA {
    @Override
    public void showA() {
        System.out.println("AAAA");
    }
}

interface AA {
    void showA();

    default void show10(String str) {
        for (int i = 0; i < 10; i++) {
            System.out.print(str + " ");
        }
        System.out.println();
    }
    default void showB10() {
        show10("BBBB");
    }
    default void showC10() {
        show10("CCCC");
    }
}
