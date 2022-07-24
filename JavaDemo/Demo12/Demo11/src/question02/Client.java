package question02;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author 11412
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8888);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        boolean reading = false;
        while (true) {
            if (reading) {
                String text = reader.readLine();
                System.out.println("服务器消息：" + text);
            } else {
                System.out.print("请输入消息：");
                Scanner scanner = new Scanner(System.in);
                writer.write(scanner.nextLine() + "\n");
                writer.flush();
            }
            reading = !reading;
        }
    }
}
