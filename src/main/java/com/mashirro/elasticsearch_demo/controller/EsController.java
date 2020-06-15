package com.mashirro.elasticsearch_demo.controller;


import com.mashirro.elasticsearch_demo.pojo.Result;
import com.mashirro.elasticsearch_demo.pojo.Xfjxx;
import com.mashirro.elasticsearch_demo.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/es")
public class EsController {

    @Autowired
    private EsService esService;

    @RequestMapping("/insert")
    @ResponseBody
    public Result insertEs() {
        try {
            Xfjxx xfjxx = new Xfjxx();
            xfjxx.setId(UUID.randomUUID().toString());
            xfjxx.setXfjbh("LX440309202006028431");
            xfjxx.setDjjgdm("4401030000000000499");
            xfjxx.setDjr("gd_xf_bly");
            xfjxx.setDjsj(new Date());
            xfjxx.setXfxs("100");
            String[] glxfjbhArray = new String[]{"LX440000202005308029"};
            xfjxx.setGlxfjbhArray(glxfjbhArray);
            esService.insertEs("my_xfjxx", "xfjxx", xfjxx, xfjxx.getId());
            return Result.success("插入es成功", null);
        } catch (Exception e) {
            return Result.error("插入es出错!");
        }
    }
}
