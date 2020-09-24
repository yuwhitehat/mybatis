package com.example.mybatis.dao;

import com.example.mybatis.dataobject.UserDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDAO {


    /**
     * 不加注解，xml配置，插入
     */
    int add(UserDO userDO);

    /**
     * 修改
     * @param userDO
     * @return
     */
    int update(UserDO userDO);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(@Param("id") long id);

    /**
     * 查询所有
     * @return
     */
    List<UserDO> findAll();

    /**
     * 根据名字查询
     * @param name
     * @return
     */
    UserDO findByUserName(@Param("userName") String name);

    /**
     * 模糊查询，通过user_name或者nick_name查找用户
     * @param keyWord
     * @return
     */
    List<UserDO> query (@Param("keyWord") String keyWord);

}
