package com.demo.mysqlredis.service;

import com.demo.mysqlredis.model.User;
import com.demo.mysqlredis.vo.ResultVo;

/**
 * @author zhangchunguang
 * @data 2020/12/23 10:24 下午
 *
 * 只是接口 说明其用处即可 数据库逻辑需要在 serviceImpl实现
 */
public interface UserService {
    ResultVo queryUserById(Long id);
}
