package connnector;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.Socket;

/**
 * Description:
 */
public class TestClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 30000);
        String requestContent = "GET /index.html HTTP/1.1";

        OutputStream output = socket.getOutputStream();
        output.write(requestContent.getBytes());
        socket.shutdownOutput();

        InputStream input = socket.getInputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream tmp = new ByteArrayOutputStream();
        while ((len = input.read(buffer)) > 0){
            tmp.write(buffer, 0, len);
        }
        socket.shutdownInput();
        //System.out.println(len);
        System.out.println(tmp.toString());

        socket.close();
    }
}
