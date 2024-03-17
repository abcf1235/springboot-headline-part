package com.atguigu.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.atguigu.mapper.HeadlineMapper;
import com.atguigu.pojo.Headline;
import com.atguigu.pojo.vo.PortalVo;
import com.atguigu.service.HeadlineService;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.pojo.Type;
import com.atguigu.service.TypeService;
import com.atguigu.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 111
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2024-03-16 10:26:39
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService{

    @Autowired
    private TypeMapper typeMapper;
    
    @Override
    public Result findAllTypes() {
        List<Type> types = typeMapper.selectList(null);
        System.out.println(types);
        Result result = Result.ok(types);
        return result;
    }

}




