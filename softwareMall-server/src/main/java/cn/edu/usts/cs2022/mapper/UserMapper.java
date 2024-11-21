package cn.edu.usts.cs2022.mapper;

import cn.edu.usts.cs2022.pojo.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user where username = #{username} and password = #{password}")
    User login(@Param("username") String username,
               @Param("password") String password);

    @Select("select * from user where username = #{username}")
    User getUserByUserName(String username);

    void register(User user);

    /**
     * 获取全部用户信息
     * @return
     */
    @Select("select * from user")
    List<User> selectAllUsers();

    /**
     * 修改用户状态
     * @param user
     */
    @Update("UPDATE user set status = #{status} where id = #{id}")
    void changeStatus(User user);
}
