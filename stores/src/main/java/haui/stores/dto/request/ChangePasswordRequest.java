package haui.stores.dto.request;

import haui.stores.dto.dxo.ChangePasswordDxo;
import haui.stores.validations.annotaions.Equals;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@Equals(source = "passwordNew", target = "confirmPassword", message = "Mật khẩu xác nhận chưa khớp")
public class ChangePasswordRequest {
    @NotBlank(message = "Bạn chưa nhập mật khẩu cũ")
    private String passwordOld;
    @NotBlank(message = "Bạn chưa nhập mật khẩu mới")
    private String passwordNew;

    @NotBlank(message = "Bạn chưa nhập xác nhận mật khẩu")
    private String confirmPassword;

    public ChangePasswordDxo toDxo() {
        ChangePasswordDxo dxo = new ChangePasswordDxo();
        BeanUtils.copyProperties(this, dxo);
        return dxo;
    }
}
