package assignment.virtualmedicalhome.vmh.response;

public class UnauthorizedException extends SecurityException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
