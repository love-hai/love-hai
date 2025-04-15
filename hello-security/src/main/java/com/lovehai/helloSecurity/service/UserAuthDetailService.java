package com.lovehai.helloSecurity.service;

import com.lovehai.helloSecurity.entity.UserAuthDetail;
import com.lovehai.helloSecurity.entity.UserAuthDetailExample;
import com.lovehai.helloSecurity.mapper.UserAuthDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author xiahaifeng
 */
@Service
public class UserAuthDetailService {
    private UserAuthDetailMapper userAuthDetailMapper;

    @Autowired
    public UserAuthDetailService(UserAuthDetailMapper userAuthDetailMapper) {
        this.userAuthDetailMapper = userAuthDetailMapper;
    }

    Optional<UserAuthDetail> findByUsername(String username) {
        UserAuthDetailExample example = new UserAuthDetailExample();
        example.createCriteria().andUsernameEqualTo(username);
        UserAuthDetail userAuthDetail = userAuthDetailMapper.selectOneByExample(example);
        return Optional.ofNullable(userAuthDetail);
    }

    public int insert(UserAuthDetail userAuthDetail) {
        return userAuthDetailMapper.insert(userAuthDetail);
    }
}