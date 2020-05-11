package haui.stores.data.enums;

import lombok.Getter;

import java.util.EnumSet;

/**
 * The enum of role user
 */
@Getter
public enum ERole {
    USER(0, "ROLE_USER"),
    ADMIN(1, "ROLE_ADMIN");

    private int code;
    private String role;

    ERole(int code, String role) {
        this.code = code;
        this.role = role;
    }

    public static ERole valueOfCode(int code) {
        return EnumSet.allOf(ERole.class).stream()
                .filter(eRole -> eRole.getCode() == code)
                .findFirst()
                .orElse(null);
    }

    public static ERole valueOfText(String text) {
        return EnumSet.allOf(ERole.class).stream()
                .filter(eRole -> eRole.getRole().equals(text))
                .findFirst()
                .orElse(null);
    }

}
