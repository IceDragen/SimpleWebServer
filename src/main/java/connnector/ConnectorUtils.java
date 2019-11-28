package connnector;

import java.io.File;

/**
 * Description:
 */
public class ConnectorUtils {

    private static final String PROTOCOL = "HTTP/1.1";

    private static final String CARRIAGE = "\r";

    private static final String NEWLINE = "\n";

    private static final String SPACE = " ";

    public static final String WEB_ROOT =
            System.getProperty("user.dir") + File.separator  + "webroot";

    public static String renderStatus(HttpStatus status) {
        StringBuilder sb = new StringBuilder(PROTOCOL)
                .append(SPACE)
                .append(status.getStatusCode())
                .append(SPACE)
                .append(status.getReason())
                .append(CARRIAGE).append(NEWLINE)
                .append(CARRIAGE).append(NEWLINE);

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
    }
}
