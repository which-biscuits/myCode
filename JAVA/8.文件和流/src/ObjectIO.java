import javafx.beans.binding.ObjectExpression;

import java.io.*;
import java.util.Date;

public class ObjectIO {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("Object.bat")));
        out.writeUTF("王子潇");
        out.writeInt(20);
        out.writeObject(new Date());
        out.close();

        ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("Object.bat")));
        String name = in.readUTF();
        int age = in.readInt();
        Date date = (Date) in.readObject();
        System.out.println(name);
        System.out.println(age);
        System.out.println(date);
    }
}
