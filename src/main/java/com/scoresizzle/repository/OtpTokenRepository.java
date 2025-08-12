package com.scoresizzle.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.scoresizzle.model.user.OtpToken;

public interface OtpTokenRepository extends MongoRepository<OtpToken, String> {
    Optional<OtpToken> findByEmail(String email);
    void deleteByEmail(String email);
}