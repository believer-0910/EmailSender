package com.exadel.EmailSender.service;

import com.exadel.EmailSender.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<UserDto> getAllUsers() {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/user/getAll")
                .retrieve()
                .bodyToFlux(UserDto.class)
                .collectList()
                .block();
    }
}
