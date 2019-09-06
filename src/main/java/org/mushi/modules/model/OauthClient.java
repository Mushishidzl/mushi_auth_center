package org.mushi.modules.model;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *  Oauth应用
 **/
@Data
public class OauthClient  implements Serializable {

    /**
     * 应用id
     */
    private String id;

    /**
     * 应用编码
     */
    private String clientId;

    /**
     * 应用名称
     */
    private String clientName;

    /**
     * 应用logo图片
     */
    private String clientLogo;

    /**
     * 应用类型
     */
    private String clientType;

    /**
     * 应用地址
     */
    private String clientAddress;

    /**
     * 授权方式 refresh_token,authorization_code,password,client_credentials,implicit
     */
    private String grantType;

    /**
     * 应用密码
     */
    private String clientSecret;

    /**
     * 是否同步组织 0:不同步,1:同步
     */
    private Integer syncOrg;

    /**
     * 同步组织URL
     */
    private String syncOrgUrl;

    /**
     * 是否同步用户 0:不同步,1:同步
     */
    private Integer syncUser;

    /**
     * 同步用户URL
     */
    private String syncUserUrl;

    /**
     * 同步数据签名算法 1:SM3,2:SHA256
     */
    private Integer syncAlgo;

    /**
     * 状态 0:禁用,1:启用
     */
    private Integer clientStatus;

    /**
     * 描述信息
     */
    private String note;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;


}
