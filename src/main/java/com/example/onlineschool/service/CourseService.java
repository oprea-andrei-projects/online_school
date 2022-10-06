package com.example.onlineschool.service;

import com.example.onlineschool.exceptions.ElementAlreadyExistsException;
import com.example.onlineschool.exceptions.NoEnrolmentsException;
import com.example.onlineschool.exceptions.NoSuchIDFoundException;
import com.example.onlineschool.models.Course;
import com.example.onlineschool.repo.CourseRepo;
import com.example.onlineschool.repo.UserRepo;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private CourseRepo courseRepo;
    private UserRepo userRepo;

    public CourseService(CourseRepo courseRepo, UserRepo userRepo){

        this.courseRepo = courseRepo;
        this.userRepo = userRepo;
    }

    public List<Course> getAllCourses(){

        return courseRepo.findAll();
    }

    public void addCourse(Course c){

        List<Course> courseList = courseRepo.findAll();

        if(Collections.frequency(courseList,c)>0){

            throw new ElementAlreadyExistsException("Course already exists !!! ");
        }

        courseRepo.save(c);

    }

    public Course findTheCourseById(Long id){

        Optional<Course> co = courseRepo.findById(id);

        if(co.isEmpty()){

            throw new NoSuchIDFoundException("ID-ul cursului nu exista !!! ");
        }

        return co.get();

    }

    public boolean hasEnrolment(Long cid){

        Optional<Course>courseOptional = courseRepo.findById(cid);

        if(courseOptional.isEmpty()){

            throw new NoSuchIDFoundException("No course found !!! ");
        }

       return  courseRepo.enroledCourses(cid).size()!=0;

    }

    public void deleteCourse(Long id){

        Optional<Course> co = courseRepo.findById(id);

        if(co.isEmpty()){

            throw new NoSuchIDFoundException("ID-ul cursului nu exista !!! ");
        }

        if(hasEnrolment(id)){

            throw new NoEnrolmentsException("This Course has enrolments !!! ");
        }

        courseRepo.delete(co.get());

    }
}
