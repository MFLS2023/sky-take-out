package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {

    //根据分类id查询菜品数量
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    @AutoFill(OperationType.INSERT)
    void insert(Dish dish);


    Page<DishVO> page(DishPageQueryDTO dishPageQueryDTO);

    //根据菜品id删除对应的口味数据
    @Delete("delete from dish where id = #{id}")
    void deleteById(Long id);

    //根据主键查找菜品
    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    //根据id动态修改菜品数据
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);


    List<Dish> list(Dish dish);
}
