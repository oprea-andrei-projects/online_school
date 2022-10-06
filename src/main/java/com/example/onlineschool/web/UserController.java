package com.example.onlineschool.web;

import com.example.onlineschool.models.Course;
import com.example.onlineschool.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class UserController {

    private UserService userService;

    public UserController(UserService userService){

        this.userService = userService;
    }

    @PostMapping("/placeEnrolment")
    public ResponseEntity<Course> placeAnEnrolment(@RequestParam Long id, @RequestBody Course course){

        userService.addCourse(id,course);

        return new ResponseEntity<>(course, HttpStatus.OK);

    }

    @DeleteMapping("/unenroll")
    public ResponseEntity<Long> unenrollFromCourse(@RequestParam Long uid, @RequestParam Long cid){

        userService.unenrollFromCourse(uid,cid);

        return new ResponseEntity<>(cid, HttpStatus.OK);
    }





}
