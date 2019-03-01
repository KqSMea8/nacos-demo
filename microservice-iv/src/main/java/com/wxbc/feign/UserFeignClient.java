package com.wxbc.feign;

import com.wxbc.pojo.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "microservice-account",fallback = UserFeignClientHystrix.class)
public interface UserFeignClient {

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    UserInfo getUserInfoByName(@RequestParam("name") String name);
}
