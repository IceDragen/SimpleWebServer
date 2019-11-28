package connnector;

import javax.servlet.*;
import java.io.*;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

/**
 * Description: 请求的封装类
 *
 *  精简版http请求报文内容：
 *      POST /index.html HTTP/1.1
 *      HOST: www.XXX.com
 *      User-Agent: Mozilla/5.0(Windows NT 6.1;rv:15.0) Firefox/15.0
 *  为了实现动态资源处理所以需要实现ServletRequest接口
 */
public class Request implements ServletRequest {
    private static final int BUFFER_SIZE = 1024;
    //客户端socket的inputStream
    private InputStream input;
    private String uri;

    public String getUri() {
        return uri;
    }


    public Request(InputStream input) {
        this.input = input;
    }

    public void parse(){
        int length = -1;

        byte[] buffer = new byte[BUFFER_SIZE];
        try {
            //这是个无限流，不能这么写，否则循环永远不会结束
            //ByteArrayOutputStream tmp = new ByteArrayOutputStream();
            //while ((length = input.read(buffer)) > 0){
            //    System.out.println(length);
            //    tmp.write(buffer, 0, length);
            //}
            length = input.read(buffer);
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < length; i++){
                builder.append((char)buffer[i]);
            }

            //System.out.println(builder.toString());
            uri = parseUri(builder.toString());

            //System.out.println(uri);
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

    @Override
    public Object getAttribute(String s) {
        return null;
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return null;
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public void setCharacterEncoding(String s) throws UnsupportedEncodingException {

    }

    @Override
    public int getContentLength() {
        return 0;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return null;
    }

    @Override
    public String getParameter(String s) {
        return null;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return null;
    }

    @Override
    public String[] getParameterValues(String s) {
        return new String[0];
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return null;
    }

    @Override
    public String getProtocol() {
        return null;
    }

    @Override
    public String getScheme() {
        return null;
    }

    @Override
    public String getServerName() {
        return null;
    }

    @Override
    public int getServerPort() {
        return 0;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return null;
    }

    @Override
    public String getRemoteAddr() {
        return null;
    }

    @Override
    public String getRemoteHost() {
        return null;
    }

    @Override
    public void setAttribute(String s, Object o) {

    }

    @Override
    public void removeAttribute(String s) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }

    @Override
    public Enumeration<Locale> getLocales() {
        return null;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String s) {
        return null;
    }

    @Override
    public String getRealPath(String s) {
        return null;
    }

    @Override
    public int getRemotePort() {
        return 0;
    }

    @Override
    public String getLocalName() {
        return null;
    }

    @Override
    public String getLocalAddr() {
        return null;
    }

    @Override
    public int getLocalPort() {
        return 0;
    }

    @Override
    public ServletContext getServletContext() {
        return null;
    }

    @Override
    public AsyncContext startAsync() throws IllegalStateException {
        return null;
    }

    @Override
    public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
        return null;
    }

    @Override
    public boolean isAsyncStarted() {
        return false;
    }

    @Override
    public boolean isAsyncSupported() {
        return false;
    }

    @Override
    public AsyncContext getAsyncContext() {
        return null;
    }

    @Override
    public DispatcherType getDispatcherType() {
        return null;
    }
}
