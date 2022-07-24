import org.junit.*;
/**
 * @author 11412
 * 设置一个类Calculator，包含4个方法：加、减、乘、除，使用JUnit对4个方法进行单元测试。
 * 2. 在每个方法运行之前创建Calculator对象，在测试方法运行完毕之后将对象设置为null 。
 */
public class Question01Test {
    Calculator calculator;
    @Before
    public void before() {
        calculator = new Calculator();
    }
    @After
    public void after() {
        calculator = null;
    }
    @Test
    public void add() {
        calculator.add(1.4, 1.234);
    }
    @Test
    public void sub() {
        calculator.sub(1.4, 1.234);
    }
    @Test
    public void mul() {
        calculator.mul(1.4, 1.234);
    }
    @Test
    public void div() {
        calculator.div(1.4, 1.234);
    }
}

class Calculator {
    public void add(double a, double b) {
        System.out.println(a + " + " + b + " = " + (a + b));
    }

    public void sub(double a, double b) {
        System.out.println(a + " - " + b + " = " + (a - b));
    }

    public void mul(double a, double b) {
        System.out.println(a + " x " + b + " = " + (a * b));
    }

    public void div(double a, double b) {
        System.out.println(a + " / " + b + " = " + (a / b));
    }
}
