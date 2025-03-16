package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetmealService {

    /**
     * 批量删除套餐
     *
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 根据id查询套餐和关联的菜品数据
     *
     * @param id
     * @return
     */
    //根据id查找套餐和关联的菜品数据
    SetmealVO getByIdWithDish(Long id);

    /**
     * 修改套餐
     * @param setmealDTO
     */
    void update(SetmealDTO setmealDTO);

    //添加套餐
    void add(SetmealDTO setmealDTO);

    //分页查询
    PageResult page(SetmealPageQueryDTO queryDTO);

    //批量删除套餐
    void deleteByIds(List<Long> ids);

}
