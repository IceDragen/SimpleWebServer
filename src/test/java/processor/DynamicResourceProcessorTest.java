package processor;

import connnector.Request;
import org.junit.Assert;
import org.junit.Test;
import util.TestUtils;

import javax.servlet.Servlet;

import java.net.MalformedURLException;

/**
 * Description:
 */
public class DynamicResourceProcessorTest {

    private Request request = TestUtils.createRequest("GET /servlet/TimeServlet HTTP/1.1");
    private DynamicResourceProcessor processor = new DynamicResourceProcessor();

    @Test
    public void getServletTest() throws ClassNotFoundException,
            MalformedURLException, InstantiationException, IllegalAccessException {
        Servlet servlet = processor.getServlet(request);

        Assert.assertEquals("TimeServlet", servlet.getClass().getSimpleName());
    }

}