package com.atguigu.service;

import com.atguigu.pojo.Headline;
import com.atguigu.pojo.vo.PortalVo;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 111
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-03-16 10:26:39
*/
public interface HeadlineService extends IService<Headline> {

    Result findNewsPage(PortalVo portalVo);

    Result showHeadlineDetail(Integer hid);

    Result publish(Headline headline, String token);

    Result findHeadlineByHid(Integer hid);

    Result updateHeadline(Headline headline);

    Result removeByHid(Integer hid);
}
