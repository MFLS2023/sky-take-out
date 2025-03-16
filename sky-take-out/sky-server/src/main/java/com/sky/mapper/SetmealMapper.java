package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SetmealMapper {

    //新增套餐
    @AutoFill(OperationType.INSERT)
    void add(Setmeal setmeal);

    //根据id查询套餐
    SetmealVO getByIdWithDish(Long id);

    //套餐分页查询
    Page<SetmealVO> page(SetmealPageQueryDTO queryDTO);
}
