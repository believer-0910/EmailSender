package com.exadel.EmailSender.dto;

public class UserDto {
    private String firstName;

    private String lastName;

    private String email;

    private RoleDto roleDto;

    public UserDto(String firstName, String lastName, String email, RoleDto roleDto) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roleDto = roleDto;
    }

    public UserDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleDto getRoleDto() {
        return roleDto;
    }

    public void setRoleDto(RoleDto roleDto) {
        this.roleDto = roleDto;
    }
}
