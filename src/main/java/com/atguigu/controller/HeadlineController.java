package com.atguigu.controller;

import com.atguigu.mapper.HeadlineMapper;
import com.atguigu.pojo.Headline;
import com.atguigu.service.HeadlineService;
import com.atguigu.utils.JwtHelper;
import com.atguigu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("headline")
public class HeadlineController {

    @Autowired
    private HeadlineService headlineService;

    @PostMapping("publish")
    public Result publish(@RequestBody Headline headline, @RequestHeader String token){
        Result result = headlineService.publish(headline,token);
        return result;
    }
}
