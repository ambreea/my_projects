package org.andreea.springmvcshoppingcart.dao;

import org.andreea.springmvcshoppingcart.model.CartInfo;
import org.andreea.springmvcshoppingcart.model.OrderDetailInfo;
import org.andreea.springmvcshoppingcart.model.OrderInfo;
import org.andreea.springmvcshoppingcart.model.PaginationResult;

import java.util.List;

public interface OrderDAO {

    public void saveOrder(CartInfo cartInfo);

    public PaginationResult<OrderInfo> listOrderInfo(int page,
                                                     int maxResult, int maxNavigationPage);

    public OrderInfo getOrderInfo(String orderId);

    public List<OrderDetailInfo> listOrderDetailInfos(String orderId);

}