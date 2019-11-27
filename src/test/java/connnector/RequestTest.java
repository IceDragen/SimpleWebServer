package connnector;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Description:
 */
public class RequestTest {
    private String request = "POST /index.html HTTP/1.1";

    @Test
    public void getUri() {
        InputStream input = new ByteArrayInputStream(request.getBytes());
        Request request = new Request(input);
        request.parse();

        assertEquals("index.html", request.getUri());
    }
}