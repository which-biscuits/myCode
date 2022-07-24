
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author 11412
 * 已知一个类，定义如下
 * package com.itheima;
 * public class DemoClass {
 * public void run() {
 * System.out.println("welcome to heima!");
 * }
 * }
 * (1)写一个Properties格式的配置文件，配置类的完整名称。
 * (2)写一个程序，读取这个Properties配置文件，获得类的完整名称并加载这个类，
 * (3)用反射的方式运行run方法。
 */
public class Question08 {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.setProperty("className", DemoClass.class.getName());
        FileOutputStream fileOutputStream = new FileOutputStream("Demo12/question08.properties");
        properties.store(fileOutputStream, "test");

        FileInputStream fileInputStream = new FileInputStream("Demo12/question08.properties");
        Properties properties1 = new Properties();
        properties1.load(fileInputStream);
        Class clazz = Class.forName(properties1.getProperty("className"));
        Object object = clazz.newInstance();
        Method run = clazz.getMethod("run");
        run.invoke(object);
    }
}
class DemoClass {
    public void run() {
        System.out.println("welcome to heima!");
    }
}

