import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author 11412
 * 定义一个注解：Book
 * * 包含属性：String value() 书名
 * * 包含属性：double price() 价格，默认值为 100
 * * 包含属性：String[] authors() 多位作者
 * 1. 定义类在成员方法上使用Book注解
 * 2. 解析获得该成员方法上使用注解的属性值
 */
public class Question12 {
    @Book(value = "wzx", price = 123.5, authors = {"123", "qweqw"})
    public void run() {
        System.out.println("test");
    }

    public static void main(String[] args) throws Exception {
        Class clazz = Question12.class;
        Object object = clazz.newInstance();
        for (Method declaredMethod : clazz.getDeclaredMethods()) {
            if (declaredMethod.isAnnotationPresent(Book.class)) {
                Book book = declaredMethod.getAnnotation(Book.class);
                System.out.println(declaredMethod.getName());
                System.out.println(book.value());
                System.out.println(book.price());
                System.out.println(Arrays.toString(book.authors()));
            }
        }
    }
}

/**
 * @author 11412
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Book {
    String value();
    double price();
    String[] authors();

}
