package nio.simpleNetty;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Description: 描述
 * @Author: zhangwei
 * @Date: 2020/7/17 17:52
 */
public class ClientReadHandler implements Handler {

    private SocketChannel key;

    ClientReadHandler(SocketChannel server){this.key = server;}

    @Override
    public void doRead() {
        //todo client read
        ByteBuffer data = ByteBuffer.allocateDirect(4096);
        try {
            key.read(data);
            data.flip();
            byte[] dByte = new byte[data.limit()];
            data.get(dByte);
            System.out.println(new String(dByte));
            data.clear();

            for(int i = 0; i < 10;i++ ){
                data.put("a".getBytes());
                data.flip();
                key.write(data);
                data.clear();
            }

        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
