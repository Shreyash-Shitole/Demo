package com.scoresizzle.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.scoresizzle.model.user.RegistrationDetails;

public interface RegistrationDetailsRepository extends MongoRepository<RegistrationDetails, String> {
    Optional<RegistrationDetails> findByEmail(String email);
    boolean existsByEmail(String email);
}