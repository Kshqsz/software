package cd.edu.usts.cs2022.service.impl;

import cd.edu.usts.cs2022.mapper.AdminMapper;
import cd.edu.usts.cs2022.pojo.po.Admin;
import cd.edu.usts.cs2022.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    public final AdminMapper adminMapper;
    @Override
    public Admin login(String username, String password) {
        return adminMapper.login(username, password);
    }
}
