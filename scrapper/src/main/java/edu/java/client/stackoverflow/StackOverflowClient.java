package edu.java.client.stackoverflow;

import edu.java.client.dto.stackoverflow.GetQuestionResponse;

public interface StackOverflowClient {

    GetQuestionResponse getQuestion(String questionId);
}
