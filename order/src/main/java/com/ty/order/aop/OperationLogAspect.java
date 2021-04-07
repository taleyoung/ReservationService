package com.ty.order.aop;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ty.common.to.OptLogTo;
import com.ty.common.utils.ApiResp;
import com.ty.common.utils.IPUtil;
import com.ty.order.feign.UserFeignService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Aspect
@Component
public class OperationLogAspect {
    @Autowired
    UserFeignService userFeignService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 设置操作日志切入点   在注解的位置切入代码
     */
    @Pointcut("@annotation(com.ty.order.aop.OperationLogAnnotation)")
    public void operLogPoinCut() {
    }


    @AfterReturning(returning = "result", value = "operLogPoinCut()")
    public void saveOperLog(JoinPoint joinPoint, ApiResp result) throws Throwable {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        try {
            //将返回值转换成map集合
            OptLogTo optLogTo = new OptLogTo();
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取切入点所在的方法
            Method method = signature.getMethod();
            //获取操作
            OperationLogAnnotation annotation = method.getAnnotation(OperationLogAnnotation.class);
            if (annotation != null) {
                optLogTo.setModule(annotation.optModule());
                optLogTo.setType(annotation.optType());
                optLogTo.setDescription(annotation.optDesc());
            }
            //操作时间
            optLogTo.setOptTime(LocalDateTime.now());
            //操作用户
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if (cookie.getName().equals("jwtToken")) {
                    DecodedJWT decodedJWT = JWT.decode(cookie.getValue());
                    Integer userId = decodedJWT.getClaim("userId").asInt();
                    String userName = decodedJWT.getClaim("userName").asString();
                    optLogTo.setUserId(userId);
                    optLogTo.setUserName(userName);
                }
            }
            //操作IP
            optLogTo.setIp(IPUtil.getIpAdrress(request));
            //返回值信息
            optLogTo.setResult(result.getMsg());
            //保存日志
//            userFeignService.reportLog(optLogTo);

            //保存日志，发送到消息队列
            rabbitTemplate.convertAndSend("user-event-exchange","user.create.log",optLogTo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}