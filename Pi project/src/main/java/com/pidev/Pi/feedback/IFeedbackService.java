package com.pidev.Pi.feedback;



import com.pidev.Pi.feedback.Feedback;
import java.util.List;

public interface IFeedbackService {
    Feedback addFeedback(Feedback feedback, Long userId);
    List<Feedback> getAllFeedbacks();
    Feedback getFeedbackById(Long id);
    Feedback updateFeedback(Long id, Feedback feedback);
    void deleteFeedback(Long id);
}
