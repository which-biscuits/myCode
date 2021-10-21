import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestTextComponent extends JFrame{
    private JTextField textField = new JTextField(15);
    private JPasswordField passwordField = new JPasswordField(15);
    private JTextArea textArea = new JTextArea(6,32);
    private final static String newline = "\n";

    public TestTextComponent() {
        setLayout(new BorderLayout());
        textArea.setEditable(false);
        textArea.setToolTipText("接收文本输入");
        passwordField.setToolTipText("接受密码输入");
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.append("用户输入文本是 : " + textField.getText() + newline);
                textField.setText(null);
            }
        });
        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordField.getPassword());
                textArea.append("用户输入的密码是 : " + password + newline);
                passwordField.setText(null);
            }
        });
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.add(textField);
        northPanel.add(passwordField);
        add(northPanel, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());
        centerPanel.add(new JScrollPane(textArea));
        add(centerPanel, BorderLayout.CENTER);
    } 

    public static void main(String[] args) {
        TestTextComponent frame = new TestTextComponent();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
