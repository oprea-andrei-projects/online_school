package com.example.onlineschool.repo;

import com.example.onlineschool.models.Course;
import com.example.onlineschool.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query("select u.courses from User u where u.id=?1")
    List<Course>courseList(Long id);
}
