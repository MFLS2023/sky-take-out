package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.PasswordConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.dto.PasswordEditDTO;
import com.sky.entity.Employee;
import com.sky.exception.AccountLockedException;
import com.sky.exception.AccountNotFoundException;
import com.sky.exception.PasswordEditFailedException;
import com.sky.exception.PasswordErrorException;
import com.sky.mapper.EmployeeMapper;
import com.sky.result.PageResult;
import com.sky.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        // TODO 后期需要进行md5加密，然后再进行比对
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }

    @Override
    public void insert(EmployeeDTO employeeDTO){
        System.out.println("当前线程的id:"+Thread.currentThread().getId());
        Employee  employee=new Employee();
        //属性拷贝
        BeanUtils.copyProperties(employeeDTO,employee);
        //帐号状态默认为1，正常状态
        employee.setStatus(StatusConstant.ENABLE);

        //默认密码123456
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
/*        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        //修改“操作人”
        employee.setCreateUser(BaseContext.getCurrentId());
        employee.setUpdateUser(BaseContext.getCurrentId());*/
        employeeMapper.insert(employee);
    }

/*    public PageResult page(String name, Integer page, Integer pageSize){
        PageHelper.startPage(page,pageSize);
        //执行分页查询
        List<EmployeeDTO>employeeDTOList=employeeMapper.list(name,page,pageSize);
        //获取查询结果
        Page<EmployeeDTO> p=(Page<EmployeeDTO>)employeeDTOList;
        //封装PageResult并返回
        return new PageResult(p.getTotal(),p.getResult());
    }*/

    @Override
    public PageResult PageQuery(EmployeePageQueryDTO employeePageQueryDTO) { //DTO已将页码和每页记录数传入，因此可以算出
        // select * from employee limit 0,10，通过Limit来控制
        PageHelper.startPage(employeePageQueryDTO.getPage(),employeePageQueryDTO.getPageSize()); //页码和每页记录数传入
        //Page是固定的，Employee是每个用户的信息
        Page<Employee> page = employeeMapper.pageQuery(employeePageQueryDTO);//
        //要将page对象处理为PageResult对象
        long total = page.getTotal();
        List<Employee> result = page.getResult();
        return new PageResult(total,result);
    }

    //修改员工状态
    @Override
    public void updateStatus(Integer status, long id){
/*        Employee employee=employeeMapper.getById(id);
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(BaseContext.getCurrentId());
        employeeMapper.updateStatus(status,id);*/

        Employee employee = Employee.builder()
                .status(status)
                .id(id)
                .build();
        employeeMapper.update(employee);
    }

    //根据id查找员工
    @Override
    public Employee getById(long id){
        Employee employee = employeeMapper.getById(id);
        return employee;
    }

    //修改员工信息
    @Override
    public void update(Employee employee){
/*        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(BaseContext.getCurrentId());*/
        employeeMapper.update(employee);
    }

    //修改密码
    @Override
    public void updatePassword(PasswordEditDTO passwordEditDTO){
//        Employee employee = employeeMapper.getById(BaseContext.getCurrentId());

        Long id= passwordEditDTO.getEmpId();
        Employee employee= employeeMapper.getById(id);
/*        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(BaseContext.getCurrentId());*/

        boolean password_Answer=passwordEditDTO.getOldPassword().equals(employee.getPassword());
        if(!password_Answer){
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }else if(password_Answer){
            employee.setPassword(passwordEditDTO.getNewPassword());
            employeeMapper.update(employee);
        }else{
            throw new PasswordEditFailedException(MessageConstant.PASSWORD_EDIT_FAILED);
        }

/*        String password = passwordEditDTO.getOldPassword();
        //把旧密码进行md5加密，再进行对比
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        Employee employee = employeeMapper.getById(BaseContext.getCurrentId());
        if(!password.equals(employee.getPassword())){
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }else{
            employee.setPassword(DigestUtils.md5DigestAsHex(passwordEditDTO.getNewPassword().getBytes()));
            employeeMapper.update(employee);
        }*/

    }


}
