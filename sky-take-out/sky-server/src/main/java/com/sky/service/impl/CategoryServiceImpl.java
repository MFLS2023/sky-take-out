package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.mapper.EmployeeMapper;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;


    //插入菜品
    @Override
    public void add(CategoryDTO categoryDTO){
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        //分类状态默认为0
        category.setStatus(StatusConstant.DISABLE);
        //设置创建时间，修改时间，创建人和修改人
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateUser(BaseContext.getCurrentId());
        category.setUpdateUser(BaseContext.getCurrentId());
        categoryMapper.add(category);
    }

    //分页查询
    @Override
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO){
        PageHelper.startPage(categoryPageQueryDTO.getPage(),categoryPageQueryDTO.getPageSize());
        Page<Category>page=categoryMapper.pageQuery(categoryPageQueryDTO);

//        PageResult pageResult=new PageResult(page.getTotal(),page.getResult());
        long total = page.getTotal();
        List<Category> list = page.getResult();

        PageResult pageResult = new PageResult();
        pageResult.setTotal(total);
        pageResult.setRecords(list);
        return pageResult;
    }

    //修改分类
    @Override
    public void changeCategory(CategoryDTO categoryDTO){
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(BaseContext.getCurrentId());
        categoryMapper.changeCategory(category);
    }

    //启用、禁用菜品状态
    @Override
    public void changeStatus(Integer status,long id){
        Category category = new Category();
        BeanUtils.copyProperties(status,category);
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(BaseContext.getCurrentId());
        categoryMapper.changeStatus(status,id);
    }

    //删除菜品
    @Override
    public void delete(Long id){
        categoryMapper.delete(id);
    }

    //根据类型查询分类
    @Override
    public List<Category> list(Integer type){
        List<Category> result= categoryMapper.list(type);
        return result;
    }


}
