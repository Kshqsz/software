package cd.edu.usts.cs2022.service;

import cd.edu.usts.cs2022.pojo.po.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User login(String username, String password);
}
