package haui.stores.services;

import haui.stores.dto.dxo.ChangePasswordDxo;
import haui.stores.dto.dxo.ForgotPasswordDxo;
import haui.stores.dto.dxo.RegisterDxo;
import haui.stores.dto.request.ChangePasswordRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    boolean registerUser(RegisterDxo registerDxo);

    boolean forgotPassword(ForgotPasswordDxo dxo);

    boolean changePassword(ChangePasswordDxo dxo);

    boolean existUser(String userName, String userNameOld);
}
