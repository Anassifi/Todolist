package com.pragmatic.anassifi.todolist.postgreSQL.model;

import lombok.*;

import javax.persistence.*;


import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PostgresUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;

    @OneToMany(mappedBy="postgresUser")
    private List<PostgresTodo> postgresTodo;
}
