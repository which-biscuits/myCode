package com.which.biscuits.homework;

/**
 * @author 11412
 */
public abstract class Person {
    public String name;
    public String age;

    public void eat() {
        System.out.println(name + "eating");
    }

    public void sleeping() {
        System.out.println(name + "sleeping");
    }
}
