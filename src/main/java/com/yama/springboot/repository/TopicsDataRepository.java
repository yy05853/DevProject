package com.yama.springboot.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yama.springboot.DevProjectApplication;
import com.yama.springboot.entity.TopicsData;

@Repository
public class TopicsDataRepository {

	public List<TopicsData> getAllTopics() {
		return DevProjectApplication.topicsDataList;
	}

	public boolean addTopics(TopicsData topicsData) {
		DevProjectApplication.topicsDataList.add(topicsData);

		return true;
	}

}
