/**
 * @author 11412
 * 2岁的红色的公鸡会吃饭(啄米)和打鸣
 * 1岁的黑色的鸭子会吃饭(吃鱼)和游泳
 */
public class Question01 {
    public static void main(String[] args) {
        Chick chick = new Chick(10, "红色");
        Duck duck = new Duck(20, "黑色");

        chick.eat();
        chick.talks();

        duck.eat();
        duck.swim();
    }

}

class Duck extends Animal {

    public Duck(int age, String color) {
        super(age, color);
    }

    @Override
    public void eat() {
        System.out.println(getAge() + " 的 " + getColor() + " 的公鸭子会吃鱼 " );
    }

    public void swim() {
        System.out.println(getAge() + " 的 " + getColor() + " 的公鸭子会游泳 " );
    }
}

class Chick extends Animal {

    public Chick(int age, String color) {
        super(age, color);
    }

    @Override
    public void eat() {
        System.out.println(getAge() + " 的 " + getColor() + " 的公鸡会啄米 " );
    }

    public void talks() {
        System.out.println(getAge() + " 的 " + getColor() + " 的公鸡会打鸣 " );
    }
}

abstract class Animal {
    private int age;
    private String color;

    public Animal(int age, String color) {
        this.age = age;
        this.color = color;
    }

    public Animal() {
    }

    public abstract void eat();

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
