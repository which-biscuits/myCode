import java.time.Period;
import java.util.Map;

/**
 * @author 11412
 * 1. 经理
 *   成员变量:工号,姓名,工资
 *   成员方法:工作(管理其他人),吃饭(吃鱼)
 * 2. 厨师
 *   成员变量:工号,姓名,工资
 *   成员方法:工作(炒菜),吃饭(吃肉)
 */
public class Question02 {
    public static void main(String[] args) {
        Person manager = new Manager("1234565", "test", "123.45");

        Person worker = new Worker("5431", "wedfkjsk", "123.43");

        manager.eating();
        manager.working();

        worker.eating();
        worker.working();
    }
}

class Worker extends Person {

    public Worker(String id, String name, String salary) {
        super(id, name, salary);
    }

    @Override
    public void working() {
        System.out.println("工号为：" + getId() + " 姓名为：" + getName() + "薪水为：" + getSalary() + " 的厨师在炒菜");
    }

    @Override
    public void eating() {
        System.out.println("工号为：" + getId() + " 姓名为：" + getName() + "薪水为：" + getSalary() + " 的厨师在吃鱼");
    }
}

class Manager extends Person {

    public Manager(String id, String name, String salary) {
        super(id, name, salary);
    }

    @Override
    public void working() {
        System.out.println("工号为：" + getId() + " 姓名为：" + getName() + "薪水为：" + getSalary() + " 的经理正在管理其他人");
    }

    @Override
    public void eating() {
        System.out.println("工号为：" + getId() + " 姓名为：" + getName() + "薪水为：" + getSalary() + " 的经理正在吃肉");
    }
}

abstract class Person {
    private String id;
    private String name;
    private String salary;

    public Person(String id, String name, String salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Person() {
    }

    public abstract void working();

    public abstract void eating();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
