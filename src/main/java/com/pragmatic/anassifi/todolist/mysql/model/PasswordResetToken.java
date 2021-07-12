package com.pragmatic.anassifi.todolist.mysql.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false)
//    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String repeatPassword;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime tokenCreationDate;

    @ManyToOne
    @JoinColumn(nullable = false, name
            = "user_id")
    private User user;

    public PasswordResetToken(String token, LocalDateTime tokenCreationDate, /* String email, */ String password, String repeatPassword, User user) {
        this.token = token;
        this.tokenCreationDate = tokenCreationDate;
//        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.user = user;
    }
}
