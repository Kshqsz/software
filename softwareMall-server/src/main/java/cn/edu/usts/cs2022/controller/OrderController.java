package cn.edu.usts.cs2022.controller;

import cn.edu.usts.cs2022.pojo.dto.OrderDTO;
import cn.edu.usts.cs2022.pojo.po.Order;
import cn.edu.usts.cs2022.pojo.po.Result;
import cn.edu.usts.cs2022.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/add")
    public Result<Order> add(@RequestBody OrderDTO orderDTO) {
        Order order =  orderService.add(orderDTO);
        return Result.success(order);
    }

    @PostMapping("/cancel/{number}")
    public Result cancel(@PathVariable("number") String orderNumber) {
        orderService.cancel(orderNumber);
        return Result.success();
    }
}
