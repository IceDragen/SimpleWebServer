package processor;

import connnector.*;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Description: 动态资源处理器
 */
public class DynamicResourceProcessor extends Processor{

    @Override
    public void process(Request request, Response response) {
        try {
            URLClassLoader servletLoader = getServletLoader();
            Servlet servlet = getServlet(servletLoader, request);
            RequestFacade requestFacade = new RequestFacade(request);
            ResponseFacade responseFacade = new ResponseFacade(response);
            servlet.service(requestFacade, responseFacade);
        } catch (InstantiationException | IllegalAccessException
                | ClassNotFoundException | ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    //通过URLClassLoader来加载对应的Servlet类
    private URLClassLoader getServletLoader() throws MalformedURLException {
        File webRoot = new File(ConnectorUtils.WEB_ROOT);
        URL webRootUrl = webRoot.toURI().toURL();

        return new URLClassLoader(new URL[]{webRootUrl});
    }

    private Servlet getServlet(URLClassLoader loader, Request request) throws ClassNotFoundException,
            IllegalAccessException, InstantiationException {
        /*
        通过request拿到的url是这样的：/servlet/TimeServlet，所以要进一步处理
        之所以是这个路径是因为默认访问某个Servlet的规范就是这样的。
         */
        String url = request.getUri();
        String servletName = url.substring(url.lastIndexOf("/") + 1);
        //URLClassLoader会在给定的目录中去搜索这个类
        Class servletClass = loader.loadClass(servletName);

        return (Servlet) servletClass.newInstance();
    }

    Servlet getServlet(Request request) throws MalformedURLException, IllegalAccessException,
            InstantiationException, ClassNotFoundException {
        URLClassLoader servletLoader = getServletLoader();

        return getServlet(servletLoader, request);
    }
}
