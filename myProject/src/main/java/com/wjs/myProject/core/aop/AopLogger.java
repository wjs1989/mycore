package com.wjs.myProject.core.aop;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Aspect
public class AopLogger {

    //凡是注解了RequestMapping的方法都被拦截
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    private void webPointcut() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    private void postPointcut() {
    }

    @Pointcut("execution(* com.wjs.myProject.core.aop.*.*(..))")
    private void point(){}

    @Before("point()")
    public void beforeAdvice(JoinPoint joinPoint){
        System.out.println("Going to setup student profile.");
    }
    @After("point()")
    public void afterAdvice(JoinPoint joinPoint){
        System.out.println("Student profile has been setup.");
    }

    @AfterReturning(pointcut = "point()", returning="retVal")
    public void afterReturningAdvice(Object retVal){
        System.out.println("Returning:" + retVal.toString() );
    }

    @AfterThrowing(pointcut = "point()", throwing = "ex")
    public void AfterThrowingAdvice(JoinPoint joinPoint,Exception ex){
        System.out.println("There has been an exception: " + ex.toString());
    }


    private void writeContent(String content) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "text/json;charset=UTF-8");
        response.setHeader("icop-content-type", "exception");
        PrintWriter writer = null;
        JsonGenerator jsonGenerator = null;
        try {
            writer = response.getWriter();
            jsonGenerator = (new ObjectMapper()).getFactory().createGenerator(writer);
            jsonGenerator.writeObject(content);
        } catch (IOException e1) {
        }finally {
            writer.flush();
            writer.close();
        }
    }
}
