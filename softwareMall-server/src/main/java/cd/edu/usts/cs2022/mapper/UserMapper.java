package cd.edu.usts.cs2022.mapper;

import cd.edu.usts.cs2022.pojo.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where username = #{username} and password = #{password}")
    User login(@Param("username") String username,
               @Param("password") String password);
}
