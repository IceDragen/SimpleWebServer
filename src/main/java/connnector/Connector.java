package connnector;

import processor.Processor;
import processor.StaticProcessor;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Description:
 */
public class Connector implements Runnable{
    private static final int DEFAULT_PORT = 30000;

    private ServerSocket server;
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
            server = new ServerSocket(port);
            System.out.println(String.format("服务器已启动，正在监听端口[%d]", port));
            while (true){
                Socket client = server.accept();
                System.out.println(client.getPort() + "已连接");
                InputStream input = client.getInputStream();
                OutputStream output = client.getOutputStream();

                Request request = new Request(input);
                request.parse();

                Response response = new Response(request, output);

                Processor processor = new StaticProcessor();
                processor.process(request, response);

                close(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close(server);
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
