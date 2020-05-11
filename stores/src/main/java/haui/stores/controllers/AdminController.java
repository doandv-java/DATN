package haui.stores.controllers;

import haui.stores.data.User;
import haui.stores.services.CommonService;
import haui.stores.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Admin controller
 */
@Controller
@RequestMapping(path = "/admin")
public class AdminController {
    @Autowired
    private CommonService commonService;

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    @ResponseBody
    public Map<String, Object> viewHomePage() {
        Map<String, Object> map = new HashMap();
        User user = commonService.loadUserDetail();
        map.put("user", user);
        return map;
    }
}
