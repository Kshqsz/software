package cn.edu.usts.cs2022.service.impl;

import cn.edu.usts.cs2022.mapper.UserMapper;
import cn.edu.usts.cs2022.pojo.po.User;
import cn.edu.usts.cs2022.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password) {
        return userMapper.login(username, password);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUserName(username);
    }

    @Override
    public void register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setStatus(1);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.register(user);
    }

    /**
     * 获取全部用户
     *
     * @return
     */
    @Override
    public List<User> getUserList() {
        return userMapper.selectAllUsers();
    }

    /**
     * 修改用户状态
     * @param user
     */
    @Override
    public void changeUserStatus(User user) {
        userMapper.changeStatus(user);
    }
}
