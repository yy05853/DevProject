package com.yama.springboot.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.yama.springboot.DevProjectApplication;
import com.yama.springboot.entity.TopicsData;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TestTopService {

    private final static int TEST_ID = 1;
    private final static String TEST_VALUE = "test_value";

    private List<TopicsData> topicsDataList;
	private TopicsData testData;

	@Mock
	private DevProjectApplication devProjectApplication;
	@InjectMocks
	private TopService topService;

    @BeforeEach
    public void setup() {
    	TopicsData data = new TopicsData();
		// 1つ目のトピックスデータを作成する
		data = new TopicsData();
		data.setId(1);
		data.setSendTo("グループ1");
		data.setSendFrom("グループ1");
		data.setTitle("コロナによる全面リモートワークのお知らせ");
		try {
			data.setDisplayStartTime(new Timestamp(new SimpleDateFormat("yyyy/MM/dd").parse("2019/12/12").getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		data.setDisplayEndTime(null);
		topicsDataList.add(data);
	}

	@Test
	void testExtractTopics() {
//		topService = new TopService();

		when(this.devProjectApplication.findByName("グループ1")).thenReturn(this.topicsDataList);

		assertEquals(1,topService.extractTopics("グループ1").size());
//		assertEquals(0,topService.testTrue("グループ1").size());
//		fail("失敗");
	}

}
