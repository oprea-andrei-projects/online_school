package com.example.onlineschool.service;

import com.example.onlineschool.models.Course;
import com.example.onlineschool.models.User;
import com.example.onlineschool.repo.CourseRepo;
import com.example.onlineschool.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.context.TestPropertySource;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@TestPropertySource(

        locations = "classpath:application-it.properties"
)
class UserServiceTest {

    @Mock
    private CourseRepo courseRepo;
    @Mock
    private UserRepo userRepo;

    @Captor
    private ArgumentCaptor<Course> courseArgumentCaptor;
    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @InjectMocks
    private UserService underTest;

    @BeforeEach
    void setUp(){

        MockitoAnnotations.openMocks(this);
        underTest = new UserService(userRepo,courseRepo);
    }


    @Test
    void test_getAllUsers(){

        List<User>userList = new ArrayList<>();
        User u = new User("username","ulastname","mail@mail.com","pass",20);
        userList.add(u);

        when(userRepo.findAll()).thenReturn(userList);

        assertEquals(userList.size(),1);

    }

    @Test
    void test_addUser(){
        List<User>list = new ArrayList<>();
        User u = new User("username","ulastname","mail@mail.com","pass",20);

        when(userRepo.findAll()).thenReturn(list);
        when(Collections.frequency(list,u)).thenReturn(0);
        underTest.addUser(u);

        then(userRepo).should().save(userArgumentCaptor.capture());
        User other = userArgumentCaptor.getValue();
        assertThat(u.getEmail().equals(other.getEmail()));


    }


    @Test
    void test_addCourse(){

        Course c2 = new Course("name2", "dep2");
        c2.setId(1L);
        User u = new User("username","ulastname","mail@mail.com","pass",20);
        u.setId(2L);
        u.addCourse(c2);

        when(courseRepo.findById(1L)).thenReturn(Optional.of(c2));
        when(userRepo.findById(2L)).thenReturn(Optional.of(u));
        when(courseRepo.enroledCourses(1L)).thenReturn(c2.getUsers());

        assertEquals(u.getCourses().size(),1);
    }

    @Test
    void test_unEnroll(){

        User u = new User("username","ulastname","mail@mail.com","pass",20);
        u.setId(2L);
        List<User> userList = new ArrayList<>();
        userList.add(u);
        Course c2 = new Course("name2", "dep2");
        c2.setId(1L);
        c2.setUsers(userList);
        List<Course> courseList = new ArrayList<>();
        courseList.add(c2);
        u.setCourses(courseList);

        when(courseRepo.findById(1L)).thenReturn(Optional.of(c2));
        when(userRepo.findById(2L)).thenReturn(Optional.of(u));
        when(userRepo.courseList(2L)).thenReturn(courseList);

        underTest.unenrollFromCourse(2L,1L);

        assertEquals(u.getCourses().size(),0);




    }






}