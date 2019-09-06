package org.mushi.support.integration.authenticator;


import org.mushi.support.integration.IntegrationAuthenticationEntity;
import org.mushi.modules.model.BaseUser;
import org.mushi.modules.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
public class SmsAuthenticator extends AbstractPreparableIntegrationAuthenticator {

    private final String  AUTH_TYPE = "sms";

    @Autowired
    private IUserService userService;

    @Override
    public BaseUser authenticate(IntegrationAuthenticationEntity entity) {
        String mobile = entity.getAuthParameter("mobile");
        if(StringUtils.isEmpty(mobile)){
            throw new OAuth2Exception("手机号不能为空");
        }
        String code = entity.getAuthParameter("code");
        //测试项目
        if(! "1234".equals(code)){
            throw new OAuth2Exception("验证码错误或已过期");
        }
        BaseUser byMobile = userService.findByMobile(mobile);
        return byMobile;
    }

    @Override
    public boolean support(IntegrationAuthenticationEntity entity) {
        return AUTH_TYPE.equals(entity.getAuthType());
    }
}
