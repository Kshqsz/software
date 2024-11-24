package cn.edu.usts.cs2022.mapper;


import cn.edu.usts.cs2022.pojo.po.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrderMapper {
    int add(Order order);


    Order getById(Integer id);

    @Update("update `order` set status = -1 where order_number =  #{orderNumber}")
    void cancel(@Param("orderNumber") String orderNumber);
}
