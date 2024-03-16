package com.atguigu.service;

import com.atguigu.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 111
* @description 针对表【news_user】的数据库操作Service
* @createDate 2024-03-16 10:26:39
*/
public interface UserService extends IService<User> {

    User findUserByName(String username);
}
