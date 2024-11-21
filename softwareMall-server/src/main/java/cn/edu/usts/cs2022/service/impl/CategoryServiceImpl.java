package cn.edu.usts.cs2022.service.impl;

import cn.edu.usts.cs2022.mapper.CategoryMapper;
import cn.edu.usts.cs2022.pojo.po.Category;
import cn.edu.usts.cs2022.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    @Override
    public void add(String categoryName) {
        categoryMapper.add(categoryName);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryMapper.getAllCategory();
    }
}
