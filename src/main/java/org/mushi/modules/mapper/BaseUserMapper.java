package org.mushi.modules.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mushi.modules.model.BaseUser;

/**
 * TODO
 **/
@Mapper
public interface BaseUserMapper {

    @Select("select * from user where username=#{username}")
    BaseUser findByUserName(@Param("username") String username);


}
