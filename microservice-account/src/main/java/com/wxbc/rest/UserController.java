package com.wxbc.rest;

import com.wxbc.pojo.UserInfo;
import com.wxbc.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RefreshScope
public class UserController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Autowired
    private UserService userService;

    @Value("${server.port}")
    private String port;

    @ResponseBody
    @RequestMapping(value = "/getUserInfo", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    public UserInfo getUserInfo(@RequestParam("name") String name) {
        logger.info("start to getUserInfo: " + port);
        UserInfo userInfo = userService.getUserInfo(name);
        return userInfo;
    }


    @Value("${userName:foo}")
    private String userName;


    @ResponseBody
    @RequestMapping(value = "/get", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    @ApiOperation(value = "getConfig", notes = "getConfig",
            response = UserInfo.class)
    public UserInfo get() {
        logger.info(userName + "");
        return new UserInfo();
    }
}
