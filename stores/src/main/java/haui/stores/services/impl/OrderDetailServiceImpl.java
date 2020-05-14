package haui.stores.services.impl;

import haui.stores.data.OrderDetail;
import haui.stores.services.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {


    @Override
    public List<OrderDetail> findOrderDetailsOfCart() {
        return null;
    }
}
