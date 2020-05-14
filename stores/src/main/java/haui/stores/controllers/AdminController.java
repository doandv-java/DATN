package haui.stores.controllers;

import haui.stores.data.User;
import haui.stores.dto.errors.ErrorValidation;
import haui.stores.dto.request.SupplyRequest;
import haui.stores.dto.rst.SupplyRst;
import haui.stores.framework.Constants;
import haui.stores.services.CommonService;
import haui.stores.services.SupplyService;
import haui.stores.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private SupplyService supplyService;

    @GetMapping("/home")
    @ResponseBody
    public Map<String, Object> viewHomePage() {
        Map<String, Object> map = new HashMap();
        User user = commonService.loadUserDetail();
        map.put("user", user);
        return map;
    }

    @GetMapping("/supply")
    public ModelAndView supplyAdminHome() {
        ModelAndView mav = new ModelAndView();
        User user = commonService.loadUserDetail();
        if (user == null) {
            mav.setViewName("redirect:/login");
        } else {
            List<SupplyRst> supplies = supplyService.getListSupply(Constants.DELETE.FALSE);
            mav.addObject("supplies", supplies);
            mav.addObject("user", user);
            mav.setViewName("/admin/supply/list");
        }
        return mav;
    }

    @PostMapping("/supply")
    @ResponseBody
    public Map<String, Object> saveSupply(@Valid @RequestBody SupplyRequest request, BindingResult result) {
        Map<String, Object> map = new HashMap<>();
        List<ErrorValidation> errors = commonService.bindResult(result);
        if (errors.isEmpty()) {
            if (supplyService.saveSupply(request.toDxo())) {
                map.put("status", 200);
            } else {
                map.put("status", 101);
                map.put("error", "Supply is exists");
            }
        } else {
            map.put("status", 101);
            map.put("error", errors);
        }
        return map;
    }

    @DeleteMapping("/supply/{id}")
    public Map<String, Object> deleteSupply(@PathVariable("id") Long id) {
        Map<String, Object> map = new HashMap<>();
        User user = commonService.loadUserDetail();
        if (user == null) {
            map.put("status", false);
            map.put("error", "User not login");
        } else {
            map.put("status", supplyService.deleteSupply(id));
        }
        return map;
    }
}
