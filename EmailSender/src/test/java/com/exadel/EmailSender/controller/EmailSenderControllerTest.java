package com.exadel.EmailSender.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.exadel.EmailSender.dto.EmailDto;
import com.exadel.EmailSender.service.EmailSender;
import com.exadel.EmailSender.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {EmailSenderController.class})
@ExtendWith(SpringExtension.class)
class EmailSenderControllerTest {
    @MockBean
    private EmailSender emailSender;

    @Autowired
    private EmailSenderController emailSenderController;

    @MockBean
    private EmailService emailService;

    @Test
    void testSendEmailToAllUsers() throws Exception {
        doNothing().when(this.emailSender).sendEmailToAllUsers((EmailDto) any());

        EmailDto emailDto = new EmailDto();
        emailDto.setSubject("Hello from the Dreaming Spires");
        emailDto.setText("Text");
        emailDto.setUserId(123L);
        String content = (new ObjectMapper()).writeValueAsString(emailDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/email/send/toAllUsers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.emailSenderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testSendEmailToUser() throws Exception {
        doNothing().when(this.emailSender).sendEmailToUser((EmailDto) any());

        EmailDto emailDto = new EmailDto();
        emailDto.setSubject("Hello from the Dreaming Spires");
        emailDto.setText("Text");
        emailDto.setUserId(123L);
        String content = (new ObjectMapper()).writeValueAsString(emailDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/email/send/toUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.emailSenderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGet() throws Exception {
        when(this.emailService.getEmail((Long) any())).thenReturn(new EmailDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/email/send/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.emailSenderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"userId\":null,\"subject\":null,\"text\":null}"));
    }

    @Test
    void testGet2() throws Exception {
        when(this.emailService.getEmail((Long) any())).thenReturn(new EmailDto());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/email/send/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.emailSenderController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"userId\":null,\"subject\":null,\"text\":null}"));
    }


    @Test
    void testGetAll() throws Exception {
        when(this.emailService.getAllEmails()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/email/send/getAll");
        MockMvcBuilders.standaloneSetup(this.emailSenderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAll2() throws Exception {
        when(this.emailService.getAllEmails()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/email/send/getAll");
        MockMvcBuilders.standaloneSetup(this.emailSenderController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(this.emailService).deleteEmail((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/email/send/delete/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.emailSenderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDelete2() throws Exception {
        doNothing().when(this.emailService).deleteEmail((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/email/send/delete/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.emailSenderController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        when(this.emailService.updateEmail((Long) any(), (EmailDto) any())).thenReturn(new EmailDto());

        EmailDto emailDto = new EmailDto();
        emailDto.setSubject("Hello from the Dreaming Spires");
        emailDto.setText("Text");
        emailDto.setUserId(123L);
        String content = (new ObjectMapper()).writeValueAsString(emailDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/email/send/update/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.emailSenderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"userId\":null,\"subject\":null,\"text\":null}"));
    }
}

