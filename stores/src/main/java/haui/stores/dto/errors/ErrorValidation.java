package haui.stores.dto.errors;

import lombok.Data;

@Data
public class ErrorValidation {
    private String field;
    private String message;

    public ErrorValidation(String field, String message) {
        this.field = field;
        this.message = message;
    }

}
