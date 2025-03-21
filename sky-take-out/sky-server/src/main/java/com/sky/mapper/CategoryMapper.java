package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import com.sky.result.Result;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {


    @Insert("insert into category (id, type, name, sort, status, create_time, update_time, create_user, update_user) " +
            "VALUES (#{id},#{type},#{name},#{sort},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @AutoFill(value= OperationType.INSERT)
    void add(Category category);

    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    @Update("update category set type=#{type}, name=#{name}, sort=#{sort}, update_time=#{updateTime}, update_user=#{updateUser} where id=#{id}")
    @AutoFill(value= OperationType.UPDATE)
    void changeCategory(Category category);

    @Update("update category set status=#{status}, update_time=#{updateTime}, update_user=#{updateUser} where id=#{id}")
    @AutoFill(value= OperationType.UPDATE)
    void changeStatus(Category category );

    @Delete("delete  from category where id=#{id}")
    void delete(Long id);


    List<Category> list(Integer type);
}
