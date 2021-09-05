package com.yama.springboot.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.yama.springboot.entity.TopicsData;
import com.yama.springboot.repository.TopicsDataRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TopicsListControllerTest {
	private MockMvc mockMvc;

	@Autowired
	TopicsListController target;

	@BeforeEach
	public void setup() {
	  mockMvc = MockMvcBuilders.standaloneSetup(target).build();
	}

	@Test
	void TestTopicksDataList() throws Exception {
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
		TopicsListController topicsListController = new TopicsListController(topicsDataRepository);

		mockMvc.perform(get("/TopicsList"))
        .andExpect(status().isOk())
        .andExpect(view().name("TopicsList.html"))
        .andExpect(model().attribute("topicsList", topicsDataRepository.getAllTopics()));
		// 上記テストは失敗します。

		//assertEquals(1,topicsListController.TopicsList().size());
	}

}
