package com.yama.springboot.service;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.yama.springboot.entity.TopicsData;
import com.yama.springboot.form.TopicsRegisterForm;
import com.yama.springboot.repository.TopicsDataRepository;
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
	void testIndexOf()
		throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		// Repositoryをオーバーライドする
		TopicsDataRepository topicsDataRepository = new TopicsDataRepository() {
			@Override
			public List<TopicsData> getAllTopics() {
				List<TopicsData> topicsDataList;

				topicsDataList = new LinkedList<>();

				// 1つ目のトピックスデータを作成する
				TopicsData topicsData = new TopicsData();
				topicsData.setId(1);
				topicsData.setSendTo("グループ1");
				topicsData.setSendFrom("グループ1");
				topicsData.setTitle("コロナによる全面リモートワークのお知らせ");
				try {
					topicsData.setDisplayStartTime(new Timestamp(new SimpleDateFormat("yyyy/MM/dd").parse("2019/12/12").getTime()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				topicsData.setDisplayEndTime(null);
				topicsDataList.add(topicsData);
				return topicsDataList;
			}
		};

		// privateメソッドを実行する
		// 一致するIDがある場合
		TopicsRegisterService topicsRegisterService = new TopicsRegisterService(topicsDataRepository);
		Method method = TopicsRegisterService.class.getDeclaredMethod("indexOf",int.class);
		method.setAccessible(true);
		int actual1 = (int)method.invoke(topicsRegisterService, 1);
		assertEquals(actual1, 0);

		// privateメソッドを実行する
		// 一致するIDがない場合
		int actual2 = (int)method.invoke(topicsRegisterService, 2);
		assertEquals(actual2, -1);


	}

	@Test
	void testSaveTopicsData()
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// Repositoryをオーバーライドする
		TopicsDataRepository topicsDataRepository = new TopicsDataRepository() {
			@Override
			public List<TopicsData> getAllTopics() {
				List<TopicsData> topicsDataList;

				topicsDataList = new LinkedList<>();

				// 1つ目のトピックスデータを作成する
				TopicsData topicsData = new TopicsData();
				topicsData.setId(1);
				topicsData.setSendTo("グループ1");
				topicsData.setSendFrom("グループ1");
				topicsData.setTitle("コロナによる全面リモートワークのお知らせ");
				try {
					topicsData.setDisplayStartTime(new Timestamp(new SimpleDateFormat("yyyy/MM/dd").parse("2019/12/12").getTime()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				topicsData.setDisplayEndTime(null);
				topicsDataList.add(topicsData);
				return topicsDataList;
			}

			@Override
			public boolean addTopics(TopicsData topicsData) {
				return true;
			}
		};

		// topicsDataを作成する
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

		// privateメソッドを実行する
		TopicsRegisterService topicsRegisterService = new TopicsRegisterService(topicsDataRepository);
		Method method = TopicsRegisterService.class.getDeclaredMethod("saveTopicsData", TopicsData.class);
		method.setAccessible(true);
		boolean actual = (boolean)method.invoke(topicsRegisterService, topicsData);

		assertEquals(actual, true);
	}

	@Test
	void testCreateFrom()
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// Repositoryをオーバーライドする
		TopicsDataRepository topicsDataRepository = new TopicsDataRepository() {
			@Override
			public List<TopicsData> getAllTopics() {
				List<TopicsData> topicsDataList;

				topicsDataList = new LinkedList<>();

				// 1つ目のトピックスデータを作成する
				TopicsData topicsData = new TopicsData();
				topicsData.setId(1);
				topicsData.setSendTo("グループ1");
				topicsData.setSendFrom("グループ1");
				topicsData.setTitle("コロナによる全面リモートワークのお知らせ");
				try {
					topicsData.setDisplayStartTime(new Timestamp(new SimpleDateFormat("yyyy/MM/dd").parse("2019/12/12").getTime()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				topicsData.setDisplayEndTime(null);
				topicsDataList.add(topicsData);
				return topicsDataList;
			}
		};

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
		TopicsRegisterService topicsRegisterService = new TopicsRegisterService(topicsDataRepository);
		Method method = TopicsRegisterService.class.getDeclaredMethod("createFrom", TopicsData.class);
		method.setAccessible(true);
		TopicsRegisterForm actual = (TopicsRegisterForm)method.invoke(topicsRegisterService, topicsData);

		assertEquals(actual.getId(), 2);
	}



}
