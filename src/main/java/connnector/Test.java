package connnector;

import java.io.IOException;
import java.io.InputStream;

/**
 * Description:
 */
public class Test {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("index.html");
        byte[] buffer = new byte[1024];
        int len = inputStream.read(buffer);
        System.out.println(new String(buffer, 0, len));
    }
}
