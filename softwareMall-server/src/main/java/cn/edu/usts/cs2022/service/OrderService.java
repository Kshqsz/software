package cn.edu.usts.cs2022.service;

import cn.edu.usts.cs2022.pojo.po.Order;

import java.util.List;

public interface OrderService {
    /**
     * 查询所有订单信息
     * @return
     */
    List<Order> selectAllOrder();

    Order add(OrderDTO orderDTO);

    void cancel(String orderNumber);
}
