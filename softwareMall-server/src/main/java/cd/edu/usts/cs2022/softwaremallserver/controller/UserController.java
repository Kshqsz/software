package cd.edu.usts.cs2022.softwaremallserver.controller;

import cd.edu.usts.cs2022.softwaremallserver.pojo.dto.LoginDTO;
import cd.edu.usts.cs2022.softwaremallserver.pojo.po.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {

    @GetMapping("/login")
    public Result Login() {
        return Result.success(12);
    }
}
