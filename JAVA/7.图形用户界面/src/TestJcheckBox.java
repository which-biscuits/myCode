import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TestJcheckBox extends JFrame {
    private JTextArea textArea = new JTextArea(4,20);
    private JCheckBox[] checkBoxes = {new JCheckBox("复选框1"), new JCheckBox("复选框2"), new JCheckBox("复选框3")};
    public TestJcheckBox() {
        textArea.setEditable(false);
        JPanel centerJPanel = new JPanel();
        centerJPanel.setLayout(new FlowLayout());
        centerJPanel.add(new JScrollPane(textArea));

        JPanel southJPanel = new JPanel();
        southJPanel.setBorder();
        ItemListener listener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JCheckBox sourceCheckBox = (JCheckBox) e.getSource();
                if (sourceCheckBox.isSelected()) {
                    textArea.append(sourceCheckBox.getText() + "被选中\n");
                } else {
                    textArea.append(sourceCheckBox.getText() + "未选中\n");
                }
            }
        };
        southJPanel.setLayout(new FlowLayout());
        for (JCheckBox checkBox : checkBoxes) {
            southJPanel.add(checkBox);
            checkBox.addItemListener(listener);
        }
        setLayout(new BorderLayout());
        add(centerJPanel, BorderLayout.CENTER);
        add(southJPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        TestJcheckBox frame = new TestJcheckBox();
        frame.setTitle("测试JCheckBox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
