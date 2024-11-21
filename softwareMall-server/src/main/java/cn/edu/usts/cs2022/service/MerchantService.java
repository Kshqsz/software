package cn.edu.usts.cs2022.service;

import cn.edu.usts.cs2022.pojo.po.Merchant;

import java.util.List;

public interface MerchantService {
    Merchant login(String username, String password);

    Merchant getMerchantByUsername(String username);

    void register(String username, String password);

    /**
     * 查询所有商家
     * @return
     */
    List<Merchant> getAllMerchant();

    /**
     * 修改商家状态
     * @param merchant
     */
    void changeStatus(Merchant merchant);
}