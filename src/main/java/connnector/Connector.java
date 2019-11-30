package connnector;

import processor.DynamicResourceProcessor;
import processor.Processor;
import processor.StaticResourceProcessor;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * Description:
 */
public class Connector implements Runnable{
    private static final int DEFAULT_PORT = 30000;

    private ServerSocketChannel server;
    private Selector selector;

    private int port;

    public Connector() {
        this(DEFAULT_PORT);
    }

    public Connector(int port) {
        this.port = port;
    }

    public void start(){
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
           server = ServerSocketChannel.open();
           server.configureBlocking(false);
           server.socket().bind(new InetSocketAddress(port));


           selector = Selector.open();
           server.register(selector, SelectionKey.OP_ACCEPT);
           System.out.println(String.format("服务器已启动，正在监听端口[%d]", port));

           while (true){
                selector.select();
               Set<SelectionKey> selectionKeys = selector.selectedKeys();
               selectionKeys.forEach(key -> {
                   try {
                       handles(key);
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               });
               selectionKeys.clear();
           }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close(server);
        }
    }

    private void handles(SelectionKey key) throws IOException {
        if (key.isAcceptable()){
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel client = server.accept();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
        }else if (key.isReadable()){
            SocketChannel client = (SocketChannel) key.channel();
            /*
            这里使用key.cancel主要出于两点考虑：
            1。因为后面要拿到socket的getInputStream和getOutputStream都是阻塞式方法，如果不cancel就会报错，即在Selector中注册的
                socket全部都要是非阻塞的。这一点后面有空可以再考虑下看看能不能用buffer替代iostream
            2。说来凑巧，因为我们这个客户连接是一次性的，即处理完用户请求之后就要关闭，不会像聊天室那样需要一直保持，所以也需要调用key.cancel()
             */
            key.cancel();
            client.configureBlocking(true);

            Socket socket = client.socket();

            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            Request request = new Request(input);
            request.parse();

            Response response = new Response(request, output);

            Processor processor;

            if (request.getUri().startsWith("servlet/")){
                processor = new DynamicResourceProcessor();

            }else {
                processor = new StaticResourceProcessor();
            }
            processor.process(request, response);

            close(client);
        }
    }

    public void close(Closeable closeable){
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
