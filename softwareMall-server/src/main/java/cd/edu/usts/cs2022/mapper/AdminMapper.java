package cd.edu.usts.cs2022.mapper;


import cd.edu.usts.cs2022.pojo.po.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {

    @Select("select * from admin where username = #{username} and password = #{password}")
    Admin login(@Param("username") String username,
                @Param("password") String password);
}
