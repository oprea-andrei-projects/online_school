package com.example.onlineschool.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Course")
@Table(name = "course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @SequenceGenerator(
            name = "sequence_course",
            allocationSize = 1
    )
    @GeneratedValue(

            strategy = GenerationType.SEQUENCE,
            generator = "sequence_course"
    )
    Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotEmpty(message = "Course name must be filled !")
    String name;

    @Column(

            name = "department",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotEmpty(message = "Department must be filled !")
    String department;



    @ManyToMany(mappedBy = "courses",fetch = FetchType.EAGER)
    @JsonBackReference
    private List<User> users = new ArrayList<>();

    public void addUser(User user){
        this.users.add(user);
    }

    public Course(String name, String department) {

        this.name = name;
        this.department = department;

    }



    @Override
    public boolean equals(Object o){

        Course c = (Course) o;

        return this.name==c.name;
    }


}

