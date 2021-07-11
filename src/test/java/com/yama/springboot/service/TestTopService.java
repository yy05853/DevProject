package com.yama.springboot.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class TestTopService {


	TopService topService;

	@Test
	void testExtractTopics() {
		topService = new TopService();
		assertEquals(topService.testTrue("グループ2").size(),1);
//		fail("失敗");
	}

}
