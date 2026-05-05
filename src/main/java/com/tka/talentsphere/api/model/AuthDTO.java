package com.tka.talentsphere.api.model;

import lombok.Data;

public class AuthDTO {

    @Data
    public static class SignupRequest {
        private String fullName;
        private String email;
        private String password;
    }

    @Data
    public static class LoginRequest {
        private String email;
        private String password;
    }

    @Data
    public static class AuthResponse {
        private String token;
        private Long hrId;
        private String fullName;
        private String email;
        private String role;

        public AuthResponse(String token, Long hrId, String fullName, String email, String role) {
            this.token = token;
            this.hrId = hrId;
            this.fullName = fullName;
            this.email = email;
            this.role = role;
        }
    }
}
