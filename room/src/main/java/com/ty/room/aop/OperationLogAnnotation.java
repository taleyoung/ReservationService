package com.ty.room.aop;

import java.lang.annotation.*;

@Target(ElementType.METHOD)//注解放置的目标位置即方法级别
@Retention(RetentionPolicy.RUNTIME)//注解在哪个阶段执行
@Documented
public @interface OperationLogAnnotation {
    String optModule() default ""; // 操作模块

    String optType() default "";  // 操作类型

    String optDesc() default "";  // 操作说明

}