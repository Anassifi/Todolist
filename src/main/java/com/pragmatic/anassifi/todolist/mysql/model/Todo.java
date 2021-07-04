package com.pragmatic.anassifi.todolist.mysql.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {

	@Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "todo_id", nullable = false, updatable = false, unique = true)
    private Long id;
    
    @NotBlank(message = "Title is mandatory")
    @Column(unique = true, nullable = false)
    private String title;
    
    @Lob
    @NotBlank(message = "Description is mandatory")
    @Column(nullable = true)
    private String description;
    
    private boolean status = false;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    
    public Todo(Long id, String title, String description, boolean status) {
    	this.id = id;
    	this.title = title;
    	this.description = description;
    	this.status = status;
	}


    public Todo(String title, String description, boolean status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }
}
