package org.mushi.support.integration.authenticator;


import org.mushi.support.integration.IntegrationAuthenticationEntity;
import org.mushi.modules.model.BaseUser;
import org.mushi.modules.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
@Primary
public class UsernamePasswordAuthenticator extends AbstractPreparableIntegrationAuthenticator {

    @Autowired
    private IUserService userService;

    @Override
    public BaseUser authenticate(IntegrationAuthenticationEntity entity) {
        String name = entity.getAuthParameter("username");
        String pwd = entity.getAuthParameter("password");
        if(name == null || pwd == null){
            throw new OAuth2Exception("用户名或密码不能为空");
        }
        BaseUser pojo = userService.findByUserName(name);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(encoder != null && encoder.matches(pwd,pojo.getPassword())){
            return pojo;
        }
        return null;
    }

    @Override
    public boolean support(IntegrationAuthenticationEntity entity) {
        return StringUtils.isEmpty(entity.getAuthType());
    }
}
