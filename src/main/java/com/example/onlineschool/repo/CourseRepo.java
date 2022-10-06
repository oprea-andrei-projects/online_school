package com.example.onlineschool.repo;

import com.example.onlineschool.models.Course;
import com.example.onlineschool.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {

    @Query("select c.users from Course c where c.id=?1")
    List<User> enroledCourses(Long id);



}
