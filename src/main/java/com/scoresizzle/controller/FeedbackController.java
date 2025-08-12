package com.scoresizzle.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scoresizzle.model.Feedback;
import com.scoresizzle.repository.FeedbackRepository;

@Validated
@RestController
public class FeedbackController {

    private final FeedbackRepository feedbackRepository;

    public FeedbackController(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public static class FeedbackRequest {
        @Email
        @NotBlank
        public String email;
        @NotBlank
        public String message;
    }

    @PostMapping("/sendFeedback")
    public ResponseEntity<?> sendFeedback(@RequestBody FeedbackRequest request) {
        Feedback feedback = new Feedback();
        feedback.setEmail(request.email);
        feedback.setMessage(request.message);
        Feedback saved = feedbackRepository.save(feedback);
        return ResponseEntity.ok().body(java.util.Map.of("id", saved.getId()));
    }
}