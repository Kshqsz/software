package cd.edu.usts.cs2022.service.impl;

import cd.edu.usts.cs2022.mapper.UserMapper;
import cd.edu.usts.cs2022.pojo.po.User;
import cd.edu.usts.cs2022.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password) {
        return userMapper.login(username, password);
    }
}
