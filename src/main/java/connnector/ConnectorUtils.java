package connnector;

/**
 * Description:
 */
public class ConnectorUtils {

    private static final String PROTOCOL = "HTTP/1.1";

    private static final String CARRIAGE = "\r";

    private static final String NEWLINE = "\n";

    private static final String SPACE = " ";

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
}
