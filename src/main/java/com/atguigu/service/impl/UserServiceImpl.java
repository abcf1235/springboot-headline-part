package com.atguigu.service.impl;

import com.alibaba.druid.sql.ast.statement.SQLIfStatement;
import com.atguigu.utils.JwtHelper;
import com.atguigu.utils.MD5Util;
import com.atguigu.utils.Result;
import com.atguigu.utils.ResultCodeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* @author 111
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2024-03-16 10:26:39
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result login(User user) {
        //根据账号查询
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,user.getUsername());
        User loginUser = userMapper.selectOne(queryWrapper);
        if(loginUser == null){
            return Result.build(null,ResultCodeEnum.USERNAME_ERROR);
        }
        else {
            if(!StringUtils.isEmpty(user.getUserPwd())
                    && loginUser.getUserPwd().equals(MD5Util.encrypt(user.getUserPwd()))){
                String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));
                Map data = new HashMap();
                data.put("token",token);
                return Result.ok(data);
            }
            return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
        }
    }

//   根据token获取用户信息
    @Override
    public Result getUserInfo(String token) {
        //  判断是否在有效期
        if(jwtHelper.isExpiration(token)){
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }
        //2.获取token对应的用户
        int userId = jwtHelper.getUserId(token).intValue();

        //3.查询数据
        User user = userMapper.selectById(userId);

        if(user!=null){
            Map data = new HashMap();
            user.setUserPwd("");
            data.put("loginUser",user);
            return Result.ok(data);
        }
        return Result.build(null,ResultCodeEnum.NOTLOGIN);
    }

//    用户注册时检查用户名是否被占用
    @Override
    public Result checkUserName(String username) {
        //根据账号查询
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username);
        User loginUser = userMapper.selectOne(queryWrapper);
        if(loginUser == null){
            return Result.ok(null);
        }
        else {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
    }

//    客户端将新用户信息发送给服务端,服务端将新用户存入数据库,存入之前做用户名是否被占用校验,
//    校验通过响应成功提示,否则响应失败提示
    @Override
    public Result regist(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,user.getUsername());
        Long count = userMapper.selectCount(queryWrapper);

        if (count > 0){
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }

        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        int rows = userMapper.insert(user);
        System.out.println("rows = " + rows);
        return Result.ok(null);
    }
}




