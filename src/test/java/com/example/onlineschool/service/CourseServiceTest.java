package com.example.onlineschool.service;

import com.example.onlineschool.models.Course;
import com.example.onlineschool.models.User;
import com.example.onlineschool.repo.CourseRepo;
import com.example.onlineschool.repo.UserRepo;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;


@TestPropertySource(
        locations ="classpath:application-it.properties"
)
class CourseServiceTest {

    Faker fk = new Faker();

    @Mock
    private CourseRepo courseRepo;
    @Mock
    private UserRepo userRepo;

    @Captor
    private ArgumentCaptor<Course> courseArgumentCaptor;
    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @InjectMocks
    private CourseService underTest;

    @BeforeEach
    void setUp(){

        MockitoAnnotations.openMocks(this);
        underTest = new CourseService(courseRepo,userRepo);
    }

    @Test
    void getAllCourses(){

        List<Course>list = new ArrayList<>();
        Course c = new Course("name", "dep");
        list.add(c);

        when(courseRepo.findAll()).thenReturn(list);
        assertEquals(underTest.getAllCourses().size(),1);

    }

//    @Test
//    void test_addCourse(){
//        List<Course>list = new ArrayList<>();
//        Course c2 = new Course("name2", "dep2");
//
//        when(courseRepo.findAll()).thenReturn(list);
//        given(Collections.frequency(list,c2)).willReturn(0);
//        underTest.addCourse(c2);
//
//        then(courseRepo).should().save(courseArgumentCaptor.capture());
//        Course c3 = courseArgumentCaptor.getValue();
//        assertThat(c3).isEqualTo(c2);
//
//    }

    @Test
    void test_deleteCourse(){

        Course c2 = new Course("name2", "dep2");
        c2.setId(1L);

        when(courseRepo.findById(1L)).thenReturn(Optional.of(c2));

        underTest.deleteCourse(1L);
        then(courseRepo).should().delete(courseArgumentCaptor.capture());

        assertEquals(c2.getDepartment(),courseArgumentCaptor.getValue().getDepartment());

    }

    @Test
    void test_hasEnrollment(){

        Course c2 = new Course("name2", "dep2");
        c2.setId(1L);
        User u = new User("username","ulastname","mail@mail.com","pass",20);
        u.setId(2L);
        c2.addUser(u);

        when(courseRepo.findById(1L)).thenReturn(Optional.of(c2));
        when(userRepo.findById(2L)).thenReturn(Optional.of(u));
        when(courseRepo.enroledCourses(1L)).thenReturn(c2.getUsers());

        assertEquals(c2.getUsers().size(),1);

    }







}