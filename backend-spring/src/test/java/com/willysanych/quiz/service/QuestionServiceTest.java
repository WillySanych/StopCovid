package com.willysanych.quiz.service;

import com.willysanych.quiz.initializer.Postgres;
import com.willysanych.quiz.model.Question;
import com.willysanych.quiz.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(initializers = {
        Postgres.Initializer.class
})
@ActiveProfiles("test")
class QuestionServiceTest {

    @BeforeAll
    static void initDatabase() {
        Postgres.container.start();
    }

    @Autowired
    private QuestionRepository questionRepository;


    @BeforeEach
    public void addQuestionToDatabase() {

        questionRepository.deleteAll();

        List<String> questionAnswers1 = new ArrayList<>();
        questionAnswers1.add("a");
        questionAnswers1.add("b");
        questionAnswers1.add("c");

        List<String> questionAnswers2 = new ArrayList<>();
        questionAnswers2.add("d");
        questionAnswers2.add("e");
        questionAnswers2.add("f");

        Question question1 = new Question(1L, "someText", questionAnswers1, "radiobutton");
        Question question2 = new Question(2L, "someText", questionAnswers2, "radiobutton");

        questionRepository.save(question1);
        questionRepository.save(question2);
    }

    @Test
    void findAllQuestions() {

        List<String> questionAnswers1 = new ArrayList<>();
        questionAnswers1.add("a");
        questionAnswers1.add("b");
        questionAnswers1.add("c");

        List<String> questionAnswers2 = new ArrayList<>();
        questionAnswers2.add("d");
        questionAnswers2.add("e");
        questionAnswers2.add("f");

        Question expectedQuestion1 = new Question(1L, "someText", questionAnswers1, "radiobutton");
        Question expectedQuestion2 = new Question(2L, "someText", questionAnswers2, "radiobutton");

        List<Question> expectedQuestions = new ArrayList<>();
        expectedQuestions.add(expectedQuestion1);
        expectedQuestions.add(expectedQuestion2);

        List<Question> actualQuestions = questionRepository.findAll();

        assertThat(expectedQuestions).usingRecursiveComparison().ignoringFields("id").isEqualTo(actualQuestions);
    }

    @Test
    void findAllQuestionsText() {

        List<String> expectedQuestionText = new ArrayList<>();
        expectedQuestionText.add("someText");
        expectedQuestionText.add("someText");

        List<String> actualQuestionsText = questionRepository.findAllQuestionsText();

        assertEquals(expectedQuestionText, actualQuestionsText);

    }
}
