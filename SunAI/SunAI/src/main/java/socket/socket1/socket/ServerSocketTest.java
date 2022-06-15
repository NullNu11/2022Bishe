package socket.socket1.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketTest {

    public static void main(String[] args) {
        try {
            //初始化服务端socket并且绑定9999端口
            ServerSocket serverSocket = new ServerSocket(9999);
            //等待客户端的连接
            Socket socket = serverSocket.accept();
            //获取输入流,并且指定统一的编码格式
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            //通过socket获取字符流
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //读取一行数据
            String str=null;
            //通过while循环不断读取信息，
            while ((str = bufferedReader.readLine()) != null) {
                //输出打印
                System.out.println(str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


//        try {
//            ServerSocket ss = new ServerSocket(9999);
//            System.out.println("启动服务器....");
//            Socket s = ss.accept();
//            System.out.println("客户端:"+s.getInetAddress().getLocalHost()+"已连接到服务器");
//            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
//            //读取客户端发送来的消息
//            String mess = br.readLine();
//            System.out.println("客户端："+mess);
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
//
//            bw=bufferedReader.readLine();
//            bw.write(mess+"\n");
//            bw.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}