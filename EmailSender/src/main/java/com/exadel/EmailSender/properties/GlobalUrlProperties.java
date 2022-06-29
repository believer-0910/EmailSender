package com.exadel.EmailSender.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:global_url.properties")
@ConfigurationProperties("url")
public class GlobalUrlProperties {

    private String get_user_by_id;
    private String get_all_users;

    public String getGet_user_by_id() {
        return get_user_by_id;
    }

    public void setGet_user_by_id(String get_user_by_id) {
        this.get_user_by_id = get_user_by_id;
    }

    public String getGet_all_users() {
        return get_all_users;
    }

    public void setGet_all_users(String get_all_users) {
        this.get_all_users = get_all_users;
    }
}
