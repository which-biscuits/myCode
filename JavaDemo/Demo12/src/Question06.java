import java.io.File;
import java.lang.reflect.Field;

/**
 * @author 11412
 * 按要求完成下面两个方法的方法体
 * 写一个方法，此方法可将obj对象中名为propertyName的属性的值设置为value.
 * public void setProperty(Object obj, String propertyName, Object value){
 * }
 * 写一个方法，此方法可以获取obj对象中名为propertyName的属性的值
 * public Object getProperty(Object obj, String propertyName){
 * }
 */
public class Question06 {
    public static void main(String[] args) throws Exception {
        Student student = new Student();
        student.wzx = "wzx123123";

        new Question06().setProperty(student, "wzx", "1234545");

        System.out.println(new Question06().getProperty(student, "wzx"));
    }


    public void setProperty(Object obj, String propertyName, Object value) throws Exception {
        Class clazz = obj.getClass();
        Field field = clazz.getField(propertyName);
        field.setAccessible(true);
        field.set(obj, value);
    }
    public Object getProperty(Object obj, String propertyName) throws Exception {
        Class clazz = obj.getClass();
        Field field = clazz.getField(propertyName);
        field.setAccessible(true);
        return field.get(obj);
    }
}
