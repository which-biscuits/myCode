package ch05;

public class Cat extends Animal{
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.setAge(2);
        cat.setWeight(5);
        System.out.println("CAT");
        cat.meow();
        System.out.println("Age = " + cat.getAge() + "\t\tWeight = " + cat.getWeight());
    }
    void meow(){
        System.out.println("Meow....");
    }
}
