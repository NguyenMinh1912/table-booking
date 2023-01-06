package com.tableorder.tableorder.entity;

public enum Role {
    GUEST("guest"),
    ADMIN("admin");

    private String roleName;

    private Role(String roleName){
        this.roleName = roleName;
    }


    public String getRoleName() {
        return roleName;
    }
}
