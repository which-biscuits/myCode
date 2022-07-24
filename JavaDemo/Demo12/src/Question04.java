import org.junit.rules.TestRule;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author 11412
 * 1. 定义一个类，在类中定义一个成员方法 show ，方法功能是：打印一个字符串。
 * 2. 使用反射机制创建该类的对象，并调用该对象的 show 方法。
 */
public class Question04 {
    public static void main(String[] args) throws Exception {
        Class testClass = Test.class;

        Constructor testConstructor = testClass.getDeclaredConstructor();

        Object object = testConstructor.newInstance();

        Method showMethod = testClass.getDeclaredMethod("show", String.class);

        showMethod.invoke(object, "wdwdwdwdwdwdw");
    }
}

class Test {
    public void show(String text) {
        System.out.println(text);
    }
}
