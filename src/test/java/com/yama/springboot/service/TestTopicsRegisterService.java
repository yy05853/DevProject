package com.yama.springboot.service;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.yama.springboot.entity.TopicsData;
import com.yama.springboot.form.TopicsRegisterForm;
@SpringBootTest
class TestTopicsRegisterService {

	@BeforeEach
	private void before() {

	}

//	@Test
//	void testBuildFormById() {
//		assertEquals(false, false);
//	}
//
//	@Test
//	void testCreateTopicsData() {
//		fail("まだ実装されていません");
//	}
//
//	@Test
//	void testUpdateTopicsData() {
//		fail("まだ実装されていません");
//	}
//
//	@Test
//	void testDeleteTopicsData() {
//		fail("まだ実装されていません");
//	}

	@Test
	void testCreateFrom()
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TopicsData topicsData = new TopicsData();
		topicsData.setId(2);
		topicsData.setSendTo("グループ1");
		topicsData.setSendFrom("グループ1");
		topicsData.setTitle("コロナによる全面リモートワークのお知らせ");
		try {
			topicsData.setDisplayStartTime(new Timestamp(new SimpleDateFormat("yyyy/MM/dd").parse("2019/12/12").getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		topicsData.setDisplayEndTime(null);
		TopicsRegisterService topicsRegisterService = new TopicsRegisterService();
		Method method = TopicsRegisterService.class.getDeclaredMethod("createFrom", TopicsData.class);
		method.setAccessible(true);
		TopicsRegisterForm actual = (TopicsRegisterForm)method.invoke(topicsRegisterService, topicsData);

		assertEquals(actual.getId(), 2);
	}



}
