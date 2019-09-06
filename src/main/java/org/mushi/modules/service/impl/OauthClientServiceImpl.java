package org.mushi.modules.service.impl;



import org.mushi.modules.mapper.OauthClientMapper;
import org.mushi.modules.model.OauthClient;
import org.mushi.modules.service.IOauthClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 实现类
 **/
@Service
public class OauthClientServiceImpl implements IOauthClientService {

    @Autowired
    private OauthClientMapper oauthClientMapper;

    @Override
    public OauthClient findClientByClientId(String clientId) {
        OauthClient oauthClient = new OauthClient();
        oauthClient.setClientId(clientId);
        return oauthClientMapper.findClientByClientId(oauthClient);
    }
}
