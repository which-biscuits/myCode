import java.io.*;

public class subject1 {
    public static void main(String[] args) throws IOException {
        DataOutputStream out_dat = null;
        DataInputStream in_dat = null;
        PrintStream out_txt = null;
        try {
            out_dat = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("data.dat")));
            out_txt = new PrintStream(new BufferedOutputStream(new FileOutputStream("data.txt")));
            for (int i = 1; i <= 9; i++) {
                for (int j = 1; j <= i; j++) {
                    out_dat.writeUTF(i + " * " + j + "= " + i*j);
                    out_txt.print(i + " * " + j + "= " + i*j);
                }
                out_dat.writeUTF("\n");
                out_txt.print("\n");
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (out_dat != null) out_dat.close();
                if (out_txt != null) out_txt.close();
            } catch (IOException f) {
                System.out.println(f);
            }
        }

        try {
            in_dat = new DataInputStream(new BufferedInputStream(new FileInputStream("data.dat")));
            for (int i = 1; i <= 9; i++) {
                for (int j = 1; j <= i; j++) {
                    System.out.print(in_dat.readUTF() + "\t");
                }
                in_dat.readUTF();
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (in_dat != null) in_dat.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
// P1.	编写如下程序。
//a)	用循环语句和DataOutputStream类，将99乘法表数据存入指定的文件。
//	（注：需整理格式）
//b)	用DataInputStream打开该文件，显示所有数据。
