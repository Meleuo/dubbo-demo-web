package com.umfb.web.app.controller.photo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.umfb.commons.ResponseResult;
import com.umfb.demo.entity.edu.Photo;
import com.umfb.demo.service.edu.IPhotoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zcah on 18-7-21.
 */

@Controller
@RequestMapping("/photo")
public class photoController {
    private final static Logger logger = LoggerFactory.getLogger(photoController.class);

    @Reference
    IPhotoService photoService;



    @RequestMapping("/randomHanYuBiHuaPhoto")
    @ResponseBody
    public ResponseResult randomHanYuBiHuaPhoto(){

        ResponseResult rslt = new ResponseResult();
        Map<String, Object> map = new HashMap<>();
        List<Photo> photos = photoService.randomHanYuBiHua("469794814246780928");

        rslt.setResultBean(photos);

        return rslt;
    }
}
