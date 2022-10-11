package com.example.onlineschool.web;

import com.example.onlineschool.OnlineSchoolApplication;
import com.example.onlineschool.models.Course;
import com.example.onlineschool.models.User;
import com.example.onlineschool.repo.CourseRepo;
import com.example.onlineschool.repo.UserRepo;
import com.example.onlineschool.service.CourseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(
        locations = "classpath:application-it.properties"
)
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OnlineSchoolApplication.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @MockBean
    private CourseRepo courseRepo;

    @MockBean
    private UserRepo userRepo;

    @MockBean
    private CourseService courseService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_enrolment() throws Exception {

        User u = new User("username","ulastname","mail@mail.com","pass",20);
        u.setId(9L);
        Course c2 = new Course("name2", "dep2");
        c2.setId(3L);
        when(userRepo.findById(9L)).thenReturn(Optional.of(u));
        when(courseRepo.findById(3L)).thenReturn(Optional.of(c2));
        mockMvc.perform(MockMvcRequestBuilders.post(String.format("/api/v1/placeEnrolment?id=%d",9L))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(c2)))
                .andExpect(status().isOk());

    }

    @Test
    void test_unenroll() throws Exception {

        User u = new User("username","ulastname","mail@mail.com","pass",20);
        u.setId(1L);
        Course c2 = new Course("name2", "dep2");
        c2.setId(1L);
        when(userRepo.findById(1L)).thenReturn(Optional.of(u));
        when(courseRepo.findById(1L)).thenReturn(Optional.of(c2));

        mockMvc.perform(MockMvcRequestBuilders.delete(String.format("/api/v1/unenroll?uid=%d&cid=%d",1L,1L))
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