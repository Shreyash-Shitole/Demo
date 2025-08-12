package com.scoresizzle.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scoresizzle.model.user.RegistrationDetails;
import com.scoresizzle.service.AuthService;

@Validated
@RestController
@RequestMapping("/get-started")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    public static class CheckDuplicateRequest {
        @Email
        @NotBlank
        public String email;
    }

    public static class ConfirmEmailRequest {
        @Email
        @NotBlank
        public String email;
    }

    public static class RegisterRequest {
        @NotBlank
        public String fullName;
        @Email
        @NotBlank
        public String email;
        @NotBlank
        public String password;
        @NotBlank
        public String otp;
    }

    public static class SigninRequest {
        @Email
        @NotBlank
        public String email;
        @NotBlank
        public String password;
    }

    @PostMapping("/checkduplicate")
    public ResponseEntity<?> checkDuplicate(@RequestBody CheckDuplicateRequest request) {
        boolean exists = authService.emailExists(request.email);
        return ResponseEntity.ok().body(java.util.Map.of("exists", exists));
    }

    @PostMapping("/confirm_email")
    public ResponseEntity<?> confirmEmail(@RequestBody ConfirmEmailRequest request) {
        authService.sendOtp(request.email);
        return ResponseEntity.ok().body(java.util.Map.of("status", "OTP_SENT"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        RegistrationDetails user = authService.register(request.fullName, request.email, request.password, request.otp);
        return ResponseEntity.ok().body(java.util.Map.of(
                "id", user.getId(),
                "email", user.getEmail(),
                "fullName", user.getFullName()
        ));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest request) {
        RegistrationDetails user = authService.signin(request.email, request.password);
        return ResponseEntity.ok().body(java.util.Map.of(
                "id", user.getId(),
                "email", user.getEmail(),
                "fullName", user.getFullName()
        ));
    }
}