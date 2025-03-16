package com.sky.mapper;

import com.sky.annotation.AutoFill;
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
}
