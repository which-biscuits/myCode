import com.sun.org.apache.xerces.internal.impl.xpath.XPath;

import java.util.Arrays;
import java.util.Scanner;

public class Subject1 {
    public static void main(String[] args) {
        // 题一
        p1();

        // 题二
        p2();

        // 题三
        p3();

        // 题四
        Vehicle test = new Vehicle(100,100);
        test.setTime(100);
        test.setDist(300);
        test.setTime(100);
        test.showInfo();

        // 题五
        p1_4(10);

        // 题六
        p1_5();
    }

    public static void p1() {
        System.out.println("Hello World!");
    }

    public static void p2() {
        System.out.println("姓名:\"小明\"\n学号:\"123456\"");
    }

    public static void p3() {
        int speed;
        String s1 = "正常";
        String s2 = "违章";
        String result;
        System.out.println("请输入速度:");
        Scanner scanner = new Scanner(System.in);
        speed = scanner.nextInt();
        // 判断车速是否违章
        if (speed >= 60 && speed <=120) {
            System.out.println(s1);
        } else {
            System.out.println(s2);
        }
    }

    public static void p1_4(int num) {
        int length = num * 2 + 1;
        char[] ans = new char[length];
        Arrays.fill(ans,' ');
        for (int i = 0; i < num; i++) {
            for (int j = i % 2; j <= i; j += 2) {
                ans[num + 1 - j] = '*';
                ans[num + 1 + j] = '*';
            }
            System.out.println(ans);
            Arrays.fill(ans,' ');
        }
    }

    public static void p1_5() {
        char ch = 'A';
        int i = 100;
        System.out.println("输出结果如下:");
        System.out.println("ch = '" + ch + "'\n i = '" + i + "'");
    }

}

class Vehicle {
    private double speed;   // 速度
    private double dist;    // 行驶距离
    private double time;    // 行驶事件

    // 构造方法, 用于创建实例时, 速度 / 行驶距离赋默认值
    public Vehicle (double speed, double dist) {
        this.speed = speed;
        this.dist = dist;
    }

    // 成员方法, 用于重新设定速度 / 形式距离
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public void setTime(double time) {
        this.time = time;
    }

    // 显示情报, 计算行驶时间, console窗打印
    public void showInfo() {
        System.out.println("此辆车行驶了" + time + "小时");
    }
}
