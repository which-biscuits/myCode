package com.which.biscuits.homework;

/**
 * @author 11412
 */
public class Homework02 {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.name = "旺财";
        Cat cat = new Cat();
        cat.name = "波斯猫";

        dog.eat();
        dog.lookHome();
        cat.eat();
        cat.catchMouse();
    }
}
