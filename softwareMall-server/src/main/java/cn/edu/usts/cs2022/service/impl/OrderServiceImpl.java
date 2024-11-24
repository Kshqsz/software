package cn.edu.usts.cs2022.service.impl;

import cn.edu.usts.cs2022.mapper.OrderMapper;
import cn.edu.usts.cs2022.pojo.dto.OrderDTO;
import cn.edu.usts.cs2022.pojo.po.Order;
import cn.edu.usts.cs2022.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    @Override
    public Order add(OrderDTO orderDTO) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        String orderNumber = String.valueOf(System.currentTimeMillis());
        order.setOrderNumber(orderNumber);
        order.setStatus(0);
        order.setCreateTime(LocalDateTime.now());
        orderMapper.add(order);

        System.out.println(order.getId());
        return order;
    }

    @Override
    public void cancel(String orderNumber) {
        orderMapper.cancel(orderNumber);
    }
}
