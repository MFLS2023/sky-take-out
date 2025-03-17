package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealMapper {

    //新增套餐
    @AutoFill(OperationType.INSERT)
    void add(Setmeal setmeal);

    //根据id查询套餐和关联的菜品数据
    SetmealVO getByIdWithDish(Long id);

    //套餐分页查询
    Page<SetmealVO> page(SetmealPageQueryDTO queryDTO);

    //根据id查询套餐
    @Select("select * from setmeal where id = #{id}")
    Setmeal getById(Long id);

    //批量删除套餐
    @Delete("delete from setmeal where id = #{id}")
    void deleteByIds(Long setmealId);

    //更新
    void update(Setmeal setmeal);



    /**
     * 动态条件查询套餐
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据套餐id查询菜品选项
     * @param setmealId
     * @return
     */
    @Select("select sd.name, sd.copies, d.image, d.description " +
            "from setmeal_dish sd left join dish d on sd.dish_id = d.id " +
            "where sd.setmeal_id = #{setmealId}")
    List<DishItemVO> getDishItemBySetmealId(Long setmealId);
}
