import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Subject2 extends JFrame {
    public Subject2() {
        JTextField jTextField1 = new JTextField(5);
        JTextField jTextField2 = new JTextField(5);
        JButton jButton1 = new JButton("+");
        JButton jButton2 = new JButton("-");
        JButton jButton3 = new JButton("x");
        JButton jButton4 = new JButton("/");
        JLabel jLabel1 = new JLabel(" = ");
        JLabel jLabel = new JLabel();
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton = (JButton) e.getSource();
                try {
                    double a = Double.parseDouble(jTextField1.getText());
                    double b = Double.parseDouble(jTextField2.getText());
                    switch (jButton.getText()) {
                        case "+" : jLabel.setText(Double.toString(a + b));break;
                        case "-" : jLabel.setText(Double.toString(a - b));break;
                        case "x" : jLabel.setText(Double.toString(a * b));break;
                        case "/" : jLabel.setText(Double.toString(a / b));break;
                    }
                } catch (Exception ne) {
                    System.out.println("输入错误, 请重新输入!");
                }
            }
        };

        jButton1.addActionListener(listener);
        jButton2.addActionListener(listener);
        jButton3.addActionListener(listener);
        jButton4.addActionListener(listener);

        setLayout(new FlowLayout());
        add(jTextField1);
        add(jButton1);
        add(jButton2);
        add(jButton3);
        add(jButton4);
        add(jTextField2);
        add(jLabel1);
        add(jLabel);
    }

    public static void main(String[] args) {
        Subject2 frame = new Subject2();
        frame.setSize(500, 300);
        frame.setTitle("Subject2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

// P2．编写如下程序。
//1)	创建JFrame，设置setLayout(new FlowLayout());。
//2)	添加成员变量：2个JTextField、4个JButton、1个JLabel。
//3)	实现计算器程序。2个文本框输入数字，4个按钮为加减乘除，标签出显示计算结果。
