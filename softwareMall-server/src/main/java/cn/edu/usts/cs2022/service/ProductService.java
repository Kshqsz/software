package cn.edu.usts.cs2022.service;

import cn.edu.usts.cs2022.pojo.dto.ProductDTO;
import cn.edu.usts.cs2022.pojo.po.Product;

import java.util.List;

public interface ProductService {
    void add(ProductDTO productDTO);

    List<Product> getAllProduct();

    List<Product> getAllByMerchantId(Integer merchantId);

    void update(Product product);

    List<Product> search(String searchInfo);

    Product getById(Integer id);
}
