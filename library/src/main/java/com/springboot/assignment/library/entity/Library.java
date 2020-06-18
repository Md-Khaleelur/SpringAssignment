package com.springboot.assignment.library.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "library")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @NotNull()
    @Size(min = 3, message = "* required")
    @Column(name = "book_name")
    private String bookName;

    @NotNull()
    @Size(min = 1, message = "* required")
    @Column(name = "author")
    private String author;

    @NotNull()
    @Size(min = 3, message = "* required")
    @Column(name = "category")
    private String category;

    @NotNull()
    @Size(min = 5, message = "* required")
    @Column(name = "publisher")
    private String publisher;

    @NotNull()
    @Size(min = 5, message = "* required")
    @Column(name = "status")
    private String status;

}
