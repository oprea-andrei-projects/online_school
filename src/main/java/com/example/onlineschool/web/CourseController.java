package com.example.onlineschool.web;

import com.example.onlineschool.models.Course;
import com.example.onlineschool.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService){

        this.courseService = courseService;
    }

    @GetMapping("/allCourses")
    public ResponseEntity<List<Course>> getAllTheCourses(){

        List<Course> courses = courseService.getAllCourses();

        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PostMapping("/addACourse")
    public ResponseEntity<Course> addACourse(@RequestBody Course c){

        courseService.addCourse(c);

        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @GetMapping("/findCourseByID/{id}")
    public ResponseEntity<Course> findByID(@PathVariable Long id){
        Course c = courseService.findTheCourseById(id);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCourse")
    public ResponseEntity<Long> deleteACourse(@RequestParam Long id){
        courseService.deleteCourse(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }



}
