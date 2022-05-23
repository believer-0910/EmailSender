package com.exadel.EmailSender.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;

import com.exadel.EmailSender.dto.EmailDto;
import com.exadel.EmailSender.service.EmailSender;
import com.fasterxml.jackson.databind.ObjectMapper;
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
}

