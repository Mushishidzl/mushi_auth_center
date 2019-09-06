package org.mushi.support;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TODO 认证授权服务 14000-14999
 **/
@Getter
@AllArgsConstructor
public enum CommonAuthEnum {

    NOLOGINNAME(14001,"no username");

    /**
     * 状态码
     */
    private Integer code;


    /**
     * 描述
     */
    private String description;
}
