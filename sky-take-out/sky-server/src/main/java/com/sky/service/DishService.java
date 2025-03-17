package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
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

    //修改菜品的起售、停售状态
    void updateStatus(Integer status, long id);

    //根据分类id获取菜品
    List<Dish> list(Long categoryId);

    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);
}