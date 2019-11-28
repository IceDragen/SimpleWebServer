package processor;

import connnector.Request;
import connnector.Response;

/**
 * Description:
 */
public abstract class Processor {

    abstract public void process(Request request, Response response);
}
