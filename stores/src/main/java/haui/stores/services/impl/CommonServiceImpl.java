package haui.stores.services.impl;

import haui.stores.data.User;
import haui.stores.data.UserPrincipal;
import haui.stores.dto.errors.ErrorValidation;
import haui.stores.services.CommonService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.persistence.criteria.CommonAbstractCriteria;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {

    @Override
    public User loadUserDetail() {
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (o.equals("anonymousUser")) {
            return null;
        }
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserPrincipal) userDetails).getUser();
    }

    @Override
    public List<ErrorValidation> bindResult(BindingResult result) {
        List<ErrorValidation> errorValidators = new ArrayList<>();
        if (result.hasErrors()) {
            result.getFieldErrors().forEach(fieldError -> {
                errorValidators.add(new ErrorValidation(fieldError.getField(), fieldError.getDefaultMessage()));
            });
        }
        return errorValidators;
    }
}
