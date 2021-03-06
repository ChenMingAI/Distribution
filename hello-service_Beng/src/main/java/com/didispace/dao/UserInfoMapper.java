package com.didispace.dao;

import com.didispace.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository public interface UserInfoMapper {
    @Select("select * from user_info")
    List<UserInfo> selectAll();
}
