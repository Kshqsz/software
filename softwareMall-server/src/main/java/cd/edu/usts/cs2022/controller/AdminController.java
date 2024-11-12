package cd.edu.usts.cs2022.controller;

import cd.edu.usts.cs2022.pojo.dto.LoginDTO;
import cd.edu.usts.cs2022.pojo.po.Admin;
import cd.edu.usts.cs2022.pojo.po.Result;
import cd.edu.usts.cs2022.service.AdminService;
import cd.edu.usts.cs2022.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    public final AdminService adminService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        Admin admin = adminService.login(username, password);

        if (admin == null) {
            return Result.error("用户名或密码错误");
        } else {
            Map<String, Object> claims = new HashMap<>();
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
    }
}
