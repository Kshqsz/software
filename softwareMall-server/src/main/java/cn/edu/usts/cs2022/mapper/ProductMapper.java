package cn.edu.usts.cs2022.mapper;

import cn.edu.usts.cs2022.pojo.po.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    void add(Product product);
}
