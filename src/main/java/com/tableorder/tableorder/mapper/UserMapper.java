package com.tableorder.tableorder.mapper;

import com.tableorder.tableorder.entity.UserEntity;
import com.tableorder.tableorder.model.UserModel;
import org.mapstruct.Mapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserModel entityToModel(UserEntity userEntity);

    UserEntity modelToEntity(UserModel user);

    default UserDetails modelToUserDetails(UserModel userModel){
        List<SimpleGrantedAuthority> roles = userModel.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
        return new User(userModel.getUsername(), userModel.getPassword(), roles);
    }

}
