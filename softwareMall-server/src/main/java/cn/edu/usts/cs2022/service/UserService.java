package cn.edu.usts.cs2022.service;

import cn.edu.usts.cs2022.pojo.po.User;


public interface UserService {
    User login(String username, String password);

    User getUserByUsername(String username);

    void register(String username, String password);
}
