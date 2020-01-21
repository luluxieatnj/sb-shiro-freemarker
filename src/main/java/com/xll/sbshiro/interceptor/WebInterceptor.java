package com.xll.sbshiro.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  拦截器
 *  1. 关于jdk1.8的接口新特性 ： HandlerInterceptor接口中  3个方法都是 "default"类型   jdk1.8的接口新特性
 *              1.1 default 修饰接口中方法，子类不再需要 "强制重写"， -- 本例中 本类WebInterceptor 可以重写方法，也可不写
 *              1.2 如果 类C 实现A 和B两个接口，而A B 接口中方法名又一样，参数列表相同，则C此时必须重写方法
 *  2. HandlerInterceptor接口
 *      2.1 preHandle 在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理；
 *      2.2 postHandle：在业务处理器处理请求执行完成后，生成视图之前执行。后处理（返回ModelAndView，但未进行页面渲染），有机会修改ModelAndView ;
 *      2.3 afterCompletion：在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。返回处理（已经渲染了页面）；
 *
 *  3. 拦截器生效
 *      需要添加配置类  springBoot2.0以上 实现  WebMvcConfigurerAdapter
 *
 */
@Slf4j
public class WebInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle 在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理。。。。");
        String contextPath = request.getContextPath();
        String requestURI = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();
        String servletPath = request.getServletPath();
        log.info("contextPath = {}", contextPath);
        log.info("requestURI = {}", requestURI);
        log.info("requestURL = {}", requestURL);
        log.info("servletPath = {}", servletPath);
        // 项目地址设置成 ctx
        request.setAttribute("ctx", contextPath);
        // 设置OK  返回true // 如果return  false 程序卡死
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle：在业务处理器处理请求执行完成后，生成视图之前执行。。。。");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion：在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。。。。");
    }
}
