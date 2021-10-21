import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class AccessFile {
    public static void main(String[] args) throws IOException {
        File file = new File("output.dat");
        if (file.exists()) {
            System.out.println("output.dat 已经存在!");
            System.exit(0);
        }

        RandomAccessFile rf = new RandomAccessFile(file, "rw");
        for (int index = 1; index < 8; index++)
            rf.writeInt(index * 11);
        rf.close();
        rf = new RandomAccessFile(file, "rw");
        rf.seek(2 * 4);
        rf.writeInt(100);
        rf.close();
        rf = new RandomAccessFile(file, "r");
        long fl = rf.length();
        while (rf.getFilePointer() < fl)
            System.out.println(rf.readInt() + " ");
        rf.close();
        System.out.println();

//        Scanner scanner = new Scanner(System.in);
//        scanner.useDelimiter("[0-9]");
//        System.out.println(scanner.next());
//        System.out.println(scanner.next());
    }
}
