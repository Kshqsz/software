package cn.edu.usts.cs2022.service;

import cn.edu.usts.cs2022.pojo.dto.CountOrderDTO;
import cn.edu.usts.cs2022.pojo.dto.UserUpdateDTO;
import cn.edu.usts.cs2022.pojo.po.Favourite;
import cn.edu.usts.cs2022.pojo.po.User;

import java.util.List;


public interface UserService {
    User login(String username, String password);

    User getUserByUsername(String username);

    void register(String username, String password);

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

    List<Favourite> getMyFavourite(Integer userId);

    Integer countFavourite(Integer userId);

    void updatePassword(String newPassword);

    void update(UserUpdateDTO userUpdateDTO);

    User getById(Integer id);

    Integer countOrder(CountOrderDTO countOrderDTO);
}
