package edu.java.client.stackoverflow;

import edu.java.client.dto.stackoverflow.GetQuestionResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface StackOverflowService {

    @GetExchange("/questions/{question_id}?site=stackoverflow")
    GetQuestionResponse getQuestion(@PathVariable("question_id") String questionId);
}
