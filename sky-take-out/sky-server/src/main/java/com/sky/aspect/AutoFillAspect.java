package com.sky.aspect;


import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

//自定义切面类，实现公共字段自动填充处理逻辑
@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void AutoFillPointCut(){}

    @Before("AutoFillPointCut()")
    public void autoFill(JoinPoint joinPoint){
        log.info("自动字段填充");
        // 通过连接点对象获取到在执行的对象
        MethodSignature signature = (MethodSignature) joinPoint.getSignature(); // 获取到方法签名
        // 拿到对应注解所使用的方法
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);
        // 获取到这个方法的操作类型
        OperationType type = autoFill.value();
        // 获得方法的参数
        Object[] pointArgs = joinPoint.getArgs();
        // 拿到实体类对象
        Object entity = pointArgs[0];
        // 获取当前时间和操作人id
        LocalDateTime now = LocalDateTime.now();
        Long id = BaseContext.getCurrentId();
        // 对不同的操作类型使用不同的反射
        if (type == OperationType.INSERT){
            try {
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                // 为获得的方法对象赋值
                setCreateTime.invoke(entity,now);
                setUpdateTime.invoke(entity,now);
                setCreateUser.invoke(entity,id);
                setUpdateUser.invoke(entity,id);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(type == OperationType.UPDATE){
            try {
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                // 为获得的方法对象赋值
                setUpdateTime.invoke(entity,now);
                setUpdateUser.invoke(entity,id);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}