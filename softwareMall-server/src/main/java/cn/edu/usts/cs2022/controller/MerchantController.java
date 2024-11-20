package cn.edu.usts.cs2022.controller;

import cn.edu.usts.cs2022.pojo.dto.LoginDTO;
import cn.edu.usts.cs2022.pojo.dto.MerchantDTO;
import cn.edu.usts.cs2022.pojo.dto.RegisterDTO;
import cn.edu.usts.cs2022.pojo.po.Merchant;
import cn.edu.usts.cs2022.pojo.po.Result;
import cn.edu.usts.cs2022.service.MerchantService;
import cn.edu.usts.cs2022.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;
    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        Merchant merchant = merchantService.login(username, password);
        if (merchant == null) {
            return Result.error("用户名或密码错误");
        } else {
            Integer status = merchant.getStatus();
            if (status == 0) {
                return Result.error("商家已被禁用！请联系管理员");
            }
            Map<String, Object> claims = new HashMap<>();
            claims.put("merchantId", merchant.getId());
            String token = JwtUtil.genToken(claims);
            MerchantDTO merchantDTO = new MerchantDTO();
            merchantDTO.setMerchant(merchant);
            merchantDTO.setMerchantId(merchant.getId());
            merchantDTO.setToken(token);
            return Result.success(merchantDTO);
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
        Merchant merchant = merchantService.getMerchantByUsername(username);
        if (merchant != null) {
            return Result.error("用户名已存在");
        }
        merchantService.register(username, password);
        return Result.success();
    }
}
