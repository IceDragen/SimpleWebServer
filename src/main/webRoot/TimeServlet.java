import connnector.ConnectorUtils;
import connnector.HttpStatus;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Description: 这个servlet的功能是收到请求就返回服务器当前时间
 *
 * 由于开发Servlet的人和开发服务器的人一般不属于一个团队，即Servlet是由服务器用户自己开发的，所以不把Servlet和服务器的代码放在一个目录下，
 * 服务器会用过反射来加载Servlet
 */
public class TimeServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        PrintWriter writer = servletResponse.getWriter();
        writer.println(ConnectorUtils.renderStatus(HttpStatus.SC_OK));
        writer.println("What time is it now ?");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = formatter.format(LocalDateTime.now());
        writer.println(time);

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }


}
