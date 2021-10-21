import java.awt.event.*;
import javax.swing.*;

public class TestWindowAdapter {
    public static void main(String[] args) {
        JFrame frame = new JFrame("测试 WindowAdapter ");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("wzxyyds666");
                System.exit(0);
            }
        });
        frame.setSize(300,300);
        frame.setVisible(true);
    }
}
