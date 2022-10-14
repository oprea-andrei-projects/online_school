package com.example.onlineschool;

import com.example.onlineschool.models.Book;
import com.example.onlineschool.models.Course;
import com.example.onlineschool.models.User;
import com.example.onlineschool.repo.BookRepo;
import com.example.onlineschool.repo.CourseRepo;
import com.example.onlineschool.repo.UserRepo;
import com.example.onlineschool.service.CourseService;
import com.example.onlineschool.service.UserService;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.transaction.Transactional;
import java.time.LocalDate;

@SpringBootApplication
public class OnlineSchoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineSchoolApplication.class, args);
    }


    @Bean
    @Transactional
    CommandLineRunner commandLineRunner(UserService userService){

        return args -> {


//             Course course=courseRepo.findById(4l).get();
//
//
//             User user =userRepo.findById(8l).get();
//
//
//             user.addCourse(course);
//
//             userRepo.save(user);


//            Faker faker = new Faker();
//
//            for(long i=1; i<10; i++){
//
//                User user=userRepo.findById(i).get();
//
//                user.addBook(new Book(faker.book().title(),LocalDate.of(2000,01,01)));
//                userRepo.save(user);
//            }

//            courseService.getAllCourses().stream().forEach(System.out::println);


//            userService.addABook(1L,new Book("carte",LocalDate.now()));

//            Book b = new Book("DasBook",LocalDate.now());
//            b.setId(26L);
//
//            userService.updateBook(1L,b);






        };
    }
}
