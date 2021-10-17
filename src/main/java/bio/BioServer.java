package bio;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhangpeng
 * @Date 2021/10/17
 */
public class BioServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9000);
        while (true) {
            System.out.println("等待连接。。。。");
            Socket socket = serverSocket.accept();
            System.out.println("客户端连接来了");
            // 方案一、
            // 如果不交给一个新线程去做，则会阻塞在handler 的read 方法这儿。
//            handler(socket);

            // 方案二：将处理逻辑交给一个子线程去做。
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        handler(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();


        }

    }

    private static void handler(Socket socket) throws Exception {
        System.out.println("thread id is " + Thread.currentThread().getId());
        byte[] bytes = new byte[1024];
        System.out.println("client 数据 ready");
        // 注意： read 方法也是阻塞的。
        int read = socket.getInputStream().read(bytes);
        System.out.println("read 完毕");
        if (read != -1) {
            System.out.println("接收到的客户端数据：" + new String(bytes, 0, read));
            System.out.println("thread id is " + Thread.currentThread().getId());
        }
        socket.getOutputStream().write("hello client".getBytes());
        socket.getOutputStream().flush();
    }
}
