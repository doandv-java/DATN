package haui.stores.dto.request;

import haui.stores.dto.dxo.ForgotPasswordDxo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
@AllArgsConstructor
public class ForgotPasswordRequest {

    private String userName;

    public ForgotPasswordDxo toDxo() {
        ForgotPasswordDxo dxo = new ForgotPasswordDxo();
        dxo.setUserName(StringUtils.trimToEmpty(userName));
        return dxo;
    }
}
