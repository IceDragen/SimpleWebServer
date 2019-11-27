package connnector;

import java.io.IOException;
import java.io.InputStream;

/**
 * Description: 请求的封装类
 *
 *  精简版http请求报文内容：
 *      POST /index.html HTTP/1.1
 *      HOST: www.XXX.com
 *      User-Agent: Mozilla/5.0(Windows NT 6.1;rv:15.0) Firefox/15.0
 *
 */
public class Request {
    private static final int BUFFER_SIZE = 1024;
    //TODO 补注释
    private InputStream input;
    private String uri;

    public String getUri() {
        return uri;
    }


    public Request(InputStream input) {
        this.input = input;
    }

    public void parse(){
        int length = 0;
        //1M应该够大了，所以后面只读了一次就认为读到了所有数据
        byte[] buffer = new byte[BUFFER_SIZE];
        try {
            length = input.read(buffer);
            String request = new String(buffer, 0, length);
            uri = parseUri(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String parseUri(String request) {
        int index1 = request.indexOf(' '), index2 = -1;
        if (index1 >= 0){
            index2 = request.indexOf(' ', index1 + 1);
            if (index1 < index2){
                return request.substring(index1 + 2, index2);
            }
        }
        return "";
    }
}
