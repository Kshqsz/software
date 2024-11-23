package cn.edu.usts.cs2022.controller;


import cn.edu.usts.cs2022.pojo.dto.ProductDTO;
import cn.edu.usts.cs2022.pojo.po.Product;
import cn.edu.usts.cs2022.pojo.po.Result;
import cn.edu.usts.cs2022.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping
    public Result update(@RequestBody Product product) {
        productService.update(product);
        return Result.success();
    }

    @GetMapping("/search")
    public Result<List<Product>> search(String searchInfo) {
        List<Product> productList = productService.search(searchInfo);
        return Result.success(productList);
    }

    @GetMapping("/{id}")
    public Result<Product> getById(@PathVariable("id") Integer id) {
        Product product = productService.getById(id);
        return Result.success(product);
    }

    @PostMapping("/getByIds")
    public Result<List<Product>> getByIds(@RequestBody List<Integer> ids) {
        if (ids.size() == 0) {
            return Result.success(List.of());
        }
        List<Product> products = productService.getByIds(ids);
        return Result.success(products);
    }

}
