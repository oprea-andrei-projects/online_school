package com.example.onlineschool.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "User")
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @SequenceGenerator(
        name = "user_sequence",
        allocationSize = 1

    )
    @GeneratedValue(

        strategy = GenerationType.SEQUENCE,
        generator = "user_sequence"
    )
    Long id;

    @Column(

            name ="firstName",
            nullable = false,
            columnDefinition ="TEXT"
    )
    @NotEmpty(message = "First name must be filled !")
    String firstName;

    @Column(

            name ="lastName",
            nullable = false,
            columnDefinition ="TEXT"
    )
    @NotEmpty(message = "Last name must be filled !")
    String lastName;

    @Column(

            name ="email",
            nullable = false,
            columnDefinition ="TEXT"
    )
    @NotEmpty(message = "Email must be filled !")
    String email;

    @Column(

            name ="password",
            nullable = false,
            columnDefinition ="TEXT"
    )
    @NotEmpty(message = "Password must be filled !")
    String password;

    @Column(

            name ="age",
            nullable = false,
            columnDefinition ="INTEGER"
    )
    @NotEmpty(message = "Age must be filled !")
    int age;

    @JsonManagedReference
    @OneToMany(
        mappedBy = "user",
        cascade = {CascadeType.ALL},
        fetch = FetchType.EAGER,
        orphanRemoval = true
    )
    @JsonIgnore
    List<Book> studentBooks = new ArrayList<>();


    public void addBook(Book b){
        studentBooks.add(b);
        b.setUser(this);
    }



    @ManyToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "enrolled_courses",
                    joinColumns = {@JoinColumn(name = "user_id")},
                    inverseJoinColumns = {@JoinColumn(name = "course_id")}

                  )
    @JsonManagedReference
    @JsonIgnore
    private List<Course> courses = new ArrayList<>();

    public User(String firstName, String lastName, String email, String password, int age) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;

    }

    @Override
    public boolean equals(Object o){

        User u = (User) o;

        return this.email.equals(u.email);

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }

    public void addCourse(Course c){

        this.courses.add(c);

    }
}
