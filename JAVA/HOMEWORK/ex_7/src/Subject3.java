import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Subject3 extends JFrame {
    public Subject3() {
        JTextField jTextField = new JTextField(30);
        JButton capacity = new JButton("文件大小");
        JButton count = new JButton("文件的单词总数");
        JLabel jLabel = new JLabel();

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton = (JButton) e.getSource();
                switch (jButton.getText()) {
                    case "文件大小" : jLabel.setText(Subject3.getFileSize(jTextField.getText()));break;
                    case "文件的单词总数" :
                        try {
                            jLabel.setText(Subject3.getFileCount(jTextField.getText()));
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                        break;
                }
            }
        };

        capacity.addActionListener(listener);
        count.addActionListener(listener);

        setLayout(new GridLayout(3,2));
        add(new JLabel("请输入文件名称: "));
        add(jTextField);
        add(capacity);
        add(count);
        add(jLabel);

    }

    public static void main(String[] args) {
        Subject3 frame = new Subject3();
        frame.setTitle("Subject3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setVisible(true);
    }

    public static String getFileCount(String string) throws FileNotFoundException {
        File file = new File(string);
        if (!file.exists() || !file.isFile()) {
            System.out.println("文件不存在");
            return "文件不存在!";
        }
        Scanner scanner = new Scanner(file);
        int count_word = 0;
        char[] chars;
        String str;
        while (scanner.hasNext()) {
            str = scanner.next();
            count_word++;
        }
        return "文件单词总数为 : " + count_word + " 个";
    }

    public static String getFileSize(String string) {
        File file = new File(string);
        if (!file.exists() || !file.isFile()) {
            System.out.println("文件不存在");
            return "文件不存在!";
        }
        return "文件大小为 : " + file.length()  + " B";
    }
}
// P3. （附加题）继续使用上周的changzhou.txt，实现以下功能：
//1)	文本框输入文件名，点击按钮，界面显示文件的大小。
//2)	点击另一个按钮，界面显示文件的字数（单词数）。