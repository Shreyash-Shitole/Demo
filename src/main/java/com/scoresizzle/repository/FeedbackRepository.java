package com.scoresizzle.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.scoresizzle.model.Feedback;

public interface FeedbackRepository extends MongoRepository<Feedback, String> {
}