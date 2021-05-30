package com.yama.springboot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yama.springboot.DevProjectApplication;
import com.yama.springboot.entity.TopicsData;

/**
 * トップのservice
 * @author yama
 * @version 1.0
 */
@Service
public class TopService {

	/**
	 * 全量のトピックス情報から指定されたグループの情報のみ抽出する
	 * @param groupName 抽出対象のグループ名
	 * @return 抽出後のトピックス情報
	 */
	public List<TopicsData> extractTopics(String groupName) {

		return DevProjectApplication.topicsDataList.stream()
				.filter(topics -> groupName.equals(topics.getSendTo()))
				.collect(Collectors.toList());
	}
}
