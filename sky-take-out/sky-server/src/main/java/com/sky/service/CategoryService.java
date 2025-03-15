package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;

import java.util.List;

public interface CategoryService {


    void add(CategoryDTO categoryDTO);

    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    void changeCategory(CategoryDTO categoryDTO);

    void changeStatus(Integer status,long id );

    void delete(Long id);
    List<Category> list(Integer type);

}
