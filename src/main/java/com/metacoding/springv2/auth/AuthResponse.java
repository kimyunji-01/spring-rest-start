package com.metacoding.springv2.auth;

import com.metacoding.springv2.user.User;

import lombok.Data;

public class AuthResponse {
    @Data
    public static class DTO {
        private Integer id;
        private String username;
        private String roles;

        public DTO(User user) {
            this.id = id;
            this.username = username;
            this.roles = roles;
        }

    }

}