package com.pidev.Pi.feedback;



import com.pidev.Pi.feedback.Feedback;
import com.pidev.Pi.user.User;
import com.pidev.Pi.feedback.FeedbackRepository;
import com.pidev.Pi.user.UserRepository;
import com.pidev.Pi.feedback.IFeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements IFeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;

    @Override
    public Feedback addFeedback(Feedback feedback, Long userId) {
        User user = userRepository.findById((userId))
                .orElseThrow(() -> new RuntimeException("User not found"));
        feedback.setUser(user);
        return feedbackRepository.save(feedback);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
    }

    @Override
    public Feedback updateFeedback(Long id, Feedback feedback) {
        Feedback existingFeedback = getFeedbackById(id);
        existingFeedback.setContent(feedback.getContent());
        existingFeedback.setRating(feedback.getRating());
        return feedbackRepository.save(existingFeedback);
    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}

