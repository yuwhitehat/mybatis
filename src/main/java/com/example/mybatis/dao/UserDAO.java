package com.example.mybatis.dao;

import com.example.mybatis.dataobject.UserDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDAO {

    @Select("select id, user_name as user,pwd, nick_name as nickName, avatar,gmt_Created as gmtCreated,gmt_modified as gmtModified from user")
    List<UserDO> findAll();

    @Insert("insert into user (use_name, pwd, nick_name, avatar, gmt_created, gmt_modified) values (#{userName}, #{pwd}, #{nickName}, #{avatar}, now(), now())")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insert(UserDO userDO);

    @Update("update user set nick_name=#{nickName},gmt_modified=now() where id=#{id}")
    int update(UserDO userDO);

    @Delete("delete from user where id=#{id}")
    int delete(@Param("id") long id);

    @Select("select id, user_name as userName, pwd, nick_name as nickName, avatar, gmt_created as gmtCreated, gmt_modified as gmtModified from user where user_name=#{userName} limit 1")
    UserDO findByUserName(@Param("userName") String name);


}
