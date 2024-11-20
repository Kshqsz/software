package cn.edu.usts.cs2022.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    @Insert("insert into category(name) values (#{name})")
    void add(String name);
}
