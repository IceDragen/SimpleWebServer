import connnector.Connector;

/**
 * Description:
 */
public class Bootstrap {
    public static void main(String[] args) {
        Connector connector = new Connector();
        connector.start();
    }
}
