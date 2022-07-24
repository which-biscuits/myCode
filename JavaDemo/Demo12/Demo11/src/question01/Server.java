package question01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 11412
 * 在项目下创建TCP 服务器端 端口号为8888
 * 1：等待客户端连接 如果有客户端连接 获取到客户端对象。
 * 2：获取到客户端对象之后读取数据客户端传送数据并输出到控制台。
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        Socket socket = server.accept();
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String text;
        while ((text = reader.readLine()) != null) {
            System.out.println(text);
        }
    }
}
