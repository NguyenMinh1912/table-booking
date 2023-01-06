package com.tableorder.tableorder.service.impl;

import com.tableorder.tableorder.entity.UserEntity;
import com.tableorder.tableorder.exceptions.EntityConfligException;
import com.tableorder.tableorder.mapper.UserMapper;
import com.tableorder.tableorder.model.UserModel;
import com.tableorder.tableorder.repository.UserRepository;
import com.tableorder.tableorder.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserModel findUserByUsername(String username) {
        Optional<UserEntity> userEntity = userRepository.findUserByUsername(username);
        return userEntity.map(u -> userMapper.entityToModel(u))
                .orElseThrow();
    }

    @Override
    public UserModel save(UserModel user) {
        Optional<UserEntity> userEntity = userRepository.findUserByUsername(user.getUsername());
        if (userEntity.isPresent()){
            throw new EntityConfligException("Username already exist");
        }
        UserEntity newUserEntity = userMapper.modelToEntity(user);
        userRepository.save(newUserEntity);
        user.setId(newUserEntity.getId());
        return user;
    }


}
