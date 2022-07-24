import java.lang.reflect.Constructor;

/**
 * @author 11412
 * 定义一个Student类，用反射去创建一个Student对象，使用两种方式
 * 1. 通过Class对象的方法创建。
 * 2. 通过Constructor对象的方法创建。
 */
public class Question03 {
    public static void main(String[] args) throws Exception {
        Class<Student> studentClass = Student.class;
        Student student1 = studentClass.newInstance();

        Constructor<Student> studentClassConstructor = studentClass.getDeclaredConstructor();
        Student student2 = studentClassConstructor.newInstance();
        student1.wzx = "wzx";
        student2.wzx = "123";
    }
}

class Student {
    public String wzx;
}
