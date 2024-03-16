package com.atguigu.controller;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.utils.MD5Util;
import com.atguigu.utils.Result;
import com.atguigu.utils.ResultCodeEnum;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Result login(@Param("username") String username,@Param("userPwd") String userPwd){
        String userPwd = MD5Util.encrypt(userPwd);
        User user = userService.findUserByName(username);
        if(user.getUserPwd() == userPwd){
            Result ok = Result.ok(null);
            return ok;
        }
        Result fail = Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
        return fail;
    }
}
