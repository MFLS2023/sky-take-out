package com.sky.controller.admin;


import com.sky.dto.SetmealDTO;
import com.sky.entity.Setmeal;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/setmeal")
@Api(tags="套餐相关接口")
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealService setmealService;


    @PostMapping
    public Result add(@RequestBody SetmealDTO setmealDTO) {
        log.info("添加套餐：{}", setmealDTO);
        setmealService.add(setmealDTO);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<SetmealVO> getById(long id) {
        log.info("查找id为：{}的菜品",id);
        SetmealVO result=setmealService.getByIdWithDish(id);
        return Result.success(result);
    }

}
