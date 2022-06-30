package com.exadel.EmailSender.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:hotelServiceURLs.properties")
@ConfigurationProperties("url")
public class HotelServiceProperties {

    private String getUserById;
    private String getAllUsers;

    public String getGetUserById() {
        return getUserById;
    }

    public void setGetUserById(String getUserById) {
        this.getUserById = getUserById;
    }

    public String getGetAllUsers() {
        return getAllUsers;
    }

    public void setGetAllUsers(String getAllUsers) {
        this.getAllUsers = getAllUsers;
    }
}
