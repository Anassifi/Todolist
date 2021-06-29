package com.pragmatic.anassifi.todolist.postgreSQL.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostgresTodo {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private PostgresUser postgresUser;

    public PostgresTodo(Long id, String title, String description, boolean status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }
}
