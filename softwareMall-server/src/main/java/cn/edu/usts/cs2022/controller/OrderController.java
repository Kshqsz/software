package cn.edu.usts.cs2022.controller;

import cn.edu.usts.cs2022.pojo.po.Order;
import cn.edu.usts.cs2022.pojo.po.Result;
import cn.edu.usts.cs2022.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * 查询所有订单
     */
    @GetMapping("/all")
    public Result<List<Order>> selectAllOrder(){
        List<Order> list = orderService.selectAllOrder();
        return Result.success(list);
    }
}
