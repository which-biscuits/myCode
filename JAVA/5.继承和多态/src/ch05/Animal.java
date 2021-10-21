package ch05;

public class Animal{
    private int weight;
    private int age;

    protected int getAge(){
        return age;
    }

    protected int getWeight(){
        return weight;
    }

    protected void setAge(int age){
        this.age = ((age>=0)? age : 0);
    }

    protected void setWeight(int weight){
        this.weight = ((weight>=0)?weight:0);
    }

    public void walk(){
        System.out.println("walk...");
    }

}
