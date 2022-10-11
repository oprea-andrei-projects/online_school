package com.example.onlineschool.web;

import com.example.onlineschool.models.Book;
import com.example.onlineschool.models.Course;
import com.example.onlineschool.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getUserCourses/{id}")
    public ResponseEntity<List<Course>> getTheUserCourses(@PathVariable Long id){
        List<Course> list = userService.showStudentCourseList(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/studentHasEnrollment")
    public ResponseEntity<Boolean> hasEnrollment(@RequestParam Long uid, @RequestParam Long cid){
        Boolean b = userService.studentHasEnrolment(uid,cid);
        return new ResponseEntity<>(b,HttpStatus.OK);
    }

    @GetMapping("/getUserBooks/{id}")
    public ResponseEntity<List<Book>> getTheUserBooks(@PathVariable Long id){

        List<Book> list = userService.getUserBookList(id);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/addBook")
    public ResponseEntity<String> addABook(@RequestParam Long id, @RequestBody Book b){
        userService.addABook(id, b);
        return new ResponseEntity<>(b.getTitle(),HttpStatus.OK);
    }

    @DeleteMapping("/deleteBook")
    public ResponseEntity<Long> deleteABook(@RequestParam Long uid, @RequestParam Long bid){
        userService.deleteTheBook(uid,bid);
        return new ResponseEntity<>(bid, HttpStatus.OK);

    }




}
