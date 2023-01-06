package com.tableorder.tableorder.service;

import com.tableorder.tableorder.model.UserModel;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {

    UserModel registerGuestUser(UserModel appUserDetails, HttpServletRequest request);

    void login(UserModel userDetails, HttpServletRequest httpServletRequest);

}
