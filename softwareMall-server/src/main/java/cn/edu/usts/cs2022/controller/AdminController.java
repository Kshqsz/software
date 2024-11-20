package cn.edu.usts.cs2022.controller;

import cn.edu.usts.cs2022.pojo.dto.LoginDTO;
import cn.edu.usts.cs2022.pojo.po.*;
import cn.edu.usts.cs2022.service.AdminService;
import cn.edu.usts.cs2022.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
            System.out.println("登录成功"+token);
            return Result.success(token);
        }
    }

    /**
     * 查询所有用户
     *get
     */
    @GetMapping("/user")
    public Result<List<User>> getUserList() {
        System.out.println("查询全部信息");
        List<User> users = adminService.getUserList();
        for (User user : users) {
            user.setPassword("*********");
        }
        return Result.success(users);
    }
    /**
     * 修改用户状态
     */

    @PutMapping("/user")
    public Result changeUserStatus(@RequestBody User user) {
        adminService.changeUserStatus(user);
        return Result.success("修改成功");
    }


    /**
     * 查询所有商家
     */
    @GetMapping("/merchant")
    public Result<List<Merchant>> getAllMerchant() {
        List<Merchant> list = adminService.getAllMerchant();
        for (Merchant merchant : list) {
            merchant.setPassword("*********");
        }
        return Result.success(list);
    }

    /**
     * 查询所有分类
     */

    @GetMapping("/category")
    public Result<List<Category>> getAllCategory() {
        List<Category> list = adminService.getAllCategory();
        return Result.success(list);
    }

    /**
     * 新增分类
     */
    @PostMapping("/category")
    public Result insertCategory(@RequestBody Category category) {
        adminService.insertCategory(category);
        return Result.success();
    }

    /**
     * 删除分类
     */
    @DeleteMapping("/category/{id}")
    public Result deleteCategory(@PathVariable Integer id) {
        System.out.println(id);
        adminService.deleteCategory(id);
        return Result.success();
    }

    /**
     * 编辑分类
     */
    @PutMapping("/category")
    public Result updateCategory(@RequestBody Category category) {
        adminService.updateCategory(category);
        return Result.success();
    }
}
