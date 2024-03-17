package com.atguigu.controller;


import com.atguigu.mapper.HeadlineMapper;
import com.atguigu.pojo.vo.PortalVo;
import com.atguigu.pojo.Type;
import com.atguigu.service.HeadlineService;
import com.atguigu.service.TypeService;
import com.atguigu.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("portal")
@RestController
public class PortalController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private HeadlineService headlineService;

    @GetMapping("findAllTypes")
    public Result findAllTypes(){
        Result result = typeService.findAllTypes();
        return result;
    }

    /**
     * 查询全部类别信息
     * @return
     */
    @GetMapping("findAllTypes2")
    public Result findAllTypes2(){
        //直接调用业务层,查询全部数据
        List<Type> list = typeService.list();
        return  Result.ok(list);
    }


    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo){
        Result result = headlineService.findNewsPage(portalVo);
        return result;
    }

    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(@Param("hid") Integer hid){
        Result result = headlineService.showHeadlineDetail(hid);
        return result;
    }
}
