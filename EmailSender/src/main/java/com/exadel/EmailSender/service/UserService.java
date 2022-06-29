package com.exadel.EmailSender.service;

import com.exadel.EmailSender.dto.UserDto;
import com.exadel.EmailSender.properties.GlobalUrlProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class UserService {

    private static final Logger log = LogManager.getLogger(UserService.class);
    private final WebClient.Builder webClientBuilder;
    private final GlobalUrlProperties globalUrlProperties;

    public UserService(WebClient.Builder webClientBuilder, GlobalUrlProperties globalUrlProperties) {
        this.webClientBuilder = webClientBuilder;
        this.globalUrlProperties = globalUrlProperties;
    }

    public List<UserDto> getAllUsers() {
        String get_all_users = globalUrlProperties.getGet_all_users();
        List<UserDto> userDtoList = webClientBuilder.build()
                .get()
                .uri(get_all_users)
                .retrieve()
                .bodyToFlux(UserDto.class)
                .doOnError(e -> log.error("Error while getting all users", e))
                .collectList()
                .block();
        assert userDtoList != null;
        if (userDtoList.isEmpty()) {
            throw new RuntimeException("Users not found");
        }
        return userDtoList;
    }

    public UserDto getUser(Long id) {
        String get_user_by_id = globalUrlProperties.getGet_user_by_id();
        UserDto userDto = webClientBuilder.build()
                .get()
                .uri(get_user_by_id + id)
                .retrieve()
                .bodyToMono(UserDto.class)
                .doOnError(e -> log.error("User not found"))
                .block();
        if (userDto == null) {
            throw new RuntimeException("User with id " + id + " not found");
        }
        return userDto;
    }
}
