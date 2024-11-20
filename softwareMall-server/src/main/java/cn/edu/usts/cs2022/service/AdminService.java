package cn.edu.usts.cs2022.service;

import cn.edu.usts.cs2022.pojo.po.Admin;
import cn.edu.usts.cs2022.pojo.po.Category;
import cn.edu.usts.cs2022.pojo.po.Merchant;
import cn.edu.usts.cs2022.pojo.po.User;

import java.util.List;

public interface AdminService {
    Admin login(String username, String password);


    /**
     * 获取全部用户信息
     * @return
     */
    List<User> getUserList();


    /**
     * 修改用户状态
     * @param user
     */
    void changeUserStatus(User user);

    /**
     * 获取所有商家
     * @return
     */
    List<Merchant> getAllMerchant();


    /**
     * 获取所有分类
     * @return
     */
    List<Category> getAllCategory();

    /**
     * 添加新的分类
     * @param category
     */
    void insertCategory(Category category);

    /**
     * 删除分类
     */
    void deleteCategory(Integer id);

    /**
     * 编辑分类
     * @param category
     */
    void updateCategory(Category category);
}
