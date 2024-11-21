package cn.edu.usts.cs2022.service;

import cn.edu.usts.cs2022.pojo.po.Category;

import java.util.List;

public interface CategoryService {
    void add(String categoryName);

    List<Category> getAllCategory();
}
