package cn.edu.usts.cs2022.controller;


import cn.edu.usts.cs2022.pojo.dto.ProductDTO;
import cn.edu.usts.cs2022.pojo.po.Product;
import cn.edu.usts.cs2022.pojo.po.Result;
import cn.edu.usts.cs2022.service.ProductService;
import cn.edu.usts.cs2022.utils.ThreadLocalUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public Result add(@RequestBody ProductDTO productDTO) {
        productService.add(productDTO);
        return Result.success();
    }

    @GetMapping("/all")
    public Result<List<Product>> getAllProduct() {
        List<Product> productList = productService.getAllProduct();
        return Result.success(productList);
    }

    @GetMapping("/all/{merchantId}")
    public Result<List<Product>> getAllByMerchantId(@PathVariable("merchantId") Integer merchantId) {
        List<Product> productList = productService.getAllByMerchantId(merchantId);
        return Result.success(productList);
    }
}
