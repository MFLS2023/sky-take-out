package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Category;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    @Insert("insert into employee (\n" +
            "              id, name, username,password,  phone, sex, id_number,create_time,  update_time,create_user,update_user)\n" +
            "        VALUES\n" +
            "            (#{id},#{name},#{username},#{password},#{phone},#{sex},#{idNumber},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @AutoFill(value= OperationType.INSERT)
    void insert(Employee employee);


    /*List<EmployeeDTO> list(String name, Integer page, Integer pageSize);*/
    //分页查询
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    //更新员工状态
    @Update("update employee set status=#{status} where id=#{id}")
    void updateStatus(Integer status,long id);

    //编辑员工信息
    @AutoFill(value= OperationType.UPDATE)
    void update(Employee employee);

    //根据id查询员工
    @Select("select * from employee where id=#{id} ")
    Employee getById(long id);




}
