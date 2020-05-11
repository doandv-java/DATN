package haui.stores.dto.dxo;

import lombok.Data;

@Data
public class ChangePasswordDxo {

    private String passwordOld;

    private String passwordNew;
}
