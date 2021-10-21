import java.io.*;
import java.util.*;

public class subject2 {
    public static void main(String[] args) throws IOException {
        File file = new File("changzhou.txt");
        System.out.println("文件共有 " + file.length() + " 个字节");
        FileReader fileReader = new FileReader("changzhou.txt");
        int count = 0;
        while (fileReader.read() != -1) {
            count++;
        }
        System.out.println("文件共有 " + count + " 个字符");

        Scanner scanner = new Scanner(new File("changzhou.txt"));
        int count_line = 0;
        int count_word = 0;
        int ChangZhou = 0;
        char[] chars;
        String str;
        while (scanner.hasNext()) {
            str = scanner.next();
            if (str.contains("changzhou") || str.contains("Changzhou"))
                ChangZhou++;
            count_word++;
            chars = str.toCharArray();
            for (char ch : chars) {
                if (ch == '.') {
                    count_line++;
                }
            }
        }
        System.out.println("以 '.' 分隔, 文件共有 " + count_line + " 个句子");
        System.out.println("以 ' ' 分隔, 文件共有 " + count_word + " 个单词");
        System.out.println("changzhou 在文件中共出现了 : " + ChangZhou + " 次");

        FileInputStream fi = new FileInputStream("changzhou.txt");
        BufferedInputStream bi = new BufferedInputStream(new FileInputStream("changzhou.txt"));
        long time1 = System.currentTimeMillis();
        int a = fi.read();
        while (a != -1) {
            a = fi.read();
        }
        long time2 = System.currentTimeMillis();

        a = bi.read();
        while (a != -1) {
            a = bi.read();
        }
        long time3 = System.currentTimeMillis();
        System.out.println("FileInputStream times : " + (time2-time1) + "millis");
        System.out.println("BufferedInputStream times : " + (time3-time2) + "millis");

        FileInputStream File = new FileInputStream("changzhou.txt");
        File.skip(999);
        StringBuilder str1 = new StringBuilder();
        int ch1;
        do {
            ch1 = File.read();
            int ch2;
            if (!Character.isLetter(ch1)) {
                do {
                    ch2 = File.read();
                    if (!Character.isLetter(ch2))
                        break;
                    else
                        str1.append(Character.toChars(ch2));
                } while (true);
            }
        } while (!Character.isLetter(ch1));
        System.out.println("第1000个字节后的第一个完整单词为 : " + str1.toString());
    }
}