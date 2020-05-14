package haui.stores.services;

import haui.stores.data.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> findOrderDetailsOfCart();
}
