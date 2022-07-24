import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * @author 11412
 * 编写一个类A，定义一个实例方法 showString ，用于打印一个字符串。
 * 2. 在编写一个类TestA，用键盘输入一个字符串，该字符串就是类A的全名，使用反射机制创建该类的对象，并
 * 调用该对象中的方法showString
 */
public class Question05 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入类名：");
        String className = scanner.nextLine();
        Class clazz = Class.forName(className);
        Constructor constructor = clazz.getDeclaredConstructor();
        Method showString = clazz.getDeclaredMethod("showString", String.class);

        showString.invoke(constructor.newInstance(), "你好，测试通过！");
    }
}

class Test01 {
    public void showString(String text) {
        System.out.println(text);
    }
}
