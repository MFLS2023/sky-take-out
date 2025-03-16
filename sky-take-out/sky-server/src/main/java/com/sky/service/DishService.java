package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;
import java.util.Set;

public interface DishService {
    //新增菜品和对应的口味
     void saveWithFlavor(DishDTO dishDTO);

    //分页查询
    PageResult page(DishPageQueryDTO dishPageQueryDTO);

    //根据ID批量删除菜品
    void deleteBatch(List<Long> ids);

    //根据ID查找菜品和对应口味
    DishVO getByIdWithFlavor(Long id);

    //根据id修改菜品基本信息和对应的口味信息
    void updateWithFlavor(DishDTO dishDTO);
}