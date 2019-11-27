package connnector;

import jdk.internal.util.xml.impl.Input;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

/**
 * Description: 服务器应答封装类
 */

public class Response {
    private static final int BUFFER_SIZE = 1024;

    private Request request;
    private OutputStream output;


    public Response(Request request, OutputStream output) {
        this.request = request;
        this.output = output;
    }

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {
        try(InputStream resource = Response.class.getClassLoader().getResourceAsStream(request.getUri());) {
            if (resource != null){
                write(resource, HttpStatus.SC_OK);
            }else {
                try (InputStream missPage = Response.class.getClassLoader().getResourceAsStream("404.html")){
                    assert missPage != null;
                    write(missPage, HttpStatus.SC_NOT_FOUND);
                }
            }
        } catch (IOException e){
            //TODO 这里怎么处理能更优雅，不要抛出IOException
            write(HttpStatus.SC_INTERNAL_ERROR);
        }
    }

    private void write(InputStream resource, HttpStatus status) throws IOException {
        //先写入response头部
        output.write(ConnectorUtils.renderStatus(status).getBytes());
        //再写入资源内容
        byte[] buffer = new byte[BUFFER_SIZE];
        int len = 0;
        while ((len = resource.read(buffer)) > 0){

            output.write(buffer, 0, len);
        }

    }

    private void write(HttpStatus status) throws IOException {
        output.write(ConnectorUtils.renderStatus(status).getBytes());
    }
}
