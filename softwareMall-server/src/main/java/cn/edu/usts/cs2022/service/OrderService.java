package cn.edu.usts.cs2022.service;

import cn.edu.usts.cs2022.pojo.dto.OrderDTO;
import cn.edu.usts.cs2022.pojo.po.Order;

public interface OrderService {
    Order add(OrderDTO orderDTO);

    void cancel(String orderNumber);
}
