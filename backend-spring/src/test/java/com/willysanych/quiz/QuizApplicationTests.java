package com.willysanych.quiz;

import com.willysanych.quiz.initializer.Postgres;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(initializers = {
		Postgres.Initializer.class
})
@ActiveProfiles("test")
public class QuizApplicationTests {

	@BeforeAll
	static void initDatabase() {
		Postgres.container.start();
	}

	@Test
	void contextLoads() {
	}

}
