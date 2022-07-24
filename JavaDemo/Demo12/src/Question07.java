import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author 11412
 *  定义一个Person类，包含属性name、age。
 * 2. 使用反射的方式创建一个实例、调用构造函数初始化name、age。使用反射方式调用setName方法对姓名进
 * 行设置，不使用setAge方法直接使用反射方式对age赋值。
 */
public class Question07 {
    public static void main(String[] args) throws Exception {
        Class clazz = Person.class;
        Constructor constructor = clazz.getDeclaredConstructor(String.class, int.class);

        Object object = constructor.newInstance("name", 123123);
        Method setName = clazz.getMethod("setName", String.class);
        System.out.println(object);
        setName.invoke(object, "qweqwe");
        System.out.println(object);
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(object, "dfsdfhjsdhfjs");
        System.out.println(object);
    }

}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
