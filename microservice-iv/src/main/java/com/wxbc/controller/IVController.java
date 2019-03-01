package com.wxbc.controller;

import com.wxbc.feign.UserFeignClient;
import com.wxbc.pojo.UserInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/iv")
@CrossOrigin(origins = "*", maxAge = 3600)
public class IVController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());


    @Autowired
    private UserFeignClient userFeignClient;

    @ResponseBody
    @ApiOperation(value = "getIV", notes = "getIV",
            response = UserInfo.class)
    @RequestMapping(value = "/getIV/{ivAddress}", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    public UserInfo getIV(@PathVariable("ivAddress") String ivAddress) {
        logger.info("start to getIV");
        UserInfo tom = userFeignClient.getUserInfoByName("tom");
        return tom;
    }


}
