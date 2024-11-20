package cn.edu.usts.cs2022.mapper;


import cn.edu.usts.cs2022.pojo.po.Admin;
import cn.edu.usts.cs2022.pojo.po.Category;
import cn.edu.usts.cs2022.pojo.po.Merchant;
import cn.edu.usts.cs2022.pojo.po.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Select("select * from admin where username = #{username} and password = #{password}")
    Admin login(@Param("username") String username,
                @Param("password") String password);

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

    /**
     * 查询所有商家
     * @return
     */
    @Select("select * from merchant")
    List<Merchant> selectMerchant();

    /**
     * 查询所有分类
     * @return
     */
    @Select("select * from category")
    List<Category> selectCategory();

    /**
     * 新增分类
     * @param category
     */
    @Insert("insert into category (name) values (#{name})")
    void insertCategory(Category category);

    /**
     * 删除分类
     * @param id
     */
    @Delete("delete from category where id = #{id}")
    void deleteCategory(Integer id);

    /**
     * 编辑分类
     * @param category
     */
    @Update("update category set name = #{name} where id = #{id}")
    void updatecategory(Category category);
}
