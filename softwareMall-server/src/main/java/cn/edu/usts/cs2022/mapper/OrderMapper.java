package cn.edu.usts.cs2022.mapper;

import cn.edu.usts.cs2022.pojo.po.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {

    /**
     * 查询所有订单信息
     * @return
     */
    @Select("SELECT * from `order` ")
    List<Order> selectAllOrder();
}
