package cn.edu.usts.cs2022.controller;

import cn.edu.usts.cs2022.pojo.po.Result;
import cn.edu.usts.cs2022.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/add")
    public Result add(String categoryName) {
        categoryService.add(categoryName);
        return Result.success();
    }
}
