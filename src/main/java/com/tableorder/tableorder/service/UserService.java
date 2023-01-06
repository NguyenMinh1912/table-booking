package com.tableorder.tableorder.service;

import com.tableorder.tableorder.model.UserModel;

public interface UserService {
    UserModel findUserByUsername(String username);

    UserModel save(UserModel user);
}
