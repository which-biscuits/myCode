import java.io.*;

public class ArrayIO {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int[] array1 = {1,2,3,4};
        Num[] array2 = {new Num(10), new Num(20), new Num(30)};
        ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("Array.dat")));
        out.writeObject(array1);
        out.writeObject(array2);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("Array.dat")));
        int[] readArray1 = (int[]) in.readObject();
        Num[] readArray2 = (Num[]) in.readObject();

        for (int i:readArray1) {
            System.out.println(i + " ");
        }
        System.out.println();
        for (Num i:readArray2) {
            System.out.println(i + " ");
        }
        System.out.println();
        in.close();

    }
}

class Num implements java.io.Serializable {
    private int i;
    public Num(int n) {
        this.i = n;
    }
    public String toString() {
        return Integer.toString(i);
    }
}
