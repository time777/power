package com.yunshuju.config;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.yunshuju.common.utils.MD5Util;
import com.yunshuju.config.event.RoleResourceUpdateEvent;
import com.yunshuju.security.AccessDecisionManagerImpl;
import com.yunshuju.security.CSRFSecurityRequestMatcher;
import com.yunshuju.security.FilterInvocationSecurityMetadataSourceImpl;
import com.yunshuju.service.CustomUserService;

/**
 * 功能：SpringSecurity的自定义配置类
 *
 * @author： YuYanHui(yuyh@dtdream.com)
 * @create： 2018-09-17 11:24:06
 * @version： 2018 Version 1.0
 * @company： 数梦工场 Created with IntelliJ IDEA
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter implements ApplicationListener<RoleResourceUpdateEvent> {

    @Autowired
    private CustomUserService customUserService;


    /**
     * 用户名密码校验进行比较 用的自定义的加密方式MD5
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return MD5Util.encode((String) rawPassword);
            }
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(MD5Util.encode((String) rawPassword));
            }
        });
    }

    /**
     * 核心处理
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /**
                 *  不使用自定义过滤器类的话，可以直接使用默认实现的类，并提供自定义的属性
                 */
                .addFilter(filterSecurityInterceptor())
                .authorizeRequests()
                //允许所有用户访问的页面
                .antMatchers("/").permitAll()
                .antMatchers("/403.html", "/404.html", "/500.html", "/noAccess.html").permitAll()
                //静态文件忽略拦截
                .antMatchers("/static/**", "/css/**", "/js/**", "/images/**").permitAll()
                //其他地址的访问均需验证权限
                .anyRequest().authenticated()
                //指定登录的页面和提交的API
                .and().formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login").permitAll()
                //注销处理
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                //退出登录后的默认网址是登录页面
                .logoutSuccessUrl("/login.html").permitAll()
                //注销后使session相关信息无效
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                //开启rememberme功能：验证，登录成功后，关闭页面，直接访问登陆后可以访问的页面
                .and().rememberMe()
                .rememberMeServices(rememberMeServices()).key("INTERNAL_SECRET_KEY")
                .and().csrf().disable();
                //自定义匹配器，方便排除那些不需要csrf防御的地址
//                .requireCsrfProtectionMatcher(csrfSecurityRequestMatcher())
//                .csrfTokenRepository(new HttpSessionCsrfTokenRepository());

        /**
         *  1、处理AuthenticationException
         *  2、处理AccessDeniedException且用户不是匿名用户
         *  例如：用户注册之后没有激活提示DisabledException
         */
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
    }


    /**
     * 设置remember-me属性
     *
     * @return
     */
    @Bean
    public TokenBasedRememberMeServices rememberMeServices() {
        //此处的 key 可以为任意非空值(null 或 "")，单必须和起前面
        //rememberMeServices(RememberMeServices rememberMeServices).key(key)的值相同
        TokenBasedRememberMeServices tokenBasedRememberMeServices = new TokenBasedRememberMeServices("INTERNAL_SECRET_KEY", customUserService);
        tokenBasedRememberMeServices.setParameter("remember-me");
        //有效期一个月
        tokenBasedRememberMeServices.setTokenValiditySeconds(31 * 24 * 60 * 60);
        return tokenBasedRememberMeServices;
    }



    /**
     * 资源角色授权器
     */
    @Autowired
    private FilterInvocationSecurityMetadataSourceImpl filterInvocationSecurityMetadataSource;

    /**
     * 决策管理器
     */
    @Autowired
    private AccessDecisionManagerImpl accessDecisionManager;

    /**
     * 身份认证管理器
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 自定义拦截器注入
     *
     * @return
     */
    @Bean
    public FilterSecurityInterceptor filterSecurityInterceptor(){
        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setSecurityMetadataSource(filterInvocationSecurityMetadataSource);
        filterSecurityInterceptor.setAccessDecisionManager(accessDecisionManager);
        filterSecurityInterceptor.setAuthenticationManager(authenticationManager);
        return filterSecurityInterceptor;
    }


    /**
     * 错误页面处理
     *
     * @return
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return new AuthenticationEntryPoint() {
            String errorPage = "/noAccess.html";
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                if (!response.isCommitted()) {
                    if (errorPage != null) {
                        // Put exception into request scope (perhaps of use to a view)
                        request.setAttribute(WebAttributes.ACCESS_DENIED_403, authException);
                        // Set the 403 status code.
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        // forward to error page.
                        RequestDispatcher dispatcher = request.getRequestDispatcher(errorPage);
                        dispatcher.forward(request, response);
                    }
                    else {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, authException.getMessage());
                    }
                }
            }
        };
    }


    /**
     * 自定义CSRF注入
     *
     * @return
     */
    @Bean
    public CSRFSecurityRequestMatcher csrfSecurityRequestMatcher(){
        Set<String> excludedUrls = new HashSet<>();
        excludedUrls.add("/vip/");
        CSRFSecurityRequestMatcher csrfSecurityRequestMatcher = new CSRFSecurityRequestMatcher();
        csrfSecurityRequestMatcher.setExcludedUrls(excludedUrls);
        return csrfSecurityRequestMatcher;
    }


    @Override
    public void onApplicationEvent(RoleResourceUpdateEvent event) {
        filterInvocationSecurityMetadataSource.updateRoleResource(event);
    }

}
