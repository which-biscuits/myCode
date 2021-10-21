import java.io.*;

public class DataOutAndPrint {
    public static void main(String[] args) throws IOException {
        int num,sum = 0;
        DataInputStream din = null;
        DataOutputStream dout = null;
        PrintStream pout = null;
        
        try {
            dout = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("Data.txt")));
            for (int index = 1; index < 8; index++) {
                dout.writeInt(index * 11);
            }
            dout.writeUTF("以上是需要求和的整数");
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (dout != null) {
                    dout.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        
        try {
            din = new DataInputStream(new BufferedInputStream(new FileInputStream("Data.txt")));
            pout = new PrintStream(new BufferedOutputStream(new FileOutputStream("Sum.txt")));
            for (int index = 1; index < 8; index++) {
                num = din.readInt();
                pout.print(num + " ");
                sum += num;
            }
            pout.println();
            pout.println(din.readUTF());
            pout.println("Sum is " + sum);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (din != null) din.close();
                if (pout != null) pout.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
