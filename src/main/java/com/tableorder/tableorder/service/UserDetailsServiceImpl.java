package com.tableorder.tableorder.service;

import com.tableorder.tableorder.mapper.UserMapper;
import com.tableorder.tableorder.model.UserModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserDetailsServiceImpl(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userService.findUserByUsername(username);
        UserDetails user = userMapper.modelToUserDetails(userModel);
        return user;
    }
}
