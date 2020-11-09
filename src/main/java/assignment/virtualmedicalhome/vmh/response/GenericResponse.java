package assignment.virtualmedicalhome.vmh.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

public class GenericResponse {
    public static final String STATUS_OK = "OK";
    public static final String STATUS_ERROR = "ERROR";

    private String status;
    private String error;
    private Object result;

    private GenericResponse() {
    }

    private static GenericResponse getSuccess(@NonNull Object object) {
        return new Builder()
                .setStatus(GenericResponse.STATUS_OK)
                .setResult(object);
    }

    private static GenericResponse getFailure(@NonNull String error) {
        return new Builder()
                .setStatus(GenericResponse.STATUS_ERROR)
                .setError(error);
    }

    public static ResponseEntity<GenericResponse> getSuccessResponse(@NonNull Object object) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(getSuccess(object));
    }

    public static ResponseEntity<GenericResponse> getFailureResponse(@NonNull String error, HttpStatus status) {
        return ResponseEntity.status(status)
                .body(getFailure(error));
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public static class Builder {
        GenericResponse response;

        public Builder() {
            this.response = new GenericResponse();
        }

        public Builder setStatus(@NonNull String status) {
            if (!status.equals(STATUS_OK) && !status.equals(STATUS_ERROR)) {
                throw new UnsupportedOperationException("Status " + status + " not supported!");
            }
            response.setStatus(status);
            return this;
        }

        public GenericResponse setError(@NonNull String error) {
            response.error = error;
            return this.build();
        }

        public GenericResponse setResult(@NonNull Object result) {
            response.result = result;
            return this.build();
        }

        private GenericResponse build() {
            if (response.status == null ||
                    (response.status.equals(STATUS_ERROR) && response.error == null) ||
                    (response.status.equals(STATUS_OK) && response.result == null)) {
                throw new IllegalStateException("All necessary fields are not set!");
            }
            return response;
        }
    }
}
