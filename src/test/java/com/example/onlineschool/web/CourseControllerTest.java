package com.example.onlineschool.web;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.onlineschool.OnlineSchoolApplication;
import com.example.onlineschool.models.Course;
import com.example.onlineschool.repo.CourseRepo;
import com.example.onlineschool.repo.UserRepo;
import com.example.onlineschool.service.CourseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestPropertySource(
        locations = "classpath:application-it.properties"
)
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OnlineSchoolApplication.class)
@AutoConfigureMockMvc
class CourseControllerTest {

    @MockBean
    private CourseRepo courseRepo;

    @MockBean
    private UserRepo userRepo;

    @MockBean
    private CourseService courseService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_getAllCourses() throws Exception {
        List<Course>list = new ArrayList<>();
        Course c2 = new Course("name2", "dep2");
//        c2.setId(1L);
        list.add(c2);

        when(courseService.getAllCourses()).thenReturn(list);
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/allCourses")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect( content().string(mapper.writeValueAsString(list)));

    }

    @Test
    void test_addACourse() throws Exception {
        Course c2 = new Course("name2", "dep2");
        c2.setId(2L);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/addACourse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(c2)))
                .andExpect(status().isOk());


    }

    @Test
    void test_deleteCourse() throws Exception {

        Course c2 = new Course("name2", "dep2");
        c2.setId(2L);

        mockMvc.perform(MockMvcRequestBuilders.delete(String.format("/api/v1/deleteCourse?id=%d",2L))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

    }




    public static String asJsonString(final Object obj) throws JsonProcessingException {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}