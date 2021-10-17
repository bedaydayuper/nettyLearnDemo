package bio;

import java.net.Socket;

/**
 * @author zhangpeng
 * 客户端可以同时启动多个的方法：https://blog.csdn.net/wait_for_eva/article/details/86557930
 * @Date 2021/10/17
 */
public class BioClient {

    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("localhost", 9000);
        socket.getOutputStream().write("Hello server".getBytes());
        socket.getOutputStream().flush();
        System.out.println("向服务端发送 结束");
        byte[] bytes = new byte[1024];
        socket.getInputStream().read(bytes);
        System.out.println("接收到服务端的数据：" + new String(bytes));
        socket.close();
    }
}
