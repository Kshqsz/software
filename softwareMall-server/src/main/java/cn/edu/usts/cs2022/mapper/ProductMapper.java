package cn.edu.usts.cs2022.mapper;

import cn.edu.usts.cs2022.pojo.po.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {
    void add(Product product);

    @Select("select * from product")
    List<Product> getAllProduct();

    @Select("select * from product where merchant_id = #{merchantId}")
    List<Product> getAllByMerchantId(Integer merchantId);
}
