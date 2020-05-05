package com.wjs.mybatis.configuration.interceptor;


import com.wjs.mybatis.configuration.interceptor.annotation.LoginValidate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * @author wenjs
 */
public class LoginInterceptor implements HandlerInterceptor {
    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean validate = false;
        if(handler instanceof HandlerMethod){
            validate = validate(request, response,  (HandlerMethod)handler);
        }else if(handler instanceof ResourceHttpRequestHandler){
            validate = true;
        }

        if(!validate){
            response.setHeader("content-type", "application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
//            ResultVo<String> error = ResultVo.error(CodeMsg.SESSION_ERROR);
//           out.print(JSONObject.toJSONString(error));
            out.close();
        }
        return validate;
    }

    private boolean validate(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler){
        RestController annotationController = handler.getBeanType().getAnnotation(RestController.class);
        if(annotationController == null){
            return true;
        }
        LoginValidate annotation = handler.getBeanType().getAnnotation(LoginValidate.class);
        if(annotation != null && !annotation.value()){
            return true;
        }

        annotation = handler.getMethodAnnotation(LoginValidate.class);
        if(annotation != null && !annotation.value()){
            return true;
        }

        HttpSession session = request.getSession();
        if(session== null){
            return false;
        }

//        Object user = session.getAttribute(CommonConstant.USER_SESSION_ID);
//        if(user== null || !(user instanceof User)){
//            return false;
//        }
//        PlatformUserManager.setUser((User)user);
        return true;
    }



//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//
//    }
}
