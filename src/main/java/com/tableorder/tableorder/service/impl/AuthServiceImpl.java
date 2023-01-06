package com.tableorder.tableorder.service.impl;

import com.tableorder.tableorder.entity.Role;
import com.tableorder.tableorder.mapper.UserMapper;
import com.tableorder.tableorder.model.UserModel;
import com.tableorder.tableorder.service.AuthService;
import com.tableorder.tableorder.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final UserMapper userMapper;

    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(
            UserService userService,
            UserMapper userMapper,
            AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserModel registerGuestUser(UserModel userModel, HttpServletRequest request) {
        userModel.setRoles(Arrays.asList(Role.GUEST));
        UserModel newUser = userService.save(userModel);
        login(userModel, request);
        return newUser;
    }

    @Override
    public void login(UserModel userModel, HttpServletRequest request){
        UserDetails userDetails = userMapper.modelToUserDetails(userModel);
        login(userDetails, request);
    }

    private void login(UserDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        request.getSession();
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication auth = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

}
