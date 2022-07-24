package question02;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author 11412
 * 使用TCP协议实现客户端和服务端互相发送消息。
 * 客户端发送一条消息，服务器读取之后，返回一条消息给客户端，客户端读取服务器返回的消息之后，又发送一条
 * 消息…循环执行，直到输入exit。
 * 1. 消息内容通过键盘输入
 * 2. 要求服务器端和客户端都使用字符流的方式发送和接收数据。
 * 使用BufferedReader方法readLine()读取一行数据。
 * 使用BufferedWriter方法write("字符串的数据")发送数据。
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        boolean reading = true;
        while (true) {
            if (reading) {
                String text = reader.readLine();
                System.out.println("客户端消息：" + text);
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
