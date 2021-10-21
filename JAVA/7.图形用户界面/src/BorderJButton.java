import java.awt.*;
import javax.swing.*;

public class BorderJButton extends JFrame{
    public BorderJButton() {
        setLayout(new BorderLayout());

        add(new Button("North"), BorderLayout.NORTH);
        add(new Button("South"), BorderLayout.SOUTH);
        add(new Button("East"), BorderLayout.EAST);
        add(new Button("West"), BorderLayout.WEST);
        add(new Button("Center"), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        BorderJButton frame = new BorderJButton();

        frame.setTitle("测试 BorderLayout ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
