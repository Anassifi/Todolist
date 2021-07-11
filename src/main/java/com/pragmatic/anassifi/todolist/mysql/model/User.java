package com.pragmatic.anassifi.todolist.mysql.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "username"), @UniqueConstraint(columnNames = "email")})
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(min = 6, message = "Name should be more than 6 characters")
    @NotBlank(message = "Name is mandatory")
    @Column(unique = true, nullable = false)
    private String name;
    
    @NotBlank(message = "Username is mandatory")
    @Column(unique = true, nullable = false)
    @Size(max = 20)
    private String userName;
    
    @NotBlank(message = "Password is mandatory")
    @Size(max = 120)
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Size(max = 50)
    @NotNull
    @Email
    private String email;

    @OneToMany(mappedBy="user")
    private List<Todo> todo;

    private Boolean enabled = false;
}
