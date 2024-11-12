package cd.edu.usts.cs2022.service;

import cd.edu.usts.cs2022.pojo.po.Admin;

public interface AdminService {
    Admin login(String username, String password);
}
