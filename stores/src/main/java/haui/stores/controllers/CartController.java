package haui.stores.controllers;

import haui.stores.data.User;
import haui.stores.services.CommonService;
import haui.stores.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/cart")
public class CartController {

    @Autowired
    private CommonService commonService;

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("")
    public ModelAndView viewCart() {
        //check User
        ModelAndView mav = new ModelAndView();
        User user = commonService.loadUserDetail();
        if (user == null) {
            mav.setViewName("redirect:/login");
        } else {
            mav.setViewName("cart");
        }
        return mav;
    }

}
