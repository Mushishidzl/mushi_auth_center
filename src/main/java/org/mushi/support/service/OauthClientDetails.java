package org.mushi.support.service;

import lombok.Data;
import org.mushi.modules.model.OauthClient;
import org.mushi.utils.CommonUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 *
 **/
@Data
public final class OauthClientDetails implements ClientDetails {

    private OauthClient client;
    private Set<String> scope;

    public OauthClientDetails(OauthClient client) {
        this.client = client;
    }

    public OauthClientDetails() {
    }

    @Override
    public String getClientId() {
        return client.getClientId();
    }

    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    @Override
    public boolean isSecretRequired() {
        return false;
    }


    @Override
    public String getClientSecret() {
        return client.getClientSecret();
    }

    @Override
    public boolean isScoped() {
        return false;
    }


    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return client.getGrantType()!=null?
                CommonUtils.transformStringToSet(client.getGrantType(),String.class):null;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return client.getClientAddress()!=null?
                CommonUtils.transformStringToSet(client.getClientAddress(),String.class):null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return (
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin,client"));
    }


    @Override
    public Integer getAccessTokenValiditySeconds() {
        return 1800;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return 36000;
    }


    @Override
    public boolean isAutoApprove(String scope) {
        return false;
    }


    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
