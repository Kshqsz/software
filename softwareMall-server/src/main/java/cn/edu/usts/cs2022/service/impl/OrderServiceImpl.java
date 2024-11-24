package cn.edu.usts.cs2022.service.impl;

import cn.edu.usts.cs2022.mapper.OrderMapper;
import cn.edu.usts.cs2022.pojo.po.Order;
import cn.edu.usts.cs2022.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    /**
     * 查询所有订单信息
     * @return
     */
    @Override
    public List<Order> selectAllOrder() {
        return orderMapper.selectAllOrder();
    }
}
