package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequestMapping("/admin/category")
@Api(tags="菜品分类相关接口")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


/*    @PutMapping
    public Result update(Category category) {

        return Result.success();
    }*/

    @PostMapping
    @ApiOperation("新增菜品")
    public Result<String> add(@RequestBody CategoryDTO categoryDTO) {
        log.info("新增菜品：{}", categoryDTO);
        categoryService.add(categoryDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("分页查询")
    public Result<PageResult> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        log.info("菜品分页查询：{}", categoryPageQueryDTO);
        PageResult pageResult=categoryService.pageQuery(categoryPageQueryDTO);

        if (pageResult != null){
            return Result.success(pageResult);
        }else {
            return Result.error("菜品查询出现问题，请联系管理员解决");
        }
    }

    @PutMapping
    @ApiOperation("修改分类")
    public Result changCategory (@RequestBody CategoryDTO categoryDTO) {
        log.info("修改菜品的分类{}", categoryDTO);
        categoryService.changeCategory(categoryDTO);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    @ApiOperation("启用、禁用分类")
    public Result<String> changeStatus(@PathVariable("status") Integer status, Long id) {
        log.info("修改菜品id为{} 的启用状态{}", status,id);
        categoryService.changeStatus(status,id);
        return Result.success();
    }

    //根据id删除分类
    @DeleteMapping
    @ApiOperation("根据id删除分类")
    public Result<String> delete(Long id) {
        log.info("删除id为{}的菜品",id);
        categoryService.delete(id);
        return Result.success();
    }

    //根据类型查询分类
    @GetMapping("/list")
    @ApiOperation("根据类型查询分类")
    public Result<List<Category>> list(Integer type) {
        log.info("查询类型为：{}的所有菜品",type);
        List<Category>result=categoryService.list(type);
        return Result.success(result);
    }
}
