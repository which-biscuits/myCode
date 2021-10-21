import javax.swing.*;
import java.awt.event.*;

public class JButtonEvent extends JFrame implements ActionListener{
    public JButtonEvent() {
        JButton button = new JButton("按钮");
        button.addActionListener((ActionListener) this);    // 注册
        add(button);
    }

    public static void main(String[] args) {
        JButtonEvent frame = new JButtonEvent();
        frame.setTitle("演示动作事件处理");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(100,100);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(((JButton) e.getSource()).getText());
    }
}
