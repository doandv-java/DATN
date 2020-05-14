package haui.stores.services.impl;

import haui.stores.data.Order;
import haui.stores.repositories.OrderRepository;
import haui.stores.services.CommonService;
import haui.stores.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CommonService commonService;

    @Autowired
    private OrderRepository orderDao;

    @Override
    public Order findCart() {
        return null;
    }
}
