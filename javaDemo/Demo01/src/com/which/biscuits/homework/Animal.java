package com.which.biscuits.homework;

/**
 * @author 11412
 */
public abstract class Animal {
    public String name;
    public String color;
    public int price;

    public void eat() {
        System.out.println(name + "在吃饭");
    }
}
