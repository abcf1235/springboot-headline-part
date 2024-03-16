package com.atguigu.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName news_user
 */
//@TableName(value ="news_user")
@Data
public class User implements Serializable {
    private Integer uid;

    private String username;

    private String userPwd;

    private String nickName;

    @Version  //乐观锁
    private Integer version;

    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}