import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Subject1 extends JFrame {
    private JTextField jTextField = new JTextField(20);
    private JButton jButton = new JButton("show");
    private JLabel jLabel = new JLabel();

    public Subject1() {
        setLayout(new FlowLayout());

        add(jTextField);
        add(jButton);
        add(jLabel);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jLabel.setText(jTextField.getText());
            }
        });
    }

    public static void main(String[] args) {
        Subject1 frame = new Subject1();
        frame.setTitle("Subject1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }
}

// P1.	编写如下程序。
//1)	创建JFrame，设置setLayout(new FlowLayout());。
//2)	添加成员变量：JTextField，JButton，JLabel。
//3)	文本框输入文字，点击按钮，显示输入的文字。
