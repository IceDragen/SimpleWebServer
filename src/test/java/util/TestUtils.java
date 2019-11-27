package util;

import connnector.Request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Description:
 */
public class TestUtils {
    public static Request createRequest(String requestStr) {
        InputStream input = new ByteArrayInputStream(requestStr.getBytes());
        Request request = new Request(input);
        request.parse();
        return request;
    }

    public static String readFileToString(InputStream input) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        StringBuilder builder = new StringBuilder();
        while ((len = input.read(buffer)) > 0){
            builder.append(new String(buffer, 0, len));
        }
        return builder.toString();
    }
}
