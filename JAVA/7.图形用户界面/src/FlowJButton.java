import java.awt.*;
import javax.swing.*;

public class FlowJButton extends JFrame {
    public FlowJButton() {
        applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        for (int index = 0; index < 9; index++) {
            add(new Button("JButton" + index));
        }
    }

    public static void main(String[] args) {
        FlowJButton frame = new FlowJButton();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Test FlowLayout");
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}
