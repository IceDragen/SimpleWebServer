package processor;

import connnector.Request;
import connnector.Response;

import java.io.IOException;

/**
 * Description:
 */
public class StaticResourceProcessor extends Processor{

    @Override
    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
