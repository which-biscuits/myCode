import org.junit.Test;

import javax.jws.soap.SOAPMessageHandlers;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 11412
 * 1. 请定义一个最简单的注解@MyAnno1
     * 1) 不需要任何属性。
     * 2) 此注解只能修饰“类”和接口
     * 3) 此注解要出现在源码和字节码中
     * 4) 定义测试类：Test1，并使用此注解修饰
 * 2. 请定义注解@MyAnno2：
     * 1) 包含一个String类型的属性“type”，并且定义默认值“java”。
     * 2) 此注解只能修饰“字段”。
     * 3) 此注解只需要能够在源码中使用。
     * 4) 定义测试类：Test2，随意定义一个成员属性，并使用此注解；
 * 3. 请定义注解@MyAnno3：
     * 1) 包含一个String类型的属性“type”，不定义默认值。
     * 2) 包含一个int[]数组类型的属性“intArr”，不定义默认值。
     * 3) 此注解只能修饰“方法”。
     * 4) 此注解要出现在源码和字节码中。
     * 5) 定义测试类：Test3，随意定义一个成员方法，并使用此注解；
 */
@MyAnno1
public class Question10 {
    @MyAnno2(type = "123")
    private String type;

    @Test
    @MyAnno3(type = "123", intArr = {})
    public void test1() {
        System.out.println("runTest1");
    }
}

/**
 * @author 11412
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@interface MyAnno1 {

}

/**
 * @author 11412
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.CLASS)
@interface MyAnno2 {
    String type() default "java";
}

/**
 * @author 11412
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno3 {
    String type();
    int[] intArr();
}