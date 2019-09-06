package org.mushi.configurer;




import org.mushi.support.BaseBasicAuthenticationFilter;
import org.mushi.support.integration.IntegrationUserDetailsService;
import org.mushi.support.OauthConstants;
import org.mushi.support.handler.CustomizeLoginFailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 **/
@Configuration
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private IntegrationUserDetailsService integrationUserDetailsService;




    private CustomizeLoginFailureHandler handler;


    public SecurityConfig(IntegrationUserDetailsService integrationUserDetailsService, CustomizeLoginFailureHandler handler) {
        this.integrationUserDetailsService = integrationUserDetailsService;
        this.handler = handler;
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(integrationUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                // http security 要拦截的url，这里这拦截，oauth2相关和登录登录相关的url，其他的交给资源服务处理
                .requestMatchers()
                .antMatchers( "/oauth/**", OauthConstants.LOGIN_PAGE,OauthConstants.LOGIN_PROCESS_URL)
                .and()
                .authorizeRequests()
                // 自定义页面或处理url是，如果不配置全局允许，浏览器会提示服务器将页面转发多次
                .antMatchers("/auth/login", OauthConstants.LOGIN_PROCESS_URL)
                .permitAll()
                .anyRequest()
                .authenticated();

        // 表单登录
        http.formLogin()
                .failureHandler(handler)
                // 页面
                .loginPage(OauthConstants.LOGIN_PAGE)
                // 登录处理url
                .loginProcessingUrl(OauthConstants.LOGIN_PROCESS_URL);

        http.httpBasic().disable();
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


}
