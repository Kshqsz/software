package cn.edu.usts.cs2022.service.impl;

import cn.edu.usts.cs2022.mapper.AdminMapper;
import cn.edu.usts.cs2022.pojo.po.Admin;
import cn.edu.usts.cs2022.pojo.po.Category;
import cn.edu.usts.cs2022.pojo.po.Merchant;
import cn.edu.usts.cs2022.pojo.po.User;
import cn.edu.usts.cs2022.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    public final AdminMapper adminMapper;
    @Override
    public Admin login(String username, String password) {
        return adminMapper.login(username, password);
    }

    /**
     * 获取全部用户
     * @return
     */
    @Override
    public List<User> getUserList() {
        return adminMapper.selectAllUsers();
    }

    /**
     * 修改用户状态
     * @param user
     */
    @Override
    public void changeUserStatus(User user) {
        adminMapper.changeStatus(user);
    }

    /**
     * 获取所有商家
     * @return
     */
    @Override
    public List<Merchant> getAllMerchant() {
        return adminMapper.selectMerchant();
    }

    /**
     * 获取全部分类
     * @return
     */
    @Override
    public List<Category> getAllCategory() {
        return adminMapper.selectCategory();
    }

    /**
     * 新增分类
     * @param category
     */
    @Override
    public void insertCategory(Category category) {
        adminMapper.insertCategory(category);
    }

    /**
     * 删除分类
     * @param id
     */
    @Override
    public void deleteCategory(Integer id) {
        //TODO
        //添加删除限制
       /* adminMapper.selectProductByCategoryId(id)
        if()*/
        adminMapper.deleteCategory(id);
    }

    /**
     * 编辑分类
     * @param category
     */
    @Override
    public void updateCategory(Category category) {
        adminMapper.updatecategory(category);
    }
}
