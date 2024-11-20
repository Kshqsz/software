package cn.edu.usts.cs2022.service.impl;

import cn.edu.usts.cs2022.mapper.MerchantMapper;
import cn.edu.usts.cs2022.mapper.ProductMapper;
import cn.edu.usts.cs2022.pojo.po.Merchant;
import cn.edu.usts.cs2022.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {
    private final MerchantMapper merchantMapper;
    @Override
    public Merchant login(String username, String password) {
        return merchantMapper.login(username, password);
    }

    @Override
    public Merchant getMerchantByUsername(String username) {
        return merchantMapper.getMerchantByUsername(username);
    }

    @Override
    public void register(String username, String password) {
        Merchant merchant = new Merchant();
        merchant.setUsername(username);
        merchant.setPassword(password);
        merchant.setStatus(1);
        merchant.setCreateTime(LocalDateTime.now());
        merchant.setUpdateTime(LocalDateTime.now());
        merchantMapper.register(merchant);
    }
}
