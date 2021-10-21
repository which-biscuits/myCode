import javax.swing.*;
import java.awt.event.*;

public class JButtonEvent1 extends JFrame{
    public JButtonEvent1() {
        JButton button = new JButton("按钮");
        button.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(((JButton) e.getSource()).getText());
                    }
                }
        );
        add(button);
    }

    public static void main(String[] args) {
        JButtonEvent1 frame = new JButtonEvent1();
        frame.setTitle("演示动作时间处理");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.setVisible(true);
    }
}
