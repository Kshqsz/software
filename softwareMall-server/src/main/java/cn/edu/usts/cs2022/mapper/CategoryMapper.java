package cn.edu.usts.cs2022.mapper;

import cn.edu.usts.cs2022.pojo.po.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Insert("insert into category(name) values (#{name})")
    void add(String name);

    @Select("select * from category")
    List<Category> getAllCategory();
}
