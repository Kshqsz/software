package cn.edu.usts.cs2022.service;

import cn.edu.usts.cs2022.pojo.po.Merchant;

public interface MerchantService {
    Merchant login(String username, String password);

    Merchant getMerchantByUsername(String username);

    void register(String username, String password);
}
