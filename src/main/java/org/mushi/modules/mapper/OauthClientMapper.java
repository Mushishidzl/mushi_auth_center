package org.mushi.modules.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mushi.modules.model.OauthClient;

/**
 *
 */
@Mapper
public interface OauthClientMapper {


    OauthClient findClientByClientId(OauthClient oauthClient);
}
