package cn.edu.usts.cs2022.controller;

import cn.edu.usts.cs2022.pojo.dto.CategoryDTO;
import cn.edu.usts.cs2022.pojo.po.Category;
import cn.edu.usts.cs2022.pojo.po.Result;
import cn.edu.usts.cs2022.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/add")
    public Result add(@RequestBody CategoryDTO categoryDTO) {
        String categoryName = categoryDTO.getName();
        categoryService.add(categoryName);
        return Result.success();
    }

    @GetMapping("/all")
    public Result<List<Category>> getAllCategory() {
        List<Category> categoryList = categoryService.getAllCategory();
        return Result.success(categoryList);
    }

}
