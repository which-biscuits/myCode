import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonLabel extends JFrame {
    private Icon[] icons = {new ImageIcon("images/00.png"), new ImageIcon("images/01.png"),
            new ImageIcon("images/02.png"), new ImageIcon("images/03.png"),
            new ImageIcon("images/04.png"), new ImageIcon("images/05.png"),
            new ImageIcon("iamges/06.png"), new ImageIcon("images/07.png")};
    private String[] name = {"00", "01", "02", "03", "04", "05", "06", "07"};
    private JLabel imgLabel;
    private JButton nextButton = new JButton("下一张"),
                    preButton = new JButton("上一张");
    private int index = 0,num = icons.length;
    public ButtonLabel() {
        setLayout(new BorderLayout());
        imgLabel = new JLabel(name[index], icons[index], SwingConstants.CENTER);
        imgLabel.setSize(100,100);
        imgLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        imgLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        add(imgLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        nextButton.setVerticalTextPosition(SwingConstants.CENTER);
        nextButton.setHorizontalTextPosition(SwingConstants.LEFT);
        preButton.setVerticalTextPosition(SwingConstants.CENTER);
        preButton.setHorizontalTextPosition(SwingConstants.LEFT);
        nextButton.setToolTipText(name[index + 1]);
        preButton.setEnabled(false);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index++;
                imgLabel.setIcon(icons[index]);
                imgLabel.setText(name[index]);
                if (index == 1) {
                    preButton.setEnabled(true);
                }
                if (index == num -1) {
                    nextButton.setEnabled(false);
                }
                if (index < num -1) {
                    nextButton.setToolTipText(name[index + 1]);
                }
                if (index > 0) {
                    preButton.setToolTipText(name[index - 1]);
                }
            }
        });
        preButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index--;
                imgLabel.setIcon(icons[index]);
                imgLabel.setText(name[index]);
                if (index == num - 2) {
                    nextButton.setEnabled(true);
                }
                if (index == 0) {
                    preButton.setEnabled(false);
                }
                if (index < num -1) {
                    nextButton.setToolTipText(name[index + 1]);
                }
                if (index > 0) {
                    preButton.setToolTipText(name[index - 1]);
                }
            }
        });
        buttonPanel.add(nextButton);
        buttonPanel.add(preButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        ButtonLabel frame = new ButtonLabel();
        frame.setTitle("测试 JButton 和 Jabel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
