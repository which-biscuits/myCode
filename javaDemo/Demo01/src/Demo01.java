/**
 * @author 11412
 * - 语法点：继承
 * - 请使用继承定义以下类:
 *       程序员(Coder)
 *         成员变量: 姓名,年龄
 *         成员方法: 吃饭,睡觉,敲代码
 *
 *       老师(Teacher)
 *         成员变量: 姓名,年龄
 *         成员方法: 吃饭,睡觉,上课
 *   将程序员和老师中相同的内容(姓名,年龄,吃饭,睡觉)抽取到父类Person中
 */
public class Demo01 {
    public static void main(String[] args) {
        Coder coder = new Coder("马化腾", 12);
        Teacher teacher = new Teacher("马云", 32);

        coder.eat();
        coder.sleep();
        coder.coding();

        teacher.eat();
        teacher.sleep();
        teacher.teaching();
    }
}

class Coder extends Person{

    public Coder(String name, int age) {
        super(name, age);
    }
    public void coding(){
        System.out.println(name + " is coding!");
    }
}

class Teacher extends Person {

    public Teacher(String name, int age) {
        super(name, age);
    }
    public void teaching() {
        System.out.println(name + " is teaching！");
    }
}

class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void eat() {
        System.out.println(name + "正在吃饭！");
    }

    public void sleep() {
        System.out.println(name + "正在睡觉！");
    }
}
