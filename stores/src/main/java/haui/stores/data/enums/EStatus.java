package haui.stores.data.enums;

import lombok.Getter;

import java.util.EnumSet;

/**
 * The status of status active or deleted
 */
@Getter
public enum EStatus {
    ACTIVE(0, "Active"),
    DELETE(1, "DELETED");

    private int code;

    private String status;

    EStatus(int code, String status) {
        this.code = code;
        this.status = status;
    }

    public static EStatus statusOfCode(int code) {
        return EnumSet.allOf(EStatus.class).stream()
                .filter(eStatus -> eStatus.getCode() == code)
                .findFirst()
                .orElse(null);
    }

    public static EStatus statusOfText(String text) {
        return EnumSet.allOf(EStatus.class).stream()
                .filter(eStatus -> eStatus.getStatus().equals(text))
                .findFirst()
                .orElse(null);

    }

}
