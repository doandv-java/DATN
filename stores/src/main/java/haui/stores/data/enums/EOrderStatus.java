package haui.stores.data.enums;

import lombok.Getter;

import java.util.EnumSet;

@Getter
public enum EOrderStatus {
    CART(0, "CART"),
    ORDER(1, "ORDER"),
    PAYED(2, "PAYED"),
    SUCCESS(3, "SUCESS"),
    DENY(4, "DENY");

    private int code;

    private String text;

    EOrderStatus(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public static EOrderStatus ofCode(int code) {
        return EnumSet.allOf(EOrderStatus.class).stream()
                .filter(eOrderStatus -> eOrderStatus.getCode() == code)
                .findFirst()
                .orElse(null);
    }

    public static EOrderStatus ofText(String text) {
        return EnumSet.allOf(EOrderStatus.class).stream()
                .filter(eOrderStatus -> eOrderStatus.getText().equalsIgnoreCase(text))
                .findFirst()
                .orElse(null);
    }

}
