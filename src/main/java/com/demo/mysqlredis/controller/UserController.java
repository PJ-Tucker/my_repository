package com.demo.mysqlredis.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.mysqlredis.model.User;
import com.demo.mysqlredis.service.RedisService;
import com.demo.mysqlredis.service.UserService;
import com.demo.mysqlredis.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangchunguang
 * @data 2020/12/23 10:22 下午
 */
@RestController
@RequestMapping("/demo")
public class UserController {
    private static Logger loggger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "/query", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public ResultVo queryUserById(Long id) {
        return userService.queryUserById(id);
    }

    @RequestMapping(value = "/redis", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public ResultVo redisCache() {
        ResultVo user = userService.queryUserById(1L);
        redisService.set(user.getEmail(), JSON.toJSONString(user));
        JSONObject object = JSONObject.parseObject(redisService.get(user.getEmail()));
        ResultVo ret = new ResultVo();
        ret.setId(object.getLong("id"));
        ret.setEmail(object.getString("email"));
        ret.setName(object.getString("name"));
        ret.setAge(object.getInteger("age"));
        return ret;
    }
}
