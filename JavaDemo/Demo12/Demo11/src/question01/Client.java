package question01;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @author 11412
 * 创建TCP 客户端，访问上一题创建的服务器端,服务器端ip：127.0.0.1 端口号：8888
 * 1: 客户端连接服务器,并发送 hello.服务器,我是客户端。
 * 2: 开启上一题服务器,等待客户端连接,客户端连接并发送数据。
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8888);

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        for (int i = 0; i < 10; i++) {
            bufferedWriter.write("12345456464\n");
            bufferedWriter.flush();
        }
    }
}
