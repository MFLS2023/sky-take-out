package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.Result;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {


    @Insert("insert into category (id, type, name, sort, status, create_time, update_time, create_user, update_user) " +
            "VALUES (#{id},#{type},#{name},#{sort},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
     void add(Category category);

    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    @Update("update  category set type=#{type},name=#{name},sort=#{sort} where id=#{id}")
    void changeCategory(Category category);

    @Update("update category set status=#{status} where id =#{id}")
    void changeStatus(Integer status,long id );

    @Delete("delete  from category where id=#{id}")
    void delete(Long id);

    @Select("select * from category where type=#{type}")
    List<Category> list(Integer type);
}
