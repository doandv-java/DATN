package haui.stores.controllers;

import haui.stores.data.User;
import haui.stores.dto.errors.ErrorService;
import haui.stores.dto.errors.ErrorValidation;
import haui.stores.dto.request.ChangePasswordRequest;
import haui.stores.dto.request.ForgotPasswordRequest;
import haui.stores.dto.request.RegisterRequest;
import haui.stores.services.CommonService;
import haui.stores.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private CommonService commonService;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ModelAndView loginPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @GetMapping("/checkUser")
    public ModelAndView checkUser() {
        ModelAndView mav = new ModelAndView();
        User user = commonService.loadUserDetail();
        if (user != null) {
            if (user.getRole().equals("ROLE_USER")) {
                mav.setViewName("redirect:/home");
            } else {
                mav.setViewName("redirect:/admin/home");
            }
        } else {
            mav.setViewName("redirect:/home");
        }
        return mav;
    }

    @GetMapping("/register")
    public ModelAndView registerPage() {
        ModelAndView mav = new ModelAndView();
        RegisterRequest register = new RegisterRequest();
        mav.setViewName("register");
        mav.addObject("register", register);
        return mav;
    }


    @PostMapping("/register")
    @ResponseBody
    public Map<String, Object> acceptRegisterForm(@Valid RegisterRequest request, BindingResult result) {
        Map<String, Object> map = new HashMap<>();
        if (result.hasErrors()) {
            List<ErrorValidation> errors = commonService.bindResult(result);
            map.put("status", 101);
            map.put("errors", errors);
        } else {
            boolean register = userService.registerUser(request.toDxo());
            if (!register) {
                map.put("status", 101);
                map.put("error", new ErrorService("userName", "Email đã tồn tại"));
            } else {
                map.put("status", 200);
            }
        }
        return map;
    }

    @PostMapping("/forgot-password")
    @ResponseBody
    public Boolean forgotPassword(String userName) {
        ForgotPasswordRequest request = new ForgotPasswordRequest(userName);
        boolean result = userService.forgotPassword(request.toDxo());
        return result;
    }

    @PostMapping("/change-password")
    @ResponseBody
    public Map<String, Object> changePassword(@Valid @RequestBody ChangePasswordRequest request, BindingResult result) {
        Map<String, Object> map = new HashMap<>();
        List<ErrorValidation> errorValidations = commonService.bindResult(result);
        if (!errorValidations.isEmpty()) {
            map.put("status", 101);
            map.put("errors", errorValidations);
        } else {
            boolean change = userService.changePassword(request.toDxo());
            if (change) {
                map.put("status", 200);
            } else {
                map.put("status", 101);
                map.put("errors", Arrays.asList(new ErrorService("passwordOld", "Password old no matches")));
            }
        }
        return map;
    }


}
