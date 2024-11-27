package cn.edu.usts.cs2022.service.impl;

import cn.edu.usts.cs2022.mapper.MerchantMapper;
import cn.edu.usts.cs2022.mapper.ProductMapper;
import cn.edu.usts.cs2022.pojo.dto.UserUpdateDTO;
import cn.edu.usts.cs2022.pojo.po.Merchant;
import cn.edu.usts.cs2022.pojo.vo.MerchantVO;
import cn.edu.usts.cs2022.service.MerchantService;
import cn.edu.usts.cs2022.utils.ThreadLocalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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


    /**
     * 查询所有商家
     * @return
     */
    @Override
    public List<Merchant> getAllMerchant() {
        return merchantMapper.selectAllMerchant();
    }

    /**
     * 修改商家状态
     * @param merchant
     */
    @Override
    public void changeStatus(Merchant merchant) {
        merchantMapper.changeStatus(merchant);
    }

    @Override
    public MerchantVO getByProductId(Integer id) {
        return merchantMapper.getByProductId(id);
    }

    @Override
    public Merchant getById(Integer id) {
        return merchantMapper.getById(id);
    }

    @Override
    public void updatePassword(String newPassword) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("merchantId");
        merchantMapper.updatePassword(userId, newPassword);
    }

    @Override
    public void update(UserUpdateDTO userUpdateDTO) {
        merchantMapper.update(userUpdateDTO);
    }

}
