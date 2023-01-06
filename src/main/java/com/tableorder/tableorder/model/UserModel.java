package com.tableorder.tableorder.model;

import com.tableorder.tableorder.entity.Role;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class UserModel {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private List<Role> roles = new ArrayList<>();
}
