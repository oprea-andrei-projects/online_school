package com.example.onlineschool.service;

import com.example.onlineschool.models.Book;
import com.example.onlineschool.models.Course;
import com.example.onlineschool.models.User;
import com.example.onlineschool.repo.BookRepo;
import com.example.onlineschool.repo.CourseRepo;
import com.example.onlineschool.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@TestPropertySource(

        locations = "classpath:application-it.properties"
)
class UserServiceTest {

    @Mock
    private CourseRepo courseRepo;
    @Mock
    private UserRepo userRepo;

    @Mock
    private BookRepo bookRepo;

    @Captor
    private ArgumentCaptor<Course> courseArgumentCaptor;
    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Captor
    private ArgumentCaptor<Book> bookArgumentCaptor;

    @InjectMocks
    private UserService underTest;

    @BeforeEach
    void setUp(){

        MockitoAnnotations.openMocks(this);
        underTest = new UserService(userRepo,courseRepo, bookRepo);
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

    @Test
    void test_addBook(){

        User u = new User("username","ulastname","mail@mail.com","pass",20);
        u.setId(1L);
        Book b = new Book("MyBook", LocalDate.of(2002,02,02));
        List<Book> books = new ArrayList<>();

        given(userRepo.findById(u.getId())).willReturn(Optional.of(u));
        given(userRepo.bookList(u.getId())).willReturn(books);
        given(Collections.frequency(books,b)).willReturn(0);

        underTest.addABook(u.getId(),b);

        then(userRepo).should().save(userArgumentCaptor.capture());
        User u2 = userArgumentCaptor.getValue();
        assertThat(u2).isEqualTo(u);
    }

    @Test
    void test_deleteBook(){

        User u = new User("username","ulastname","mail@mail.com","pass",20);
        u.setId(1L);
        Book b = new Book("MyBook", LocalDate.of(2002,02,02));
        b.setId(2L);
        b.setUser(u);

        doReturn(Optional.of(b)).when(bookRepo).findById(2L);
        doReturn(Optional.of(u)).when(userRepo).findById(1L);
        underTest.deleteTheBook(u.getId(),b.getId());
        then(userRepo).should().save(u);

    }

    @Test
    void test_updateBook(){

        User u = new User("username","ulastname","mail@mail.com","pass",20);
        u.setId(1L);


        Book b1 = new Book("MB2", LocalDate.of(2002,02,02));
        b1.setId(2L);
        Book b = new Book("MyBook", LocalDate.of(2002,02,02));
        b.setId(b1.getId());
        b.setUser(u);

        doReturn(Optional.of(b)).when(bookRepo).findById(2L);
        doReturn(Optional.of(u)).when(userRepo).findById(1L);

        underTest.updateBook(u.getId(),b1);

        assertThat(b.getTitle()).isEqualTo("MB2");
    }










}