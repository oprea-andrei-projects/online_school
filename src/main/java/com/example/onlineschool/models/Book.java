package com.example.onlineschool.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity(name = "Book")
@Table(name = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @SequenceGenerator(
            name = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy =GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    Long id;


    @Column(

            name ="title",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotEmpty(message = "Title must be filled !")
    String title;

    @Column(

            name = "created_at",
            nullable = false,
            columnDefinition = "DATE"
    )
    @NotEmpty(message = "Date must be specified !")
    LocalDate created_at;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(
        name ="user_id",
        referencedColumnName = "id",
        foreignKey =@ForeignKey(
            name ="user_id_fk"
        )
    )
    private User user;

    public Book(String title, LocalDate created_at) {

        this.title = title;
        this.created_at = created_at;

    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", created_at=" + created_at +
                ", user=" + user +
                '}';
    }
}
