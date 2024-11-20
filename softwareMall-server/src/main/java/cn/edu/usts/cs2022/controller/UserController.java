package cn.edu.usts.cs2022.controller;

import cn.edu.usts.cs2022.pojo.dto.RegisterDTO;
import cn.edu.usts.cs2022.pojo.dto.UserDTO;
import cn.edu.usts.cs2022.pojo.po.Result;
import cn.edu.usts.cs2022.pojo.dto.LoginDTO;
import cn.edu.usts.cs2022.pojo.po.User;
import cn.edu.usts.cs2022.service.UserService;
import cn.edu.usts.cs2022.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 用户登录
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    public Result Login(@RequestBody LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        User user = userService.login(username, password);
        if (user == null) {
            return Result.error("用户名或密码错误");
        } else {
            Integer status = user.getStatus();
            if (status == 0) {
                return Result.error("用户已被禁用！请联系管理员");
            }
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", user.getId());
            String token = JwtUtil.genToken(claims);
            UserDTO userDTO = new UserDTO();
            userDTO.setUser(user);
            userDTO.setUserId(user.getId());
            userDTO.setToken(token);
            return Result.success(userDTO);
        }
    }

    @PostMapping("/register")
    public Result Register(@RequestBody RegisterDTO registerDTO) {
        String username = registerDTO.getUsername();
        String password = registerDTO.getPassword();
        String rePassword = registerDTO.getRePassword();

        if (!password.equals(rePassword)) {
            return Result.error("两次密码输入不一致!");
        }
        User user = userService.getUserByUsername(username);
        if (user != null) {
            return Result.error("用户名已存在");
        }
        userService.register(username, password);
        return Result.success();
    }




}
