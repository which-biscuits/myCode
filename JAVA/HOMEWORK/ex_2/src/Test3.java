
public class Test3 {
    public static void main(String[] args) {
        // 重载构造方法
        // 继承构造方法
        Animals anm1 = new Animals();
        Animals anm2 = new Animals(13,5);
        // 分别继承父类不同的构造方法
        Cat cat1 = new Cat(13,5);
        Dog dog1 = new Dog();
        System.out.println("cat1's weight & age:" + cat1.getWeight() + "," + cat1.getAge());
        System.out.println("dog1's weight & age:" + dog1.getWeight() + "," + dog1.getAge());

        // 实现成员方法的多态
        Dog dog2 = new Dog();
        dog1.set(10,1);
        dog2.set("weight",10);
        dog2.set("age",1);
        System.out.println("dog1's weight & age:" + dog1.getWeight() + "," + dog1.getAge());
        System.out.println("dog2's weight & age:" + dog1.getWeight() + "," + dog1.getAge());

        // 子类用父类的静态方法互相传递实例变量
        Cat cat2 = new Cat(1,1);
        Animals.copy(cat1,cat2);    // 将 cat2 的 weight/age 赋值给cat1
        System.out.println("cat1's weight & age:" + cat1.getWeight() + "," + cat1.getAge());
        System.out.println("cat2's weight & age:" + cat1.getWeight() + "," + cat1.getAge());
    }
}

class Animals {    // 抽象父类
    private int weight;     // 动物的体重
    private int age;        // 动物的年龄

    Animals(int weight, int age) {  // 重载构造方法
        this.weight = (Math.max(weight, 0));
        this.age = (Math.max(age, 0));
    }

    Animals() {}    // 构造方法的多态

    public int getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    public void setWeight(int weight) {
        this.weight = (Math.max(weight, 0));
    }

    public void setAge(int age) {
        this.age = (Math.max(age, 0));
    }

    public static void copy(Object obj1, Object obj2) {     // 用于调用的父类静态方法
        Animals anm1 = (Animals) obj1;
        Animals anm2 = (Animals) obj2;
        anm1.setWeight(anm2.getWeight());
        anm1.setAge(anm2.getAge());
    }

    public void pat_calls() {};

    public void jumping() {};

}

class Cat extends Animals {     // 子类 Cat

    Cat(int weight, int age) {
        super(weight,age);
    }

    public void set(int weight, int age) {
        this.setWeight(weight);
        this.setAge(age);
    }

    public void set(String attr, int value) {   // 成员方法的多态
        if (attr.equals("weight")) {
            this.setWeight(value);
        } else if (attr.equals("age")) {
            this.setAge(value);
        }
    }

    @Override
    public void pat_calls() {
        System.out.println("miaow...");
    }

    @Override
    public void jumping() {
        System.out.println("The cat is jumping!");
    }
}

class Dog extends Animals {     // 子类 Dog

    Dog() {     // 分别继承父类不同的构造方法
        super();
    }

    public void set(int weight, int age) {
        this.setWeight(weight);
        this.setAge(age);
    }

    public void set(String attr, int value) {   // 成员方法的多态
        if (attr.equals("weight")) {
            this.setWeight(value);
        } else if (attr.equals("age")) {
            this.setAge(value);
        }
    }

    @Override
    public void pat_calls() {
        System.out.println("wow...");
    }

    @Override
    public void jumping() {
        System.out.println("The Dog is jumping!");
    }
}
