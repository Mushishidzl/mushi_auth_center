package org.mushi.modules.service;


import org.mushi.modules.model.BaseUser;

/**
 *
 **/
public interface IUserService {

    BaseUser findByUserName(String username);

    BaseUser findByUsername(String username);

    BaseUser findByMobile(String mobile);

    BaseUser getUserByQrCode(String qrCode);
}
