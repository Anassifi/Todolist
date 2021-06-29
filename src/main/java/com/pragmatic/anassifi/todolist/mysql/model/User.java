package com.pragmatic.anassifi.todolist.mysql.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
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
    private String username;
    
    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")
    @Column(nullable = false)
    private String password;
    
    @NotNull
    @Email
    private String email;

    @OneToMany(mappedBy="user")
    private List<Todo> todo;
}
