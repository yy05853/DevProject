package com.yama.springboot.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.yama.springboot.entity.TopicsData;
import com.yama.springboot.repository.TopicsDataRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TestTopService {

//    private final static int TEST_ID = 1;
//    private final static String TEST_VALUE = "test_value";
//
//    private List<TopicsData> topicsDataList;
//	private TopicsData testData;

//	@Mock
//	private DevProjectApplication devProjectApplication;
//	@InjectMocks
//	private TopService topService;

//    @BeforeEach
//    public void setup() {
//    	TopicsData data = new TopicsData();
//		// 1つ目のトピックスデータを作成する
//		data = new TopicsData();
//		data.setId(1);
//		data.setSendTo("グループ1");
//		data.setSendFrom("グループ1");
//		data.setTitle("コロナによる全面リモートワークのお知らせ");
//		try {
//			data.setDisplayStartTime(new Timestamp(new SimpleDateFormat("yyyy/MM/dd").parse("2019/12/12").getTime()));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		data.setDisplayEndTime(null);
//		topicsDataList.add(data);
//	}

	@Test
	void testExtractTopics() {
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
		TopService topService = new TopService(topicsDataRepository);

//		when(this.devProjectApplication.findByName("グループ1")).thenReturn(this.topicsDataList);

		assertEquals(1,topService.extractTopics("グループ1").size());
//		assertEquals(0,1,"message");
//		assertEquals(1,topService.testTrue("グループ1").size());
//		fail("失敗");
	}

}
