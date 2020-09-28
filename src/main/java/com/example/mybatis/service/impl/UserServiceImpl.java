package com.example.mybatis.service.impl;

import com.example.mybatis.dao.UserDAO;
import com.example.mybatis.dataobject.UserDO;
import com.example.mybatis.model.Result;
import com.example.mybatis.model.User;
import com.example.mybatis.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public Result<User> register(String userName, String pwd) {
        Result<User> userResult = new Result<>();
        if (StringUtils.isEmpty(userName)) {

            userResult.setCode("600");
            userResult.setMessage("用户名不能为空");
            return userResult;
        }
        if (StringUtils.isEmpty(pwd)) {
            userResult.setCode("601");
            userResult.setMessage("密码不能为空");
            return userResult;
        }
        UserDO userDO = userDAO.findByUserName(userName);
        if (userDO != null) {
            userResult.setCode("602");
            userResult.setMessage("用户名已经存在");
            return userResult;
        }
        // 密码加自定义盐值，确保密码安全
        String saltPwd = pwd + "_ykd2050";
        // 生成md5值，并转小写字母
        String md5Pwd = DigestUtils.md5Hex(saltPwd).toUpperCase();
        UserDO newUser = new UserDO();
        newUser.setUserName(userName);
        newUser.setPwd(md5Pwd);
        userDAO.add(newUser);
        userResult.setSuccess(true);

        User user = newUser.toModel();
        userResult.setData(user);
        return userResult;
    }

    @Override
    public Result<User> login(String userName, String pwd) {
        Result<User> userResult = new Result<>();

        if (StringUtils.isEmpty(userName)){
            userResult.setCode("600");
            userResult.setMessage("用户名不能为空");
            return userResult;
        }
        if (StringUtils.isEmpty(pwd)) {
            userResult.setCode("601");
            userResult.setMessage("密码不能为空");
            return userResult;
        }
        UserDO userDO = userDAO.findByUserName(userName);
        if (userDO == null) {
            userResult.setCode("602");
            userResult.setMessage("用户名不存在");
            return userResult;
        }
        // 密码加自定义盐值，确保密码安全
        String saltPwd = pwd + "_ykd2050";
        // 生成md5值，并转小写字母
        String md5Pwd = DigestUtils.md5Hex(saltPwd).toUpperCase();
        if (!md5Pwd.equals(userDO.getPwd())) {
            userResult.setCode("603");
            userResult.setMessage("密码不对");
            return userResult;
        }
        User user = userDO.toModel();
        userResult.setData(user);
        userResult.setSuccess(true);

        return userResult;
    }


}
