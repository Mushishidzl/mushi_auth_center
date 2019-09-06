package org.mushi.support.service;

import org.mushi.modules.model.OauthClient;
import org.mushi.modules.service.IOauthClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

/**
 *  自定义的OauthClientDetailsService
 **/
@Component
public final class OauthClientDetailsService implements ClientDetailsService {

    @Autowired
    private IOauthClientService clientService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        OauthClient client = this.clientService.findClientByClientId(clientId);
        if(client==null){
            throw new ClientRegistrationException("客户端不存在");
        }
        // TODO 简化模式和授权码模式校验用户是否对该应用有权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken) && authentication != null) {
            String currentUserName = authentication.getName();
            System.out.print("++++++++++++++++++++++++++++++++++++++++++++"+currentUserName);
        }
        // 构建自定义的OauthClientDetails
        OauthClientDetails details=new OauthClientDetails(client);
        return details;
    }

}
