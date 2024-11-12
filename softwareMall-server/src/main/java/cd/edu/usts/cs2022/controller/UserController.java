package cd.edu.usts.cs2022.controller;

import cd.edu.usts.cs2022.pojo.dto.UserDTO;
import cd.edu.usts.cs2022.pojo.po.Result;
import cd.edu.usts.cs2022.pojo.dto.LoginDTO;
import cd.edu.usts.cs2022.pojo.po.User;
import cd.edu.usts.cs2022.service.UserService;
import cd.edu.usts.cs2022.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public Result Login(@RequestBody LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        User user = userService.login(username, password);
        if (user == null) {
            return Result.error("用户名或密码错误");
        } else {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            String token = JwtUtil.genToken(claims);
            UserDTO userDTO = new UserDTO();
            userDTO.setUser(user);
            userDTO.setUserId(user.getId());
            userDTO.setToken(token);
            return Result.success(userDTO);
        }
    }
}
