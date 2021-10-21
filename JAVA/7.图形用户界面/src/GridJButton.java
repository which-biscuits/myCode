import java.awt.*;
import javax.swing.*;

public class GridJButton extends JFrame {
    public GridJButton() {
        setLayout(new GridLayout(3,4,10,10));
        for (int index = 0; index < 12; index++) {
            add(new Button("JButton" + index));
        }
    }

    public static void main(String[] args) {
        GridJButton frame = new GridJButton();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);
    }
}
