package com.atguigu.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName news_headline
 */
//@TableName(value ="news_headline")  application.yaml统一配置了表前缀，所以不需要每一个类单独指定
@Data
public class Headline implements Serializable {
    private Integer hid;

    private String title;

    private String article;

    private Integer type;

    private Integer publisher;

    private Integer pageViews;

    private Date createTime;

    private Date updateTime;

    @Version  //乐观锁
    private Integer version;

    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}