package com.exadel.EmailSender.service;

import com.exadel.EmailSender.dto.UserDto;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

import static com.exadel.EmailSender.utils.HotelMainUtils.GET_ALL_USERS;
import static com.exadel.EmailSender.utils.HotelMainUtils.GET_USER_BY_ID;

@Service
public class UserService {

    private final Logger log;
    private final WebClient.Builder webClientBuilder;

    public UserService(Logger log, WebClient.Builder webClientBuilder) {
        this.log = log;
        this.webClientBuilder = webClientBuilder;
    }

    public List<UserDto> getAllUsers() {
        List<UserDto> userDtoList = userDtoList = webClientBuilder.build()
                    .get()
                    .uri(GET_ALL_USERS)
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

    public UserDto getUser(Long id){
        UserDto userDto =  webClientBuilder.build()
                    .get()
                    .uri(GET_USER_BY_ID + id)
                    .retrieve()
                    .bodyToMono(UserDto.class)
                    .doOnError(e -> {
                        log.error("User not found");
                    })
                    .block();
        if  (Objects.equals(userDto, new UserDto())) {
            throw new RuntimeException("User with id " + id + " not found");
        }
        return userDto;
    }
}
