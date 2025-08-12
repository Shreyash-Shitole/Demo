package com.scoresizzle.service;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.scoresizzle.model.user.OtpToken;
import com.scoresizzle.model.user.RegistrationDetails;
import com.scoresizzle.repository.OtpTokenRepository;
import com.scoresizzle.repository.RegistrationDetailsRepository;

@Service
public class AuthService {

    private final RegistrationDetailsRepository registrationDetailsRepository;
    private final OtpTokenRepository otpTokenRepository;
    private final EmailService emailService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final SecureRandom secureRandom = new SecureRandom();

    public AuthService(RegistrationDetailsRepository registrationDetailsRepository,
                       OtpTokenRepository otpTokenRepository,
                       EmailService emailService) {
        this.registrationDetailsRepository = registrationDetailsRepository;
        this.otpTokenRepository = otpTokenRepository;
        this.emailService = emailService;
    }

    public boolean emailExists(String email) {
        return registrationDetailsRepository.existsByEmail(email.toLowerCase());
    }

    public void sendOtp(String email) {
        String normalizedEmail = email.toLowerCase();
        String otp = String.format("%06d", secureRandom.nextInt(1_000_000));

        OtpToken token = otpTokenRepository.findByEmail(normalizedEmail).orElseGet(OtpToken::new);
        token.setEmail(normalizedEmail);
        token.setOtp(otp);
        token.setExpiresAt(Instant.now().plus(10, ChronoUnit.MINUTES));
        otpTokenRepository.save(token);

        emailService.sendOtpEmail(normalizedEmail, otp);
    }

    public RegistrationDetails register(String fullName, String email, String password, String otp) {
        String normalizedEmail = email.toLowerCase();
        if (!StringUtils.hasText(otp)) {
            throw new IllegalArgumentException("OTP is required");
        }
        Optional<OtpToken> tokenOpt = otpTokenRepository.findByEmail(normalizedEmail);
        if (tokenOpt.isEmpty()) {
            throw new IllegalArgumentException("No OTP requested for this email");
        }
        OtpToken token = tokenOpt.get();
        if (token.getExpiresAt() == null || token.getExpiresAt().isBefore(Instant.now())) {
            throw new IllegalArgumentException("OTP has expired");
        }
        if (!token.getOtp().equals(otp)) {
            throw new IllegalArgumentException("Invalid OTP");
        }
        if (registrationDetailsRepository.existsByEmail(normalizedEmail)) {
            throw new IllegalArgumentException("Email already registered");
        }

        RegistrationDetails user = new RegistrationDetails();
        user.setFullName(fullName);
        user.setEmail(normalizedEmail);
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setVerified(true);
        RegistrationDetails saved = registrationDetailsRepository.save(user);

        otpTokenRepository.deleteByEmail(normalizedEmail);
        return saved;
    }

    public RegistrationDetails signin(String email, String password) {
        RegistrationDetails user = registrationDetailsRepository.findByEmail(email.toLowerCase())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return user;
    }
}