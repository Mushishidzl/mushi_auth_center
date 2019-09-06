package org.mushi.configurer;


import org.mushi.support.BaseBasicAuthenticationFilter;
import org.mushi.support.integration.IntegrationAuthenticationFilter;
//import org.mushi.support.filter.BaseBasicAuthenticationFilter;
import org.mushi.support.oauth2.BaseOAuth2WebResponseExceptionTranslator;
import org.mushi.support.service.OauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 *  配置
 **/
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private AuthenticationManager authenticationManager;

    private OauthClientDetailsService clientDetailsService;

    private BaseBasicAuthenticationFilter filter;

    private TokenStore tokenStore;

    private AuthenticationEntryPoint authenticationEntryPoint;

    private BaseOAuth2WebResponseExceptionTranslator baseWebResponseExceptionTranslator;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return "";
            }
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return true;
            }
        };
    }

    private IntegrationAuthenticationFilter integrationAuthenticationFilter;

    private UserDetailsService userDetailsService;

    @Autowired(required = false)
    public OAuth2AuthorizationServerConfig(AuthenticationManager authenticationManager, OauthClientDetailsService clientDetailsService,
                                           TokenStore tokenStore, AuthenticationEntryPoint authenticationEntryPoint,
                                           BaseOAuth2WebResponseExceptionTranslator baseWebResponseExceptionTranslator,
                                           BaseBasicAuthenticationFilter filter,IntegrationAuthenticationFilter integrationAuthenticationFilter) {
        this.authenticationManager = authenticationManager;
        this.clientDetailsService = clientDetailsService;
        this.tokenStore = tokenStore;
        this.filter= filter;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.baseWebResponseExceptionTranslator = baseWebResponseExceptionTranslator;
        this.integrationAuthenticationFilter = integrationAuthenticationFilter;
    }

    public OAuth2AuthorizationServerConfig() {
        super();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 允许表单登录
        security.allowFormAuthenticationForClients();
        // 自定义异常处理端口
        security.authenticationEntryPoint(authenticationEntryPoint);
        filter.setClientDetailsService(clientDetailsService);
        security.addTokenEndpointAuthenticationFilter(filter);
        // 客户端认证之前的过滤器
        security.addTokenEndpointAuthenticationFilter(integrationAuthenticationFilter);
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 配置加载客户端的service
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints
                // token 存储方式
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                // 不配置会导致token无法刷新
                .userDetailsService(userDetailsService)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST, HttpMethod.GET);

        // 处理 ExceptionTranslationFilter 抛出的异常
        endpoints.exceptionTranslator(baseWebResponseExceptionTranslator);

        endpoints.pathMapping("/oauth/confirm_access","/custom/confirm_access");
    }

}
