package cn.edu.usts.cs2022.service.impl;

import cn.edu.usts.cs2022.pojo.dto.ProductDTO;
import cn.edu.usts.cs2022.pojo.po.Product;
import cn.edu.usts.cs2022.service.ProductService;
import cn.edu.usts.cs2022.utils.ThreadLocalUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService  {

    @Override
    public void add(ProductDTO productDTO) {
        Product product = new Product();

        BeanUtils.copyProperties(productDTO, product);
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer merchantId = (Integer) map.get("merchantId");
        product.setMerchantId(merchantId);
        product.setStatus(0); // 表示待审核
        product.setCreateTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
    }
}
