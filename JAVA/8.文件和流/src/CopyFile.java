import java.io.*;
import java.util.Scanner;

public class CopyFile {
    public static void main(String[] args) throws IOException {
        FileInputStream inFile;
        FileOutputStream outFile;
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入源文件名称:");
        File fromFile = new File(scanner.nextLine());
        if (! fromFile.exists()) {
            System.out.println("源文件不存在!");
            System.exit(0);
        }
        System.out.println("请输入目标文件名称:");
        File toFile = new File(scanner.nextLine());
        if (toFile.exists()) {
            System.out.println("目标文件已存在!");
            System.exit(0);
        }
        inFile = new FileInputStream(fromFile);
        outFile = new FileOutputStream(toFile);

        System.out.println("文件" + fromFile.getName() + "有" + inFile.available() + "个字节");
        int ch;
        while ((ch = inFile.read()) != -1) {
            outFile.write(ch);
        }
        inFile.close();
        outFile.close();
        System.out.println("复制完成!");

    }
}
