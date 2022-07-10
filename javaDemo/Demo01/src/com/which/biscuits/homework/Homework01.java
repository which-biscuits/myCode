package com.which.biscuits.homework;

/**
 * @author 11412
 */
public class Homework01 {
    public static void main(String[] args) {
        Coder coder = new Coder();
        coder.name = "马云";
        Teacher teacher = new Teacher();
        teacher.name = "马化腾";

        coder.eat();
        coder.sleeping();
        coder.coding();

        teacher.eat();
        teacher.sleeping();
        teacher.teaching();
    }
}
