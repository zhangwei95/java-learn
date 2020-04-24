package bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class BIOSocketServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        InputStream in = null;
        try {
            serverSocket = new ServerSocket(18888);
            int recvMsgSize = 0;
            byte[] recvBuf = new byte[1024];
            while (true){
                Socket clientSocket = serverSocket.accept();
                SocketAddress clientAddress = clientSocket.getRemoteSocketAddress();
                System.out.println("Handling client " + clientAddress);
                in = clientSocket.getInputStream();
                while ((recvMsgSize = in.read(recvBuf)) != -1){
                    System.out.println(new String(recvBuf,0,recvMsgSize));
                }

            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try{
                if(serverSocket!=null){
                    serverSocket.close();
                }
                if(in!=null){
                    in.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
