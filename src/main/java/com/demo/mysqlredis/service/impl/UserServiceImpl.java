package com.demo.mysqlredis.service.impl;

import com.demo.mysqlredis.dao.UserMapper;
import com.demo.mysqlredis.model.User;
import com.demo.mysqlredis.service.UserService;
import com.demo.mysqlredis.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangchunguang
 * @data 2020/12/23 10:26 下午
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultVo queryUserById(Long id) {
        User user = userMapper.queryUserById(id);
        ResultVo ret = new ResultVo();
        ret.setId(user.getId());
        ret.setName(user.getName());
        ret.setAge(user.getAge());
        ret.setEmail(user.getEmail());
        return ret;
    }
}
