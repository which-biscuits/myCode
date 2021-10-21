import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardJButton extends JFrame {
    private CardLayout cl = new CardLayout();
    private Container container = getContentPane();

    public CardJButton() {
        JButton button;
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.next(container); // container必须为内容框格
            }
        };

        setLayout(cl);
        for (int index = 0; index < 12; index++) {
            button = new JButton("JButton " + index);
            add(button, Integer.toString(index));
            button.addActionListener(listener);
        }
    }

    public static void main(String[] args) {
        CardJButton frame = new CardJButton();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}
