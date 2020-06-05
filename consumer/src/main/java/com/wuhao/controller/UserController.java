package com.wuhao.controller;

import com.wuhao.domain.User;
import com.wuhao.redis.RedisTemplateService;
import com.wuhao.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    @Autowired
    RedisTemplateService redisTemplateService;

    @Reference(version = "1.0.0")
    private UserService userServiceImpl;

    /**
     * 用户查询
     * @return
     */
    @RequestMapping("/getUser")
    public String getUserById(){
        Long id = 1L;
        String redisKey = "USER:ID:"+ id;
        User cacheUser = redisTemplateService.get(redisKey, User.class);
        if(cacheUser == null){
            log.info("redis 缓存没数据, 从数据库查询...");
            User dbUser = userServiceImpl.getUserById(id);
            redisTemplateService.set(redisKey,dbUser);
            return dbUser.toString();
        }
        log.info("redis 缓存有数据, 从redis中查询...");
        return cacheUser.toString();
    }
}
