package org.mushi.modules.service.impl;



import org.mushi.modules.mapper.BaseUserMapper;
import org.mushi.modules.model.BaseUser;
import org.mushi.modules.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 实现类
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private BaseUserMapper baseUserMapper;

    @Override
    public BaseUser findByUserName(String username) {
        return baseUserMapper.findByUserName(username);
    }

    @Override
    public BaseUser findByUsername(String username) {
        return findByUserName(username);
    }

    @Override
    public BaseUser findByMobile(String mobile) {
        return findByUserName("admin");
    }

    @Override
    public BaseUser getUserByQrCode(String qrCode) {
        return findByUserName("admin");
    }
}
