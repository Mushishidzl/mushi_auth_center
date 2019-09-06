package org.mushi.modules.service;

import org.apache.ibatis.annotations.Param;
import org.mushi.modules.model.OauthClient;

/**
 * oauth应用
 **/
public interface IOauthClientService  {

    OauthClient findClientByClientId(@Param("clientId") String clientId);
}
