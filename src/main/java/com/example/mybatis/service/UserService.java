package com.example.mybatis.service;

import com.example.mybatis.model.Result;
import com.example.mybatis.model.User;

public interface UserService {

    /**
     * 用户注册
     * @param userName
     * @param pwd
     * @return
     */
    public Result<User> register(String userName, String pwd);

    /**
     * 执行登录逻辑，登录成功返回User对象
     * @param userName
     * @param pwd
     * @return
     */
    public Result<User> login(String userName, String pwd);

}
