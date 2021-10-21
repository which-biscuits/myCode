import javax.swing.*;
import java.awt.*;

public class FrameDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("测试JFrame");
        JButton button = new JButton("按钮");    // 创建一个按钮
        Container container = frame.getContentPane();    // 取得框架的内容框格
        container.add(button);    // 将按钮添加到框架的内容框格中
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setVisible(true);
    }
}

class FrameWithButton1 extends JFrame {
    public FrameWithButton1() {
        JButton button1 = new JButton("按钮");
        button1.setSize(100,100);
        add(button1);
        JButton button2 = new JButton("按钮");
        button2.setSize(100,100);
        add(button2);
    }

    public static void main(String[] args) {
        FrameWithButton1 frame = new FrameWithButton1();
        frame.setTitle("将一个按钮添加到框架中");
        frame.setSize(3000,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
