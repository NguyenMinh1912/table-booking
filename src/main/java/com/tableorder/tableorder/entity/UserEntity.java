package com.tableorder.tableorder.entity;

import com.tableorder.tableorder.entity.converter.ListRoleConverter;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    @Convert(converter = ListRoleConverter.class)
    private List<Role> roles = new ArrayList<>();
}
