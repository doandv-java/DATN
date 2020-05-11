package haui.stores.services;

import haui.stores.data.User;
import haui.stores.dto.errors.ErrorValidation;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface CommonService {

    User loadUserDetail();

    List<ErrorValidation> bindResult(BindingResult result);
}
