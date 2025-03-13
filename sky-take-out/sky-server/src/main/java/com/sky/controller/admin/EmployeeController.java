package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.dto.PasswordEditDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

    @PostMapping
    @ApiOperation("新增员工")
    public Result insert(@RequestBody EmployeeDTO employeeDTO) {
        log.info("插入员工：{}", employeeDTO);
        System.out.println("当前线程的id:"+Thread.currentThread().getId());

        employeeService.insert(employeeDTO);
        return Result.success();
    }

    /*@GetMapping("page")
    @ApiOperation("员工分页查询")
    public Result page(String name,
            @RequestParam(defaultValue = "1")Integer page,
                       @RequestParam(defaultValue = "10")Integer pageSize) {
        log.info("分页查询，参数：{},{},{}", name, page, pageSize);
        PageResult pageResult=employeeService.page(name,page,pageSize);

        return Result.success(pageResult);

    }*/
    @GetMapping("/page")
    @ApiOperation("员工分页查询")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO){
        log.info("员工分页查询，参数为：{}",employeePageQueryDTO);
        PageResult pageResult = employeeService.PageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }


    @PostMapping("/status/{status}")
    @ApiOperation("员工账号状态修改")
    public Result updateStatus(@PathVariable Integer status,long id) {
        log.info("员工账号状态修改，员工ID：{},员工账号状态：{}",status,id);
        employeeService.updateStatus(status,id);
        return Result.success();
    }



    @GetMapping("/{id}")
    @ApiOperation("根据id查找员工")
    public Result<Employee> getById(@PathVariable("id") long id) {
        log.info("根据ID：{} 查找员工信息", id);
        Employee employee =employeeService.getById(id);
        return Result.success(employee);
    }

    @PutMapping
    @ApiOperation("编辑员工信息")
    public Result update(@RequestBody Employee employee) {
        log.info("编辑员工信息：{}",employee);
        employeeService.update(employee);
        return  Result.success();
    }


    @PutMapping("/editPassword")
    @ApiOperation("修改密码")
    public Result updatePassword(@RequestBody PasswordEditDTO passwordEditDTO) {
        log.info("修改密码；{}",passwordEditDTO);
        //原来还有个情况就是：新旧密码一样，直接抛出异常
        if(passwordEditDTO.getOldPassword() .equals(passwordEditDTO.getNewPassword())){
            return Result.error("新旧密码相同");
        }
        employeeService.updatePassword(passwordEditDTO);
        return Result.success();
    }


}

