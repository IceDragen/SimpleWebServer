package processor;

import connnector.Request;
import org.junit.Assert;
import org.junit.Test;
import util.TestUtils;

import javax.servlet.Servlet;

import java.net.MalformedURLException;

import static org.junit.Assert.*;

/**
 * Description:
 */
public class DynamicProcessorTest {

    private Request request = TestUtils.createRequest("GET /servlet/TimeServlet HTTP/1.1");
    private DynamicProcessor processor = new DynamicProcessor();

    @Test
    public void getServletTest() throws ClassNotFoundException,
            MalformedURLException, InstantiationException, IllegalAccessException {
        Servlet servlet = processor.getServlet(request);

        Assert.assertEquals("TimeServlet", servlet.getClass().getSimpleName());
    }

}