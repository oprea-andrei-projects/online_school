package com.example.onlineschool.service;


import com.example.onlineschool.exceptions.ElementAlreadyExistsException;
import com.example.onlineschool.exceptions.NoEnrolmentsException;
import com.example.onlineschool.exceptions.NoSuchIDFoundException;
import com.example.onlineschool.models.Course;
import com.example.onlineschool.models.User;
import com.example.onlineschool.repo.CourseRepo;
import com.example.onlineschool.repo.UserRepo;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepo userRepo;
    private CourseRepo courseRepo;



    public UserService(UserRepo userRepo, CourseRepo courseRepo){

        this.userRepo = userRepo;
        this.courseRepo = courseRepo;

    }


    //----user----//

    public List<User>getAllUsers(){

        return userRepo.findAll();
    }

    public void addUser(User u){

        List<User> userList = userRepo.findAll();

        if(Collections.frequency(userList,u)>0){

            throw new ElementAlreadyExistsException("User already exists !!! ");
        }


        userRepo.save(u);

    }

    public boolean studentHasEnrolment(Long uid, Long cid){

        Optional<User>userOptional = userRepo.findById(uid);

        if(userOptional.isEmpty()){

            throw new NoSuchIDFoundException("No user found !!! ");
        }

        List<Course> list = userRepo.courseList(uid).stream().filter(e->e.getId()==cid).collect(Collectors.toList());

        return list.size()!=0;

    }



    public void addCourse(Long uid, Course course){

        Optional<User> userOptional = userRepo.findById(uid);
        Optional<Course> courseOptional = courseRepo.findById(course.getId());

        if(userOptional.isEmpty()){

            throw new NoSuchIDFoundException("No user found !!! ");
        }
        if(courseOptional.isEmpty()){

            throw new NoSuchIDFoundException("No course found !!! ");
        }

        if(studentHasEnrolment(uid, course.getId())){

            throw new ElementAlreadyExistsException("Student is already enrolled at this course !!! ");
        }

        userOptional.get().addCourse(course);

        userRepo.save(userOptional.get());

    }

    public void unenrollFromCourse(Long uid, Long cid){

        Optional<User> userOptional = userRepo.findById(uid);
        Optional<Course> courseOptional = courseRepo.findById(cid);


        if(userOptional.isEmpty()){

            throw new NoSuchIDFoundException("No user found !!! ");
        }
        if(courseOptional.isEmpty()){

            throw new NoSuchIDFoundException("No course found !!! ");
        }

        userOptional.get().getCourses().remove(courseOptional.get());
        userRepo.save(userOptional.get());



    }











}