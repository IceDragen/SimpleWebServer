package connnector;

/**
 * Description: 请求状态码
 */
public enum  HttpStatus {
    SC_OK(200, "OK"),
    SC_NOT_FOUND(404, "File Not Found"),
    SC_INTERNAL_ERROR(500, "internal error");

    private int statusCode;
    private String reason;

    HttpStatus(int statusCode, String reason) {
        this.statusCode = statusCode;
        this.reason = reason;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getReason() {
        return reason;
    }

}
