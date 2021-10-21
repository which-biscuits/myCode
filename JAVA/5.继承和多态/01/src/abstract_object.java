
public class abstract_object {
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.setWeight(2);
        cat.setAge(1);
        System.out.println("CAT age = " + cat.getAge() +
                "\nCAT weight = " + cat.getWeight());
        makeSpeak(cat);

        Dog dog = new Dog();
        dog.setWeight(10);
        dog.setAge(5);
        System.out.println("DOG age = " + dog.getAge() +
                "\nDOG weight = " + dog.getWeight());
        makeSpeak(dog);

    }

    public static void makeSpeak(Animal animal) {
        animal.speak();
    }
}

class Cat extends Animal {
    public void speak() {
        System.out.println("Meow...");
    }
}

class Dog extends Animal {
    public void speak() {
        System.out.println("Bowwow...");
    }
}

abstract class Animal {
    private int weight;
    private int age;

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    abstract public void speak();
}
