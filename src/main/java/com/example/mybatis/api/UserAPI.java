package com.example.mybatis.api;

import com.example.mybatis.model.Result;
import com.example.mybatis.model.User;
import com.example.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserAPI {

    @Autowired
    private UserService userService;

    @PostMapping("api/user/reg")
    @ResponseBody
    public Result<User> userReg(@RequestParam("userName") String userName, @RequestParam("pwd") String pwd){
        return userService.register(userName, pwd);
    }


    @PostMapping("api/user/login")
    @ResponseBody
    public Result<User> userLogin(@RequestParam("userName") String userName, @RequestParam("pwd") String pwd,
                                  HttpServletRequest request) {
        Result<User> userResult = userService.login(userName, pwd);
        if (userResult.isSuccess()) {
            request.getSession().setAttribute("userId", userResult.getData().getId());
        }
        return userResult;
    }


    @GetMapping("api/user/logout")
    @ResponseBody
    public Result userLogout(@RequestParam("userName") String userName, @RequestParam("pwd") String pwd,HttpServletRequest request) {

        request.getSession().removeAttribute("userId");
        Result result = new Result();
        result.setSuccess(true);
        return result;
    }

}
