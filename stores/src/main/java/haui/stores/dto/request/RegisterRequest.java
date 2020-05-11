package haui.stores.dto.request;

import haui.stores.dto.dxo.RegisterDxo;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterRequest {

    @NotBlank(message = "{register.name.notBlank}")
    private String userName;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    public RegisterDxo toDxo() {
        RegisterDxo dxo = new RegisterDxo();
        dxo.setUserName(StringUtils.trimToEmpty(userName));
        dxo.setPassword(password);
        dxo.setName(StringUtils.trimToEmpty(name));
        return dxo;
    }
}
