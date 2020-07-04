package com.six.group.configer;

import com.six.group.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Override
    /**拦截器配置
     *
     */
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistry=registry.addInterceptor(new LoginInterceptor());
        interceptorRegistry.addPathPatterns("/html/*");   //拦截html下的路径
        interceptorRegistry.excludePathPatterns(                         //添加不拦截路径
                "/html/login.html",            //登录
                "/html/checkLogin.html"       //登录跳转
        );
    }


}
