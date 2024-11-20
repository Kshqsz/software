package cn.edu.usts.cs2022.service.impl;

import cn.edu.usts.cs2022.mapper.CategoryMapper;
import cn.edu.usts.cs2022.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    @Override
    public void add(String categoryName) {
        categoryMapper.add(categoryName);
    }
}
