import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * @author 11412
 * 1) 模拟JUnit测试的注释@Test，首先需要编写自定义注解@MyTest，并添加元注解，保证自定义注解只能修饰方法，
 * 且在运行时可以获得。
 * 2) 其次编写目标类（测试类），然后给目标方法（测试方法）使用@MyTest注解，编写三个方法，其中两个加上
 * @MyTest注解。
 * 3) 最后编写调用类，使用main方法调用目标类，模拟JUnit的运行，只要有@MyTest注释的方法都会运行。
 */
public class Question11 {
    @MyTest
    public void run() {
        System.out.println("run start！");
    }

    public static void main(String[] args) throws Exception {
        Class clazz = Question11.class;
        Object object = clazz.newInstance();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(MyTest.class)) {
                method.invoke(object);
            }
        }
    }
}

/**
 * @author 11412
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface MyTest {
}
