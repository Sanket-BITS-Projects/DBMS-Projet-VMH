package assignment.virtualmedicalhome.vmh.response;

public class InvalidSessionException extends IllegalStateException {
    public InvalidSessionException() {
        super("Invalid session! Login again");
    }
}
