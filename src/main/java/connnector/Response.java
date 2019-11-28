package connnector;

import jdk.internal.util.xml.impl.Input;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Optional;

/**
 * Description: 服务器应答封装类
 *
 * 为了实现动态资源处理所以需要实现ServletResponse接口
 */

public class Response implements ServletResponse {
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

    //TODO 为什么处理静态资源的方法放在Response这个类里，不应该放在StaticProcessor里吗
    public void sendStaticResource() throws IOException {
        try {
            if (isValidUri(request.getUri())){
                try(InputStream resource = Response.class.getClassLoader().getResourceAsStream(request.getUri());) {
                    if (resource != null){
                        write(resource, HttpStatus.SC_OK);
                    }else {
                       processFileNotFound();
                    }
                }
            }else {
                processFileNotFound();
            }
        } catch (IOException e) {
            write(HttpStatus.SC_INTERNAL_ERROR);
        }

    }

    private void processFileNotFound() throws IOException {
        try (InputStream missPage = Response.class.getClassLoader().getResourceAsStream("404.html")){
            assert missPage != null;
            write(missPage, HttpStatus.SC_NOT_FOUND);
        }
    }

    private boolean isValidUri(String uri) {
        return !uri.isEmpty();
    }

    private void write(InputStream resource, HttpStatus status) throws IOException {
        //先写入response头部
        output.write(ConnectorUtils.renderStatus(status).getBytes());
        //再写入资源内容
        byte[] buffer = new byte[BUFFER_SIZE];
        int len = 0;
        while ((len = resource.read(buffer)) > 0){
            //System.out.println(len);
            output.write(buffer, 0, len);
        }

    }

    private void write(HttpStatus status) throws IOException {
        output.write(ConnectorUtils.renderStatus(status).getBytes());
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(output, true);
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }


    @Override
    public void setCharacterEncoding(String s) {

    }

    @Override
    public void setContentLength(int i) {

    }

    @Override
    public void setContentType(String s) {

    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale locale) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
