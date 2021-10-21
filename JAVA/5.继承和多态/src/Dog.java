import ch05.Animal;

public class Dog extends Animal {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.setAge(2);
        dog.setWeight(5);
        System.out.println("DOG");
        dog.walk();
        dog.bark();
        System.out.println("Age = " + dog.getAge() + "\t\tWeight = " + dog.getWeight());
    }

    public void bark(){
        System.out.println("Bowwow...");
    }
}

class TestProtected{
    public static void main(String[] args){
        Dog dog = new Dog();
        dog.walk();
        dog.bark();
        Dog.main(args);
        // dog.setAge(2);   // protected 只能在所属包及其所属类的子类中进行访问
        // dog.setWeight(5);
        // System.out.println("Age = " + dog.getAge() + "\t\tWeight = " + dog.getWeight());
    }
}